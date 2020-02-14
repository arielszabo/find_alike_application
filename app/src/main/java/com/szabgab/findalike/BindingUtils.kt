package com.szabgab.findalike

import android.graphics.BitmapFactory
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter


@BindingAdapter("movieTitle")
fun TextView.setTitleText(item: TitleData?) {
    item?.let {
        text = item.title
    }
}

@BindingAdapter("markAsSeenCheckBox")
fun CheckBox.setMarkAsSeenCheckBox(item: TitleData?) {
    item?.let {
        isChecked = item.isSeen
    }
}

@BindingAdapter("plotText")
fun TextView.setPlotText(item: TitleData?) {
    item?.let {
        text = item.plot
    }
}

@BindingAdapter("ratingValueText")
fun TextView.setRatingValueText(item: TitleData?) {
    item?.let {
        text = "9.9" // todo get from titleData
    }
}

@BindingAdapter("posterImageView")
fun ImageView.setPosterImage(item: TitleData?) {
    item?.let {
        setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable.example_icon))
        // TODO get image from web setImageBitmap(item.posterImage)
    }
}