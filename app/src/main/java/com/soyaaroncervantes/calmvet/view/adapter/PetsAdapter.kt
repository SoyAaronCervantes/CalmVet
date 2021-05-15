package com.soyaaroncervantes.calmvet.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.models.pets.Animal

class PetsAdapter: RecyclerView.Adapter<PetViewHolder>() {
  private val animals = mutableListOf<Animal>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder =
    PetViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_pet, parent, false))

  override fun getItemCount(): Int = animals.size

  override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
    val animal = animals[ position ]
    holder.petAge.text = animal.age
    holder.petDescription.text = animal.description
    holder.petName.text = animal.name
    Glide
      .with( holder.itemView.context )
      .load( animal.headerPhoto )
      .into( holder.petPhoto )
  }

  fun updateList( list: List<Animal> )  {
    animals.clear()
    animals.addAll( list )
    notifyDataSetChanged()
  }

}