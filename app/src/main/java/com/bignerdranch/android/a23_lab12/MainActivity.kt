package com.bignerdranch.android.a23_lab12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

interface JsonDownloadCallback {
    fun onDownloadComplete()
}

class MainActivity : AppCompatActivity(), JsonDownloadCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val jsonDownloader = JSONDownloader(this@MainActivity, this)
        jsonDownloader.downloadJsonFile("https://rickandmortyapi.com/api/character")
    }

    override fun onDownloadComplete() {
        val jsonReader = JSONReader(this@MainActivity)
        charactersDatas = jsonReader.readCharacterData()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SearchFragment.newInstance())
            .commitNow()
    }

}