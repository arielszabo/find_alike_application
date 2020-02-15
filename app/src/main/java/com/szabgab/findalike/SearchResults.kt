package com.szabgab.findalike

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.szabgab.findalike.databinding.ActivitySearchResultsBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SearchResults : AppCompatActivity() {

    // TODO onSaveInstanceState to fix device rotation data lose

    private lateinit var binding: ActivitySearchResultsBinding

    private var searchedTitle = SearchedTitle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_results)

        binding.recyclerview.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,
            false)

        searchedTitle.title = intent.getStringExtra(CHOSEN_TITLE)?.toString()
        searchedTitle.imdbID = intent.getStringExtra(CHOSEN_IMDB_ID)?.toString()
        binding.searchedTitle = searchedTitle

        val suggestedTitles = getDataToDisplay(applicationContext)

        val adapter = SearchResultViewAdapter(suggestedTitles)
        binding.recyclerview.adapter = adapter
    }

    // TODO move this from here:
    private fun getDataToDisplay(context: Context): ArrayList<TitleData> {
        val jsonFilePath = "counties_sample_data.json" // todo refactor here
        val jsonFileString: String = context.assets.open(jsonFilePath).bufferedReader().readText()

        val gson = Gson()
        val listTitleType = object : TypeToken<List<TitleData>>() {}.type

        val suggestedTitles: ArrayList<TitleData> = gson.fromJson(jsonFileString, listTitleType)

        return suggestedTitles
    }

}
