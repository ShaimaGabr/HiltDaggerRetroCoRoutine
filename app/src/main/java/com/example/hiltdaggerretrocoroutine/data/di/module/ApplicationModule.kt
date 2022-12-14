package com.example.hiltdaggerretrocoroutine.data.di.module

import androidx.viewbinding.BuildConfig
import com.example.hiltdaggerretrocoroutine.data.api.ApiHelper
import com.example.hiltdaggerretrocoroutine.data.api.ApiHelperImpl
import com.example.hiltdaggerretrocoroutine.data.api.ApiService
import com.example.hiltdaggerretrocoroutine.utiles.Constants
import com.example.hiltdaggerretrocoroutine.utiles.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideOKHttpClient()=if(BuildConfig.DEBUG){
        val loggingInterceptor=HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }else{
        OkHttpClient.Builder().build() }
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit=
        Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL).client(okHttpClient).build()
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit):ApiService= retrofit.create(ApiService::class.java)
    @Provides
    @Singleton
    fun provideApiHelper(apiHelperImpl: ApiHelperImpl):ApiHelper=apiHelperImpl

}