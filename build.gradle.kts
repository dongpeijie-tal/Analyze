buildscript {
    repositories {
        google()
    }

    dependencies {
        classpath("io.github.didi:drouter-plugin:1.4.0")
    }
}

plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.android.library") version "8.1.1" apply false
}