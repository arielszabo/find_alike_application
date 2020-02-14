package com.szabgab.findalike

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.szabgab.findalike.databinding.ActivitySearchResultsBinding

class SearchResults : AppCompatActivity() {

    // TODO onSaveInstanceState to fix device rotation data lose

    private lateinit var binding: ActivitySearchResultsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_results)

        binding.recyclerview.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,
            false)

        val suggestedTitles = ArrayList<TitleData>()


        val chosenTitle = intent.getStringExtra(CHOSEN_TITLE)?.toString()


        for (i in 0..150) {
            val year = 2_000 + i
            val suggestedTitleData = TitleData("$chosenTitle was chosen",
                "director name",
                year,
                "Some long long plot to describe the movie",
                BitmapFactory.decodeResource(resources, R.drawable.example_icon),
                "imdbid",
                "http://imdb.com/ttsomething")
            suggestedTitles.add(suggestedTitleData)

        }

        val adapter = RecyclerViewAdapter(suggestedTitles)
        binding.recyclerview.adapter = adapter
    }
}
