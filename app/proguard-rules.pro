########################################
# Mantener info útil de crashes (recomendado)
########################################
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes InnerClasses
-keepattributes EnclosingMethod
-keepattributes SourceFile,LineNumberTable

########################################
# Firebase / Google Play Services (normalmente no requiere keep, pero ayuda)
########################################
-dontwarn com.google.firebase.**
-dontwarn com.google.android.gms.**
-dontwarn com.google.common.**

########################################
# Room (recomendado para evitar problemas con anotaciones)
########################################
-keep class androidx.room.** { *; }
-dontwarn androidx.room.**

########################################
# Kotlin coroutines
########################################
-dontwarn kotlinx.coroutines.**

########################################
# Jetpack Compose / Navigation (normalmente OK, pero no molesta)
########################################
-dontwarn androidx.compose.**
-dontwarn androidx.navigation.**

########################################
# Coil
########################################
-dontwarn coil.**
