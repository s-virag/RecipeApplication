package cmt.recipeapplication.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cmt.recipeapplication.databinding.ActivityDetailsBinding
import cmt.recipeapplication.model.RecipeData
import cmt.recipeapplication.testData.testData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private var recipeId: String? = null
    private lateinit var recipe : RecipeData

    companion object {
        private const val TAG = "DetailsActivity"
        const val EXTRA_RECIPE_ID = "extra.recipe_id"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recipeId = intent.getStringExtra(EXTRA_RECIPE_ID)
        //api fetch
        val idx = recipeId?.toInt()
        recipe = if(idx != null) {
            testData.tempList[idx]
        }else{
            testData.temp
        }
        binding.tvDetailsName.text = recipe.name
        binding.tvDetailsTags.text = createTags()
        binding.tvDetailsIngredientsList.text = createIngredientsList()
        binding.tvDetailsInstructionsList.text = createInstructionList()
        Glide.with(this)
            .load(recipe.image)
            .transition(DrawableTransitionOptions().crossFade())
            .into(binding.ivDetails)

    }

    fun createTags(): String{
        var tagList = ""
        for(t in recipe.tags){
            tagList = tagList + '#' + t + ' '
        }
        return tagList
    }

    fun createIngredientsList():String{
        var ingredientList = ""
        for(i in recipe.ingredients){
            ingredientList = ingredientList + i + '\n'
        }
        return ingredientList
    }

    fun createInstructionList(): String{
        var instructionList = ""
        for(i in recipe.instructions){
            instructionList = instructionList + i + '\n'
        }
        return instructionList
    }

}