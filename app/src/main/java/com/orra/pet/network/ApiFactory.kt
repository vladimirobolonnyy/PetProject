package com.orra.pet.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit


interface ApiFactory {

    fun <T> createApi(
        clazz: Class<T>,
        timeout: Timeout = Timeout()
    ): T

    fun createClient(
        additionalInterceptors: List<Interceptor> = emptyList(),
        timeout: Timeout = Timeout()
    ): OkHttpClient
}

class Timeout(
    val connect: Long = 30,
    val read: Long = 30,
    val write: Long = 30,
)


class ApiFactoryImpl(
    private val certificatesProvider: CertificatesProvider,
    private val apiUrl: String = "https://date.nager.at/api/",
) : ApiFactory {
    private val certificates by lazy {
        runCatching { certificatesProvider.getHandshakeCertificates() }.getOrNull()
    }

    private val kotlinJson = Json {
        ignoreUnknownKeys = true
    }

    override fun <T> createApi(clazz: Class<T>, timeout: Timeout): T {
        val contentType = "application/json".toMediaType()

        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(createClient(emptyList(), timeout))
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(kotlinJson.asConverterFactory(contentType))
            .build()
            .create(clazz)
    }

    override fun createClient(
        additionalInterceptors: List<Interceptor>,
        timeout: Timeout
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(timeout.connect, TimeUnit.SECONDS)
            .readTimeout(timeout.read, TimeUnit.SECONDS)
            .writeTimeout(timeout.write, TimeUnit.SECONDS)

        certificates?.let {
            client.sslSocketFactory(it.sslSocketFactory(), it.trustManager)
        }

        additionalInterceptors.forEach { client.interceptors().add(it) }

        return client.build()
    }

}