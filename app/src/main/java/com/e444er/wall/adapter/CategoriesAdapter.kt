package com.e444er.wall.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.e444er.wall.R
import com.e444er.wall.databinding.CategoryRowBinding
import com.e444er.wall.model.Category

class CategoriesAdapter(
    private val category: List<Category>,
//    private val listener: CategoryInteractionListener
) :
    RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {


    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = CategoryRowBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_row, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentCategory = category[position]
        holder.binding.apply {
            categoryTextView.text = currentCategory.categoryName

            Glide.with(holder.itemView.context)
                .load(currentCategory.imageUrl)
                .centerCrop()
                .error(R.color.babyBlue)
                .into(categoryImageView)
        }
        holder.itemView.setOnClickListener {
//            listener.onClickCategory(currentCategory, it)
//        }
        }

    }

    override fun getItemCount() = category.size
}