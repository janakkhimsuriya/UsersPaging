package com.practicle.techflitter.di.modules

import android.content.Context
import com.grapesnberries.curllogger.CurlLoggerInterceptor
import com.practicle.techflitter.BuildConfig
import com.practicle.techflitter.data.source.remote.RemoteInterceptor
import com.practicle.techflitter.data.source.remote.RemoteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteModule {
    @Provides
    @Singleton
    fun provideRetrofit(
        gsonFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonFactory)
            .client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        remoteInterceptor: RemoteInterceptor,
        curlLoggerInterceptor: CurlLoggerInterceptor
    ): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC

        return OkHttpClient.Builder().cache(Cache(context.cacheDir, (5 * 1024 * 1024).toLong()))
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(remoteInterceptor)
            .addInterceptor(curlLoggerInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConvertorFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideCurlInterceptor(): CurlLoggerInterceptor = CurlLoggerInterceptor()

    @Provides
    @Singleton
    fun provideRemoteService(retrofit: Retrofit): RemoteService {
        return retrofit.create(RemoteService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteInterceptor(): RemoteInterceptor = RemoteInterceptor()
}