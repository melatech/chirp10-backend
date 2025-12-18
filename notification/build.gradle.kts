

plugins {
    id("java-library")
    id("chirp10.spring-boot-service")
    kotlin("plugin.jpa")
}

group = "com.melatech"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
    implementation(projects.common)

    implementation(libs.firebase.admin.sdk)

    //implementation(libs.reactor.netty.http)

    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.mail)
    implementation(libs.spring.boot.starter.amqp)
    implementation(libs.spring.boot.starter.thymeleaf)
    implementation(libs.spring.boot.starter.validation)

    implementation(libs.spring.boot.starter.data.jpa)

    runtimeOnly(libs.postgresql)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

//configurations.all {
//    resolutionStrategy {
//        force(libs.versions.reactor.netty.get())
//    }
//}