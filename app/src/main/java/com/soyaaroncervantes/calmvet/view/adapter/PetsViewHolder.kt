package com.soyaaroncervantes.calmvet.view.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.soyaaroncervantes.calmvet.R

class PetsViewHolder( view: View): RecyclerView.ViewHolder( view )  {
  val petIcon: ImageView = view.findViewById( R.id.petIconImage )
  val petHeader:ImageView = view.findViewById( R.id.petHeaderImage )
  val petName: TextView = view.findViewById( R.id.petName )
  val petBreed: TextView = view.findViewById( R.id.petBreed )
  val petDescription: TextView = view.findViewById( R.id.petDescription )
}