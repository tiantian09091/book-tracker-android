// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

buildscript {
    val enforcedJavaPoetVersion = "1.13.0"
    dependencies {
        classpath("com.squareup:javapoet:$enforcedJavaPoetVersion")
    }
    configurations.all {
        resolutionStrategy.eachDependency {
            if (requested.group == "com.squareup" && requested.name == "javapoet") {
                useVersion(enforcedJavaPoetVersion)
                because("Hilt Gradle plugin requires ClassName.canonicalName() which was added in Javapoet 1.13.0")
            }
        }
    }
}

subprojects {
    val enforcedJavaPoetVersion = "1.13.0"
    configurations.all {
        resolutionStrategy.eachDependency {
            if (requested.group == "com.squareup" && requested.name == "javapoet") {
                useVersion(enforcedJavaPoetVersion)
            }
        }
    }
}
