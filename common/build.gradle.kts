plugins {
    id("java-library")
    id("chirp10.kotlin-common")
}

group = "com.melatech"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
    api(libs.kotlin.reflect)
    api(libs.jackson.module.kotlin)
    //api("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.11.4")

    implementation(libs.spring.boot.starter.amqp)
    implementation(libs.spring.boot.starter.security)

    implementation(libs.jwt.api)
    runtimeOnly(libs.jwt.impl)
    runtimeOnly(libs.jwt.jackson)

    //implementation(libs.jackson.datatype.jsr310)
    //implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:3.0.0")
    //implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:3.0.0-rc1")


    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}