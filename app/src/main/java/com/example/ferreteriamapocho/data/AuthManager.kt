package com.example.ferreteriamapocho.data

import android.util.Log
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthManager {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile = _userProfile.asStateFlow()

    init {
        loadUserProfile()
    }

    fun loadUserProfile() {
        val user = auth.currentUser ?: run {
            _userProfile.value = null
            return
        }

        db.collection("users")
            .document(user.uid)
            .addSnapshotListener { snapshot, _ ->
                if (snapshot != null && snapshot.exists()) {
                    _userProfile.value = snapshot.toObject(UserProfile::class.java)
                }
            }
    }

    fun registerUser(
        name: String,
        lastName: String,
        email: String,
        phone: String,
        address: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email.trim(), password)
            .addOnCompleteListener { task ->

                if (!task.isSuccessful) {
                    val ex = task.exception
                    Log.e("AuthManager", "registerUser failed", ex)

                    val errorCode = (ex as? FirebaseAuthException)?.errorCode

                    val errorMessage = when (errorCode) {
                        "ERROR_EMAIL_ALREADY_IN_USE" ->
                            "El correo ya está registrado"
                        "ERROR_INVALID_EMAIL" ->
                            "Correo inválido"
                        "ERROR_WEAK_PASSWORD" ->
                            "La contraseña debe tener al menos 6 caracteres"
                        "ERROR_OPERATION_NOT_ALLOWED" ->
                            "Método de login no habilitado en Firebase Console"
                        null -> when (ex) {
                            is FirebaseNetworkException ->
                                "Sin conexión a Internet. Revisa tu red."
                            else ->
                                ex?.localizedMessage ?: "Error desconocido al crear la cuenta"
                        }
                        else ->
                            "Error al crear cuenta ($errorCode)"
                    }

                    onResult(false, errorMessage)
                    return@addOnCompleteListener
                }

                val uid = auth.currentUser!!.uid

                val profile = UserProfile(
                    uid = uid,
                    name = name,
                    lastName = lastName,
                    email = email,
                    phone = phone,
                    address = address
                )

                db.collection("users")
                    .document(uid)
                    .set(profile)
                    .addOnSuccessListener {
                        _userProfile.value = profile
                        onResult(true, null)
                    }
                    .addOnFailureListener { e ->
                        Log.e("AuthManager", "Error guardando perfil", e)
                        onResult(false, "Error guardando datos en Firestore")
                    }
            }
    }

    fun loginUser(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email.trim(), password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    loadUserProfile()
                    onResult(true, null)
                } else {
                    val ex = task.exception
                    Log.e("AuthManager", "loginUser failed", ex)

                    val errorCode = (ex as? FirebaseAuthException)?.errorCode

                    val errorMessage = when (errorCode) {
                        "ERROR_USER_NOT_FOUND" -> "Usuario no existe"
                        "ERROR_WRONG_PASSWORD" -> "Contraseña incorrecta"
                        "ERROR_INVALID_EMAIL" -> "Correo inválido"
                        "ERROR_USER_DISABLED" -> "Cuenta deshabilitada"
                        null -> when (ex) {
                            is FirebaseNetworkException ->
                                "Sin conexión a Internet. Revisa tu red."
                            else ->
                                ex?.localizedMessage ?: "Error desconocido al iniciar sesión"
                        }
                        else -> "Error al iniciar sesión ($errorCode)"
                    }

                    onResult(false, errorMessage)
                }
            }
    }

    fun updateUserProfile(name: String, lastName: String, phone: String, address: String) {
        val user = auth.currentUser ?: return

        val updated = mapOf(
            "name" to name,
            "lastName" to lastName,
            "phone" to phone,
            "address" to address
        )

        db.collection("users")
            .document(user.uid)
            .update(updated)
    }

    fun logout() {
        auth.signOut()
        _userProfile.value = null
    }
}