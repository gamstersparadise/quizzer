
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.plugin.serialization)
    alias(libs.plugins.kotlin.rpc)
}

group = "com.example"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.swagger)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.openapi)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.host.common)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.config.yaml)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.auth.jwt)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)
    implementation(libs.koin.ktor)
    implementation(libs.ktor.server.rabbitmq)

    // clean up
// Client API
    implementation("org.jetbrains.kotlinx:kotlinx-rpc-krpc-client:0.6.2")
    // Server API
    implementation("org.jetbrains.kotlinx:kotlinx-rpc-krpc-server:0.6.2")
    // Serialization module. Also, protobuf and cbor are provided
    implementation("org.jetbrains.kotlinx:kotlinx-rpc-krpc-serialization-json:0.6.2")

    // Transport implementation for Ktor
    implementation("org.jetbrains.kotlinx:kotlinx-rpc-krpc-ktor-client:0.6.2")
    implementation("org.jetbrains.kotlinx:kotlinx-rpc-krpc-ktor-server:0.6.2")

}
