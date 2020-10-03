object Dependencies {
    const val constarint_layout =
        "androidx.constraintlayout:constraintlayout:${Versions.constarint_layout}"

    const val koin_core = "org.koin:koin-core:${Versions.koin}"
    const val koin_android = "org.koin:koin-android:${Versions.koin}"
    const val koin_view_model = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val koin_test = "org.koin:koin-test:${Versions.koin}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_rxjava_adapter =
        "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val retrofit_moshi_converter =
        "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"

    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshi_kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val moshi_code_gene = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"

    const val rx_android = "io.reactivex.rxjava2:rxandroid:${Versions.rx_android}"
    const val rx_java = "io.reactivex.rxjava2:rxjava:${Versions.rx_java}"

    const val google_maps = "com.google.android.gms:play-services-maps:${Versions.google_maps}"
    const val google_directions = "com.github.jd-alexander:library:${Versions.google_directions}"

    const val core = "androidx.core:core:${Versions.core_ktx}"
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"

    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"

    val room = "androidx.room:room-runtime:${Versions.room}"
    val room_annotation = "androidx.room:room-compiler:${Versions.room}"
    val room_rxjava2_support = "androidx.room:room-rxjava2:${Versions.room}"
}

object Versions {
    const val core_ktx = "1.3.2"
    const val koin = "2.1.6"
    const val retrofit = "2.9.0"
    const val moshi = "1.10.0"
    const val rx_android = "2.1.1"
    const val rx_java = "2.2.19"
    const val google_maps = "17.0.0"
    const val google_directions = "1.1.0"
    const val constarint_layout = "2.0.1"
    const val mockito = "3.2.4"
    const val room ="2.2.5"
}