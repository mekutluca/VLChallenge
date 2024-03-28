package dev.kutluca.vlchallenge.util.network

import dev.kutluca.vlchallenge.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HttpApiFactory {
    /**
     * Build OkHttp client.
     *
     * @param baseUrl - base url of requests.
     * @param httpInterceptors - interceptor for HTTP requests.
     */
    inline fun <reified T> buildApi(
        baseUrl: String,
        httpInterceptors: List<Interceptor>,
    ): T {
        val apiBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)

        val okHttpClient = OkHttpClient().newBuilder().apply {

            httpInterceptors.forEach { addInterceptor(it) }
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
                addInterceptor(interceptor)
            }

            val timeout = 30L
            connectTimeout(timeout, TimeUnit.SECONDS)
            writeTimeout(timeout, TimeUnit.SECONDS)
            readTimeout(timeout, TimeUnit.SECONDS)
        }.build()

        return apiBuilder
            .client(okHttpClient)
            .build()
            .create(T::class.java)
    }
}