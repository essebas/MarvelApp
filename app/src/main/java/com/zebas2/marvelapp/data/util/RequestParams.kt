package com.zebas2.marvelapp.data.util

import android.security.keystore.KeyProperties
import com.zebas2.marvelapp.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp


class RequestParams {

    fun getTimeStamp(): String {
        return Timestamp(System.currentTimeMillis()).time.toString()
    }

    fun getMd5Hash(ts: String): String {
        val md = MessageDigest.getInstance(KeyProperties.DIGEST_MD5)
        val convert = ts + BuildConfig.API_PRIVATE_KEY + BuildConfig.API_PUBLIC_KEY
        return BigInteger(1, md.digest(convert.toByteArray())).toString(16).padStart(32, '0')
    }

}