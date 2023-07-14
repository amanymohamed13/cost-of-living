import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20"
    application
}

group = "org.the-chance"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
    implementation("org.webjars.npm:before:0.0.1")
    implementation("org.webjars.npm:before:0.0.1")
    implementation("org.webjars.npm:before:0.0.1")
    implementation("org.webjars.npm:before-after-hook:2.2.3")
    implementation("org.webjars.npm:before-after-hook:2.2.3")
    implementation("com.github.bigwheel:scalatest-structured-before_2.12:1.0.1")
    implementation("com.github.bigwheel:scalatest-structured-before_2.12:1.0.1")
    implementation("com.github.bigwheel:scalatest-structured-before_2.12:1.0.1")
    implementation("io.opencaesar.ecore:ecore-bikeshed-gradle:0.9.0")
    implementation("it.krzeminski.vis-assert:vis-assert:0.4.1-beta")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}