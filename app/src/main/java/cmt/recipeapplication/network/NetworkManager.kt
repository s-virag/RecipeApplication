package cmt.recipeapplication.network

import android.provider.UserDictionary.Words.APP_ID
import cmt.recipeapplication.model.RecipeData
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    private val retrofit: Retrofit
    private val recipeApi: RecipeApi

    private const val SERVICE_URL = "https://dummyjson.com"

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(SERVICE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        recipeApi = retrofit.create(RecipeApi::class.java)
    }

    fun getAll(): Call<List<RecipeData>?>? {
        return recipeApi.getAll()
    }

    fun getOne(id : Int): Call<RecipeData?>?{
        return recipeApi.getOne(id)
    }
}