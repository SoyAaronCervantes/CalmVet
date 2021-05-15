package com.soyaaroncervantes.calmvet.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.databinding.ListItemBinding
import com.soyaaroncervantes.calmvet.models.pets.Animal

class PetViewHolder( view: View ): RecyclerView.ViewHolder( view ) {
  var petPhoto: ImageView = view.findViewById( R.id.petImage )
  var petName: TextView = view.findViewById( R.id.petName )
  var petAge: TextView = view.findViewById( R.id.petAge )
  var petDescription: TextView = view.findViewById( R.id.petDescription )
}