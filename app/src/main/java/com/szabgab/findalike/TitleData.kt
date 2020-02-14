package com.szabgab.findalike
import android.graphics.Bitmap

data class TitleData(var title: String,
                     var director: String,
                     var plot: String,
                     var year: Int,
                     var posterImage: Bitmap, // todo change this maybe ?
                     var imdbID: String,
                     var imdbLink: String,
                     var isSeen: Boolean = false,
                     var isLayoutExpanded: Boolean = false)