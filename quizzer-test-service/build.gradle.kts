
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
    implementation(libs.koin.ktor)
    implementation(libs.koin.logger.slf4j)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.mongodb.driver.core)
    implementation(libs.mongodb.driver.sync)
    implementation(libs.bson)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.config.yaml)
    implementation("org.litote.kmongo:kmongo:5.2.0")
    implementation("org.litote.kmongo:kmongo-coroutine:5.2.0")
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    testImplementation(libs.ktor.server.test.host)
    testImplementation("io.mockk:mockk:1.13.17")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    implementation(kotlin("test"))

    // rabbit mq
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

tasks.test {
    useJUnitPlatform()
}
