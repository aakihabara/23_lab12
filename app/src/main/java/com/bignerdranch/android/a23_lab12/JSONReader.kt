package com.bignerdranch.android.a23_lab12

import android.content.Context
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class JSONReader(private val context: Context) {

    fun readCharacterData(): List<Character>? {
        try {
            val inputStream = context.openFileInput("characters.json")
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            var line: String?

            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }

            val json = JSONObject(stringBuilder.toString())
            val results = json.getJSONArray("results")
            val characterList = mutableListOf<Character>()

            for (i in 0 until results.length()) {
                val character = results.getJSONObject(i)
                val id = character.getInt("id")
                val name = character.getString("name")
                val status = character.getString("status")
                val species = character.getString("species")
                val gender = character.getString("gender")
                val origin = character.getJSONObject("origin").getString("name")
                val location = character.getJSONObject("location").getString("name")
                val image = character.getString("image")
                val characterObj = Character(id, name, status, species, gender, origin, location, image)
                characterList.add(characterObj)
            }

            return characterList

        } catch (e: Exception) {
            return null
        }
    }
}