buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath(Deps.android_gradle_plugin)
        classpath(Deps.SqlDelight.gradle)
        classpath(Deps.cocoapodsext)
        classpath("org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}")

        classpath(kotlin("gradle-plugin", Versions.kotlin))
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven(url = "https://kotlin.bintray.com/kotlinx")
        maven(url = "https://dl.bintray.com/touchlabpublic/kotlin")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
