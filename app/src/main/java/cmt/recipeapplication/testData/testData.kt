package cmt.recipeapplication.testData

import cmt.recipeapplication.model.RecipeData

object testData {
    val temp = RecipeData(
        id = 0,
        name = "Placeholder Recipe",
        ingredients = listOf("Apple", "Cinnamon", "Sugar", "Flour", "Butter"),
        image = "https://images.unsplash.com/photo-1578985545062-69928b1d9587?q=80&w=1989&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        instructions = listOf("Placeholder instruction 1", "Placeholder instruction2" ),
        tags = listOf("cake", "apple")
    )

    val temp2 = RecipeData(
        id = 1,
        name = "Placeholder Recipe2",
        ingredients = listOf("Apple", "Cinnamon", "Sugar", "Flour", "Butter"),
        image = "https://images.unsplash.com/photo-1535141192574-5d4897c12636?q=80&w=1976&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        instructions = listOf("Placeholder instruction 1", "Placeholder instruction2" ),
        tags = listOf("cake", "apple")
    )

    val tempList = listOf(temp,temp2)
}