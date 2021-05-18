package com.soyaaroncervantes.calmvet.view.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.databinding.ListItemBinding
import com.soyaaroncervantes.calmvet.models.pets.Animal

class PetViewHolder( view: View ): RecyclerView.ViewHolder( view ) {
  val context: Context = view.context
  var image: ImageView = view.findViewById( R.id.petImage )
  var name: TextView = view.findViewById( R.id.petName )
  var animal: TextView = view.findViewById( R.id.petAnimal )
  var description: TextView = view.findViewById( R.id.petDescription )
}