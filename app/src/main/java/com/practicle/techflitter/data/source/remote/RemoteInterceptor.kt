package com.practicle.techflitter.data.source.remote

import com.practicle.techflitter.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class RemoteInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader(RemoteConstants.AUTH_HEADER, "Bearer ${BuildConfig.API_KEY}")
                .build()
        )
    }
}