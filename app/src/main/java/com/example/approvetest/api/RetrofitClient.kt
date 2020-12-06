package com.example.approvetest.api

import com.example.approvetest.utils.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private val baseURL = "http://reward4.ukwest.cloudapp.azure.com/"
    private var retrofit:Retrofit? = null

    val instance:Retrofit
    get(){
        if(retrofit == null){
            var client = OkHttpClient.Builder().addInterceptor(object:Interceptor{
                override fun intercept(chain: Interceptor.Chain): Response {
                    var newRequest:Request = chain.request().newBuilder()
                        .addHeader("Authorization","Bearer ${Constants.BEARER_TOKEN}")
                        .build()
                    return chain.proceed(newRequest)
                }
            }).build()

            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    val api
    get() = instance.create(Api::class.java)


}