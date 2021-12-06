/**
 * Created by Janak Khimsuriya on 30/11/21.
 */
object BuildPlugins {
    val gradleBuild by lazy { "com.android.tools.build:gradle:${Versions.gradleBuild}" }
    val kotlinBuild by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinBuild}" }
    val hiltBuild by lazy { "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltBuild}" }
}