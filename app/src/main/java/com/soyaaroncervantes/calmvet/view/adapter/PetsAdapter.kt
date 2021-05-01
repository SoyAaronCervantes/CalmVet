package com.soyaaroncervantes.calmvet.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.models.pets.Animal

class PetsAdapter( private val petsListener: PetsListener ): RecyclerView.Adapter< PetsViewHolder >() {
  private val pets = arrayListOf<Animal>()

  override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): PetsViewHolder =
    PetsViewHolder( LayoutInflater.from( parent.context ).inflate( R.layout.fragment_pet, parent, false ) )

  override fun getItemCount(): Int = pets.size

  override fun onBindViewHolder( petsViewHolder: PetsViewHolder, position: Int ) {
    val pet = pets[ position ]

    petsViewHolder.petName.text = pet.name
    petsViewHolder.petBreed.text = pet.breed
    petsViewHolder.petDescription.text = pet.description

    Glide.with( petsViewHolder.itemView.context )
      .load( pet.iconPhoto )
      .apply( RequestOptions.circleCropTransform() )
      .into( petsViewHolder.petIcon )

    Glide.with( petsViewHolder.itemView.context )
      .load( pet.headerPhoto )
      .apply( RequestOptions.centerCropTransform() )
      .into( petsViewHolder.petHeader )

    petsViewHolder.itemView.setOnClickListener {
      petsListener.onPetClick( pet, position )
    }
  }

  fun updateData( value: List<Animal> ) {
    pets.clear()
    pets.addAll( value )
    notifyDataSetChanged()
  }


}