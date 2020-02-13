package com.szabgab.findalike

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.szabgab.findalike.databinding.ActivityReportMissingTitlesBinding

class ReportMissingTitles : AppCompatActivity() {

    private lateinit var binding: ActivityReportMissingTitlesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_report_missing_titles)


//        binding.sendMissingTitleButton.setOnClickListener {
//             binding.missingTitleUrlText.text // TODO send to the API
//        }
    }
}
