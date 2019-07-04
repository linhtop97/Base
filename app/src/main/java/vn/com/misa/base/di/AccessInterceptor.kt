package vn.com.misa.base.di

import okhttp3.Interceptor
import okhttp3.Response
import vn.com.misa.base.AppContext
import vn.com.misa.base.commons.Keys

class AccessInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader(Keys.AUTHORIZATION, "${AppContext.getTokenType()} ${AppContext.getAccessToken()}")
        return chain.proceed(requestBuilder.build())
    }
}