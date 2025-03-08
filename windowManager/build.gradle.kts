import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.io.FileInputStream
import java.util.Properties


tasks.register("copy-secret-to-gradle-properties") {
    val secretsPropertiesFile = rootProject.file("secrets.properties")
    val gradlePropertiesFile = rootProject.file("gradle.properties")
    gradlePropertiesFile.appendText(secretsPropertiesFile.readText())
}

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.gradle.secrets)
}

group = "io.github.abdelrahmanesam"
version = "1.0.0"

kotlin {
    androidTarget {
        publishLibraryVariants("release")
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.window)
                implementation(libs.androidx.activity.compose)
            }
        }
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
                implementation(compose.components.resources)
                api(libs.kotlinx.serialization.json)
                api(libs.kotlinx.coroutines.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
    }
}

android {
    namespace = "com.apptikar.kmpwindowmanager"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}


mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    coordinates(group.toString(), "KMPWindowManager", "0.5.0")

    pom {
        name = "KMPWindowManager"
        description =
            "extract window info easily with KMPWindowManager and support different screen sizes"
        inceptionYear = "2025"
        url = "https://github.com/kotlin/multiplatform-library-template/"
        licenses {
            license {
                name.set("The Apache Software License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        scm {
            url.set("https://github.com/AbdelrahmanEsam/KMPWindowManager")
            connection.set("scm:git:https://github.com/AbdelrahmanEsam/KMPWindowManager.git")
        }
        developers {
            developer {
                id = "Abd-Elrahman Esam"
                name = "Abd-Elrahman Esam"
                url = "https://www.linkedin.com/in/abd-elrahman-esam/"
            }
        }
    }
}

tasks.named("publishAndReleaseToMavenCentral") {
    dependsOn("copy-secret-to-gradle-properties")
}

secrets {
    propertiesFileName = "secrets.properties"
}
