package com.example.recyclerviewlesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewlesson.databinding.PlantItemBinding

class PlantAdapter : RecyclerView.Adapter<PlantViewHolder>() {
    val plantList = ArrayList<Plant>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.plant_item, parent, false)
        return PlantViewHolder(view)
    }

    override fun getItemCount(): Int = plantList.size

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        holder.bind(plantList[position])
    }

    fun addPlant(plant: Plant) {
        plantList.add(plant)
        notifyDataSetChanged()
    }
}

class PlantViewHolder(item: View) : RecyclerView.ViewHolder(item) {
    val binding = PlantItemBinding.bind(item)
    fun bind(plant: Plant) = with(binding) {
        im.setImageResource(plant.imageId)
        tvTitle.text = plant.title
    }
}