package com.zebas2.marvelapp.marvelapiclient.data.util

import com.zebas2.marvelapp.data.util.RequestParams
import org.junit.Before

class RequestParamsTest {

    private lateinit var requestParams: RequestParams

    @Before
    fun setUp() {
        requestParams = RequestParams()
    }

    private fun getUrlCorrectParams(){
        val ts = requestParams.getTimeStamp()
        val hash = requestParams.getMd5Hash(ts)
    }
}