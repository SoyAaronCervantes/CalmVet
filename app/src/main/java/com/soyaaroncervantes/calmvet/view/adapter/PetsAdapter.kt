package com.soyaaroncervantes.calmvet.view.adapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.models.pets.Animal
import com.soyaaroncervantes.calmvet.modules.GlideApp

class PetsAdapter(options: FirestoreRecyclerOptions<Animal>) : FirestoreRecyclerAdapter<Animal, PetViewHolder>(options) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder =
    PetViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_pet, parent, false))

  override fun onBindViewHolder(holder: PetViewHolder, position: Int, animal: Animal) {
    holder.name.text = animal.name
    holder.description.text = animal.description
    holder.animal.text = animal.animal

    val photo = getPhotoURI( animal.id!! )

    GlideApp
      .with(holder.image.context)
      .load( photo )
      .into(holder.image)

  }

  companion object {
    private const val TAG = "[Pets Adapter]"
  }

  private fun getPhotoURI( id: String ): StorageReference {
    val storage = FirebaseStorage.getInstance()
    return storage.getReferenceFromUrl("gs://calmvet-project.appspot.com/images/pets/$id")
  }

}