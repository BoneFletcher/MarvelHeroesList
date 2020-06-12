package com.sdv.attractgrouptesttask.di

import com.sdv.attractgrouptesttask.data.config.Constant.BASE_URL
import com.sdv.attractgrouptesttask.data.profile.ProfileHttpApi
import com.sdv.attractgrouptesttask.data.profile.repository.ProfileRepository
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val apiModule = module {
    fun provideProfileHttpApi(retrofit: Retrofit): ProfileHttpApi {
        return retrofit.create(ProfileHttpApi::class.java)
    }

    single { provideProfileHttpApi(get()) }
}

val netModule = module {
//    fun provideCache(application: Application): Cache {
//        val cacheSize = 10 * 1024 * 1024
//        return Cache(application.cacheDir, cacheSize.toLong())
//    }

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        return okHttpClientBuilder.build()
    }

    fun provideGson(): Moshi {
        return Moshi.Builder().build()
    }

    fun provideRetrofit(factory: Moshi, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(factory))
            .client(client)
            .build()
    }

    single { provideHttpClient() }
    single { provideGson() }
    single { provideRetrofit(get(), get()) }
}

val repositoryModule = module {
    fun provideProfileRepository(api: ProfileHttpApi): ProfileRepository {
        return ProfileRepository(api)
    }
    single { provideProfileRepository(get()) }
}
