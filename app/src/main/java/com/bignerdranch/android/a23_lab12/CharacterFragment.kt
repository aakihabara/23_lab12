package com.bignerdranch.android.a23_lab12

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide


class CharacterFragment : Fragment()  {


    private lateinit var name: TextView
    private lateinit var status: TextView
    private lateinit var species: TextView
    private lateinit var gender: TextView
    private lateinit var origin: TextView
    private lateinit var location: TextView
    private lateinit var imageField: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_character, container, false)
        name = view.findViewById(R.id.name_text) as TextView
        status = view.findViewById(R.id.status_text) as TextView
        species = view.findViewById(R.id.species_text) as TextView
        gender = view.findViewById(R.id.gender_text) as TextView
        origin = view.findViewById(R.id.origin_text) as TextView
        location = view.findViewById(R.id.location_text) as TextView
        imageField = view.findViewById(R.id.image_character) as ImageView

        val cName = arguments?.getString("charName")

        val character = charactersDatas?.find {
            it.name.equals(cName, ignoreCase = true)
        }

        name.text = character?.name
        status.text = character?.status
        species.text = character?.species
        gender.text = character?.gender
        origin.text = character?.origin
        location.text = character?.location
        Glide.with(this).load(character?.image).into(imageField)

        return view
    }
}