package com.szabgab.findalike
import android.graphics.Bitmap

data class TitleData(var title: String,
                     var posterImage: Bitmap, // todo change this maybe ?
                     var year: Int,
                     var plot: String,
                     var imdbLink: String,
                     var isClickable: Boolean = true,
                     var isChecked: Boolean = false,
                     var isExpanded: Boolean = false)
