package vn.com.misa.base.utilities

import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import okhttp3.OkHttpClient
import vn.com.base.libraries.applications.ExtApplication
import vn.com.base.libraries.utilities.StringUtility
import java.util.concurrent.TimeUnit

object PicasoUtility {
    fun get(): Picasso {
        // okhttpclient
        val client = OkHttpClient.Builder()
                .retryOnConnectionFailure(ServiceConfig.RETRY_POLICY)
        client.connectTimeout(ServiceConfig.REQUEST_TIMEOUT_LONG.toLong(), TimeUnit.SECONDS)
                .readTimeout(ServiceConfig.REQUEST_TIMEOUT_LONG.toLong(), TimeUnit.SECONDS)
                .writeTimeout(ServiceConfig.REQUEST_TIMEOUT_LONG.toLong(), TimeUnit.SECONDS)

        // trust all certificate
      //  ApiModule.addTrustCertificate(client)

        // picaso
        val builder = Picasso.Builder(ExtApplication.instance)
        val okHttpDownloader = OkHttp3Downloader(client.build())
        builder.downloader(okHttpDownloader)
        return builder.build()
    }

    fun load(url: String?): RequestCreator {
        return if (StringUtility.isNullOrEmpty(url)) {
            get().load(null as? String?)
        } else {
            get().load(url)
        }
    }
}