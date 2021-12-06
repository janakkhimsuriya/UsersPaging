buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://www.jitpack.io")
    }
    dependencies {
        classpath(BuildPlugins.gradleBuild)
        classpath(BuildPlugins.kotlinBuild)
        classpath(BuildPlugins.hiltBuild)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0")
    }
}

task("clean", Delete::class) {
    delete(rootProject.buildDir)
}