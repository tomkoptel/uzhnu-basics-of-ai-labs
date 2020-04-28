plugins {
    id("org.jetbrains.kotlin.jvm")
    `java-library`
}

repositories {
    jcenter()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // We use coroutines for parallel computing lab
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    val spek_version = "2.0.10"
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spek_version")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spek_version")
    // spek requires kotlin-reflect, can be omitted if already in the classpath
    testRuntimeOnly("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.amshove.kluent:kluent:1.60")
}

tasks.withType<Test> {
    useJUnitPlatform {
        includeEngines("spek2")
    }
}
