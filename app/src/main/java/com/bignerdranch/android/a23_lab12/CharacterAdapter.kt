package com.bignerdranch.android.a23_lab12

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CharacterAdapter(private val characterList: List<CharacterList>) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    interface CharacterClickListener {
        fun onCharacterClicked(name: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_character, parent, false)
        return ViewHolder(view)
    }

    private var characterClickListener: CharacterClickListener? = null

    fun setCharacterClickListener(listener: CharacterClickListener) {
        characterClickListener = listener
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characterList[position]
        holder.name.text = "Name: " + character.name
        holder.species.text = "Species: " +  character.species
        holder.gender.text = "Gender: " +  character.gender
        holder.status.text = "Status: " +  character.status

        holder.itemView.setOnClickListener {
            characterClickListener?.onCharacterClicked(character.name)
        }
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.search_name_field)
        val species: TextView = itemView.findViewById(R.id.search_species_field)
        val gender: TextView = itemView.findViewById(R.id.search_gender_field)
        val status: TextView = itemView.findViewById(R.id.search_status_field)
    }
}
