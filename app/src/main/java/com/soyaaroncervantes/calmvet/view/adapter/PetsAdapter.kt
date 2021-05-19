package com.soyaaroncervantes.calmvet.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.storage.FirebaseStorage
import com.soyaaroncervantes.calmvet.R
import com.soyaaroncervantes.calmvet.models.pets.Animal

class PetsAdapter(options: FirestoreRecyclerOptions<Animal>) : FirestoreRecyclerAdapter<Animal, PetViewHolder>(options) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder =
    PetViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_pet, parent, false))

  override fun onBindViewHolder(holder: PetViewHolder, position: Int, model: Animal) {
    holder.name.text = model.name
    holder.description.text = model.description
    holder.animal.text = model.animal

    val storage = FirebaseStorage.getInstance()
    val storageRef = storage.getReferenceFromUrl("gs://calmvet-project.appspot.com");
    val imageReference = storageRef.child("/images/users/${model.createdBy}/pets/${model.id}.jpg")

  }

  companion object {
    private const val TAG = "[Pets Adapter]"
  }

}