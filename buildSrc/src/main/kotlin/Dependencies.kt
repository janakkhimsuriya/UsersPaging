object Dependencies {
    val junit by lazy { "junit:junit:${Versions.junit}" }
    val junitTest by lazy { "androidx.test.ext:junit:${Versions.junitTest}" }
    val espressoTest by lazy { "androidx.test.espresso:espresso-core:${Versions.espressoTest}" }

    val appcompat by lazy { "androidx.appcompat:appcompat:${Versions.appcompat}" }
    val material by lazy { "com.google.android.material:material:${Versions.material}" }
    val constraint by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraint}" }
    val swipeRefresh by lazy { "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefresh}" }

    val kotlinCore by lazy { "androidx.core:core-ktx:${Versions.kotlinCore}" }
    val kotlinActivity by lazy { "androidx.activity:activity-ktx:${Versions.kotlinActivity}" }
    val kotlinFragment by lazy { "androidx.fragment:fragment-ktx:${Versions.kotlinFragment}" }

    val arguments by lazy { "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}" }
    val fragmentKtx by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}" }
    val uiKtx by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navigation}" }

    val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.daggerHiltAndroid}" }
    val hiltAndroidCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.daggerHiltAndroid}" }

    val hiltViewModel by lazy { "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.daggerHiltViewModel}" }
    val hiltViewModelCompiler by lazy { "androidx.hilt:hilt-compiler:${Versions.daggerHiltViewModel}" }

    val curl by lazy { "com.github.grapesnberries:curlloggerinterceptor:${Versions.curl}" }
    val gson by lazy { "com.google.code.gson:gson:${Versions.gson}" }
    val okhttp by lazy { "com.squareup.okhttp3:okhttp:${Versions.okhttp}" }
    val logging by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}" }

    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
    val convertor by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofit}" }

    val paging by lazy { "androidx.paging:paging-runtime-ktx:${Versions.paging}" }

    val roomKtx by lazy { "androidx.room:room-ktx:${Versions.room}" }
    val roomRuntime by lazy { "androidx.room:room-runtime:${Versions.room}" }
    val roomCompiler by lazy { "androidx.room:room-compiler:${Versions.room}" }
}