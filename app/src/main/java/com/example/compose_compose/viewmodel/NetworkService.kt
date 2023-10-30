package com.example.compose_compose.viewmodel

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import retrofit2.http.GET

//@Module
//@InstallIn(CCRepository::class)
//object NetworkService {
//    @Provides
//    fun providNetwork(): CCNetworkService {
//        return Retrofit.Builder()
//            .baseUrl("https://www.google.com")
//            .build()
//            .create(CCNetworkService::class.java)
//    }
//}
//
//interface CCNetworkService{
//    @GET("params")
//    fun getFromWeb()
//}