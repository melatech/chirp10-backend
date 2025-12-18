pluginManagement {
	includeBuild("build-logic")
	repositories {
		maven { url = uri("https://repo.spring.io/snapshot") }
		gradlePluginPortal()
	}
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "chirp10"

include("app")

include("user")
include("chat")
include("notification")
include("common")