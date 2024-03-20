package cmt.recipeapplication

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import cmt.recipeapplication.adapter.RecipeAdapter
import cmt.recipeapplication.databinding.ActivityMainBinding
import cmt.recipeapplication.details.DetailsActivity
import cmt.recipeapplication.model.RecipeData
import cmt.recipeapplication.network.NetworkManager
import cmt.recipeapplication.testData.testData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread
import kotlin.jvm.internal.TypeReference


class MainActivity : AppCompatActivity(), RecipeAdapter.RecipeItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
    }

    private fun initRecyclerView(){
        adapter = RecipeAdapter(this)
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = adapter

        loadItems()

    }

    override fun onItemSelected(item: RecipeData?) {
        Log.d("Data", item?.id.toString() )
        val showDetailsIntent = Intent()
        showDetailsIntent.setClass(this@MainActivity, DetailsActivity::class.java)
        showDetailsIntent.putExtra(DetailsActivity.EXTRA_RECIPE_ID, item?.id.toString())
        startActivity(showDetailsIntent)
    }


    private fun loadItems(){
        thread {
            NetworkManager.getAll()?.enqueue(object : Callback<List<RecipeData>?> {
                override fun onResponse(
                    call: Call<List<RecipeData>?>,
                    response: Response<List<RecipeData>?>
                ) {
                    Log.d(TAG, "onResponse: " + response.code())
                    if (response.isSuccessful) {
                        runOnUiThread{
                            displayRecipeList(response.body())
                        }

                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Error: " + response.message(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(
                    call: Call<List<RecipeData>?>,
                    throwable: Throwable
                    ) {
                        throwable.printStackTrace()
                        Toast.makeText(this@MainActivity, "Network request error occured, check LOG", Toast.LENGTH_LONG).show()
                        runOnUiThread {
                            displayRecipeList(testData.tempList)
                        }
                    }


            })
        }
    }

    private fun displayRecipeList(items: List<RecipeData>?){
        adapter.update(items)
    }



}