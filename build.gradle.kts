plugins {
    kotlin("jvm") version "1.7.0"
    id("org.jetbrains.kotlin.plugin.spring") version "1.7.0" // New plugin
}

group = "com.infinum.course"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    // New dependencies
    implementation("org.springframework:spring-context:5.3.20")
    implementation("junit:junit:4.13.1")
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("org.springframework:spring-test:5.3.20")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation("io.mockk:mockk:1.12.4")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
