package com.orra.pet.network

import com.orra.pet.presentation.Application

object NetworkModule {

    //todo clear

    private val certificateProvider: CertificatesProvider by lazy { RawFileCertificatesProvider(Application.context) }
    val converter = ResponseConverter()
    val factory: ApiFactory = ApiFactoryImpl(certificateProvider)
    val api by lazy { factory.createApi(Api::class.java) }

}