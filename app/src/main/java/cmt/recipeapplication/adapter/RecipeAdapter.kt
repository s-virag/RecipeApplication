package cmt.recipeapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cmt.recipeapplication.databinding.ItemRecipeListBinding
import cmt.recipeapplication.model.RecipeData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class RecipeAdapter(private val listener: RecipeItemClickListener) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private val items = mutableListOf<RecipeData>()
    private lateinit var context : Context
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): RecipeViewHolder {
        context = parent.context
        return RecipeViewHolder(ItemRecipeListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecipeAdapter.RecipeViewHolder, position: Int) {
        val current = items[position]
        holder.itemView.setOnClickListener{
            listener.onItemSelected(current)
        }
        holder.binding.tvName.text = current.name
        holder.binding.tvIngredients.text = createIngredientList(position)
        Glide.with(context)
            .load(current.image)
            .transition(DrawableTransitionOptions().crossFade())
            .into(holder.binding.imgIcon)

    }

    fun createIngredientList(position: Int): String {
        val current = items[position]
        var ingredientList = ""
        for(i in current.ingredients){
            ingredientList = ingredientList + i + '\n'
        }
        return ingredientList
    }
    override fun getItemCount(): Int = items.size

    interface RecipeItemClickListener{
        fun onItemSelected(item: RecipeData?)
    }

    inner class RecipeViewHolder(val binding: ItemRecipeListBinding) : RecyclerView.ViewHolder(binding.root){
    }

    fun update(recipeItems: List<RecipeData>?) {
        if (recipeItems != null) {
            items.clear()
            items.addAll(recipeItems)
            notifyDataSetChanged()
        }

    }

}