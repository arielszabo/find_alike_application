package com.szabgab.findalike

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ReportMissingTitles : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_missing_titles)


        val missingTitleUrlText: TextView = findViewById(R.id.missing_title_url_text)
        val sendMissingTitleButton: Button = findViewById(R.id.send_missing_title_button)

//        sendMissingTitleButton.setOnClickListener {
//             missingTitleUrlText.text // TODO send to the API
//        }
    }
}
