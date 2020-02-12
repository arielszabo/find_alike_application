package com.szabgab.findalike

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SearchResults : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,
            false)

        val suggestedTitles = ArrayList<TitleData>()


        val chosenTitle = intent.getStringExtra(CHOSEN_TITLE)?.toString()


        for (i in 0..150) {
            val year = 2_000 + i
            val suggestedTitleData = TitleData("{$chosenTitle} was chosen",
                BitmapFactory.decodeResource(resources, R.drawable.example_icon),
                year,
                "Some long long plot to describe the movie",
                "http://imdb.com/ttsomething")
            suggestedTitles.add(suggestedTitleData)

        }

        val adapter = RecyclerViewAdapter(suggestedTitles)
        recyclerView.adapter = adapter
    }
}
