pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    // (IMPORTANTE) evita que algún módulo “rompa” los repos
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)

    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ferreteria mapocho"
include(":app")
