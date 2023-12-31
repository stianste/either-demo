import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("com.ncorti.ktfmt.gradle") version "0.14.0"
  id("io.spring.dependency-management") version "1.1.3"
  id("org.springframework.boot") version "3.1.4"
  kotlin("jvm") version "1.9.10"
  kotlin("plugin.spring") version "1.9.10"
}

group = "example"

version = "0.0.1-SNAPSHOT"

java { sourceCompatibility = JavaVersion.VERSION_17 }

repositories { mavenCentral() }

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-webflux")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("io.arrow-kt:arrow-core:1.2.1")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("io.netty:netty-resolver-dns-native-macos:4.1.100.Final:osx-aarch_64")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs += "-Xjsr305=strict"
    jvmTarget = "17"
  }
}

configurations { ktfmt { googleStyle() } }

tasks.withType<Test> { useJUnitPlatform() }
