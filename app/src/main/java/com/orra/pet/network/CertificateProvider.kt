package com.orra.pet.network

import android.content.Context
import androidx.annotation.RawRes
import com.orra.pet.R
import okhttp3.tls.HandshakeCertificates
import okhttp3.tls.decodeCertificatePem

class RawFileCertificatesProvider(
    private val context: Context
) : CertificatesProvider {

    override fun getHandshakeCertificates(): HandshakeCertificates {
        return HandshakeCertificates.Builder()
            .addPlatformTrustedCertificates()
            .build()
    }

    private fun HandshakeCertificates.Builder.addCertFromRaw(@RawRes id: Int): HandshakeCertificates.Builder {
        val cert = context.resources.openRawResource(id).bufferedReader().use { it.readText() }
        return addTrustedCertificate(cert.decodeCertificatePem())
    }

}

interface CertificatesProvider {
    fun getHandshakeCertificates(): HandshakeCertificates?
}