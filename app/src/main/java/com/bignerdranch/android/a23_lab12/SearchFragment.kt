package com.bignerdranch.android.a23_lab12

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SearchFragment : Fragment(), CharacterAdapter.CharacterClickListener {

    private lateinit var searchText: AutoCompleteTextView
    private lateinit var searchButton: AppCompatButton

    companion object {
        fun newInstance() = SearchFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        searchText = view.findViewById(R.id.search_field) as AutoCompleteTextView
        searchButton = view.findViewById(R.id.search_button) as AppCompatButton
        var namesList: Array<String>? = null
        var speciesList: Array<String>? = null
        var genderList: Array<String>? = null
        var statusList: Array<String>? = null
        val dataCharacters = charactersDatas
        if (dataCharacters != null) {
            namesList = dataCharacters.map { it.name }.toTypedArray()
            speciesList = dataCharacters.map { it.species }.toTypedArray()
            genderList = dataCharacters.map { it.gender }.toTypedArray()
            statusList = dataCharacters.map { it.status }.toTypedArray()
        }

        val recyclerView: RecyclerView = view.findViewById(R.id.character_recycler_view)

        if (namesList != null && speciesList != null && genderList != null && statusList != null) {
            val characterList = mutableListOf<CharacterList>()

            for (i in namesList.indices) {
                val name = namesList[i]
                val species = speciesList[i]
                val gender = genderList[i]
                val status = statusList[i]

                val character = CharacterList(name, species, gender, status)
                characterList.add(character)
            }

            val adapterChar = CharacterAdapter(characterList)
            adapterChar.setCharacterClickListener(this)
            recyclerView.adapter = adapterChar
        } else {
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        var adapter: ArrayAdapter<String>? = null
        if (namesList != null) {
            adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, namesList)
            searchText.setAdapter(adapter)
        }

        searchButton.setOnClickListener {
            val inputData = searchText.text.toString()

            val characterFragment = CharacterFragment()
            val bundle = Bundle()
            bundle.putString("charName", inputData)
            characterFragment.arguments = bundle

            val fragmentManager = requireActivity().supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, characterFragment)
                .addToBackStack(null)
                .commit()
        }
        return view
    }

    override fun onCharacterClicked(name: String) {
        val characterFragment = CharacterFragment()
        val bundle = Bundle()
        bundle.putString("charName", name)
        characterFragment.arguments = bundle
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, characterFragment)
            .addToBackStack(null)
            .commit()
    }

}