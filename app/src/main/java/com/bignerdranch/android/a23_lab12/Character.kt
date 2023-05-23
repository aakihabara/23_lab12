package com.bignerdranch.android.a23_lab12

data class Character(val id: Int,
                     val name: String,
                     val status: String,
                     val species: String,
                     val gender: String,
                     val origin: String,
                     val location: String,
                     var image: String)

var charactersDatas: List<com.bignerdranch.android.a23_lab12.Character>? = null