package zander.dependencies
object Versions{
    const val compileSdk = 29
    const val buildTools = "29.0.3"
    const val minSdk = 21
    const val targetSdk = 29
    const val versionCode = 1
    const val versionName = "1.0.0"

    const val appcompat = "1.1.0"
    const val constraintLayout = "1.1.3"
    const val swipeRefreshLayout = "1.1.0-rc01"
    const val material = "1.2.0-beta01"
    const val recyclerView = "1.1.0"
    const val viewPager2 = "1.0.0"
    const val lifecycle_extension = "2.2.0"
    const val navigation = "2.2.2"

    const val kotlin = "1.3.72"
    const val core_ktx = "1.3.0"
    const val coroutines = "1.3.0"
    const val coroutines_android = "1.3.5"
    const val livedata_ktx = "2.2.0"
    const val viewmodel_ktx = "2.2.0"

    const val retrofit = "2.7.1"
    const val retrofit_converter_gson = "2.6.2"
    const val okhttp_logging_interceptor = "4.0.0"
    const val persistentCookieJar = "v1.0.1"
    const val glide = "4.11.0"
    const val glide_compiler = "4.11.0"
    const val cardView = "1.0.0"
    const val verticalTabLayout = "1.2.5"
    const val flowLayout = "1.1.2"
    const val baseRecyclerViewAdapterHelper = "2.9.50"
    const val banner = "1.4.10"
    const val circleImageview = "2.2.0"
    const val material_dialogs = "3.3.0"

    const val daggerHilt = "2.28-alpha"
    const val hilt = "1.0.0-alpha01"
    const val koin = "2.0.1"
}

object Deps{
    // androidx
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val lifecycle_extension = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle_extension}"
    const val navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    // kotlin
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_android}"
    const val livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.livedata_ktx}"
    const val viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewmodel_ktx}"

    // network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_converter_gson}"
    const val okhttp_logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_logging_interceptor}"
    const val persistentCookieJar = "com.github.franmontiel:PersistentCookieJar:${Versions.persistentCookieJar}"

    //dagger
    const val dagger_hilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
    const val kapt_dagger_hilt = "com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}"
    const val hilt_lifecycle_viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt}"
    const val kapt_hilt = "androidx.hilt:hilt-compiler:${Versions.hilt}"

    //koin
    const val koin_android = "org.koin:koin-android:${Versions.koin}"
    const val koin_androidx_scope = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val koin_androidx_viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"

    //third
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide_compiler}"
    const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
    const val verticalTabLayout = "q.rorbin:VerticalTabLayout:${Versions.verticalTabLayout}"
    const val flowLayout = "com.hyman:flowlayout-lib:${Versions.flowLayout}"
    const val circleimageview = "de.hdodenhof:circleimageview:${Versions.circleImageview}"
    const val banner = "com.youth.banner:banner:${Versions.banner}"
    const val baseRecyclerViewAdapterHelper = "com.github.CymChad:BaseRecyclerViewAdapterHelper:${Versions.baseRecyclerViewAdapterHelper}"
    const val material_dialogs_core = "com.afollestad.material-dialogs:core:${Versions.material_dialogs}"

}