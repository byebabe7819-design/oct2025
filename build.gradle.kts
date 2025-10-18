plugins {
    kotlin("jvm") version "1.9.10" apply false
}

allprojects {
    repositories {
        mavenCentral()
        google()
    }
}

subprojects {
    // apply Kotlin JVM plugin where needed
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        mavenCentral()
    }

    dependencies {
        "testImplementation"("org.junit.jupiter:junit-jupiter:5.10.0")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
