package com.farmit.utils.gatewayutility

import android.app.ProgressDialog
import android.content.Context
import android.util.Base64
import java.lang.Exception
import java.security.KeyFactory
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

class RSAUtility {

    fun encrypt(plainText: String, key: String?): String? {
        try {
            val publicKey = KeyFactory.getInstance("RSA").generatePublic(X509EncodedKeySpec(Base64.decode(key, Base64.DEFAULT)))
            val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
            cipher.init(Cipher.ENCRYPT_MODE, publicKey)
            return Base64.encodeToString(
                cipher.doFinal(plainText.toByteArray(charset("UTF-8"))),
                Base64.DEFAULT
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

}