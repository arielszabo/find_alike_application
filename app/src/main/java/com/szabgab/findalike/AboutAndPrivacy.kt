package com.szabgab.findalike

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.szabgab.findalike.databinding.ActivityAboutAndPrivacyBinding
import com.szabgab.findalike.databinding.ActivityMainBinding

class AboutAndPrivacy : AppCompatActivity() {

    private lateinit var binding: ActivityAboutAndPrivacyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_about_and_privacy)

    }
}
