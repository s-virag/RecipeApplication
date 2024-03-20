package cmt.recipeapplication.network

import cmt.recipeapplication.model.RecipeData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {
    @GET("/recipes")
    fun getAll(): Call<List<RecipeData>?>?

    @GET("/recipes/{id}")
    fun getOne(@Path("id") id : Int): Call<RecipeData?>
}