package cmt.recipeapplication.model

data class RecipeData(
    val id : Int,
    val name : String,
    val ingredients : List<String>,
    val image: String,
    val instructions: List<String>,
    val tags: List<String>

)
