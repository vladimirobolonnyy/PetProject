package com.orra.pet.network

import com.orra.pet.presentation.Application

object NetworkModule {

    private val certificateProvider: CertificatesProvider by lazy {
        RawFileCertificatesProvider(Application.context)
    }
    val converter = ResponseConverter()

    val testApi by lazy {
        val factory = ApiFactoryImpl(certificateProvider, "https://date.nager.at/api/")
        factory.createApi(Api::class.java)
    }

}