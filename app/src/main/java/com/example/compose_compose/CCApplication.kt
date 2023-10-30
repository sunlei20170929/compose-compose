package com.example.compose_compose

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.intercept.Interceptor
import coil.request.ImageResult
import coil.size.pxOrElse
import dagger.hilt.android.HiltAndroidApp
import okhttp3.HttpUrl.Companion.toHttpUrl


@HiltAndroidApp
class CCApplication: Application(), ImageLoaderFactory {
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .components {
                add(UnsplashSizingInterceptor)
            }
            .build()
    }
}

/**
 * A Coil [Interceptor] which appends query params to Unsplash urls to request sized images.
 */
object UnsplashSizingInterceptor : Interceptor {
    override suspend fun intercept(chain: Interceptor.Chain): ImageResult {
        val data = chain.request.data
        val widthPx = chain.size.width.pxOrElse { -1 }
        val heightPx = chain.size.height.pxOrElse { -1 }
        if (widthPx > 0 && heightPx > 0 && data is String &&
            data.startsWith("https://images.unsplash.com/photo-")
        ) {
            val url = data.toHttpUrl()
                .newBuilder()
                .addQueryParameter("w", widthPx.toString())
                .addQueryParameter("h", heightPx.toString())
                .build()
            val request = chain.request.newBuilder().data(url).build()
            return chain.proceed(request)
        }
        return chain.proceed(chain.request)
    }
}