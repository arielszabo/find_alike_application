package com.szabgab.findalike

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val suggestedTitlesDataList: ArrayList<TitleData>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.title_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bindItems(suggestedTitlesDataList[position], this)
    }

    override fun getItemCount(): Int {
        return suggestedTitlesDataList.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bindItems(titleData : TitleData, adapter: RecyclerViewAdapter){
            val expandableLayout: ConstraintLayout = itemView.findViewById(R.id.expandableLayout)

            val movieTitleTextView: TextView = itemView.findViewById(R.id.movie_title)
            val posterImageView: ImageView = itemView.findViewById(R.id.posterImageView)
            val markAsSeenCheckBox: CheckBox = itemView.findViewById(R.id.markAsSeenCheckBox)
            val plotTextView: TextView = itemView.findViewById(R.id.plot_text)
            val ratingValueText: TextView = itemView.findViewById(R.id.rating_value_text)
            val posterTitleAndCheckbox: LinearLayout = itemView.findViewById(R.id.poster_title_and_checkbox)

            movieTitleTextView.text = titleData.title
            posterImageView.setImageBitmap(titleData.posterImage)
            markAsSeenCheckBox.isClickable = titleData.isClickable
            markAsSeenCheckBox.setChecked(titleData.isChecked)
            plotTextView.text = titleData.plot
            ratingValueText.text = "9.9" // todo get from titleData

            posterTitleAndCheckbox.setOnClickListener {
                titleData.isExpanded = !titleData.isExpanded
                adapter.notifyItemChanged(adapterPosition)
            }

            markAsSeenCheckBox.setOnClickListener {
                val checkBoxStatus = markAsSeenCheckBox.isChecked
                titleData.isChecked = checkBoxStatus // todo send to DB
                // markAsSeenCheckBox.isChecked = checkBoxStatus
                adapter.notifyItemChanged(adapterPosition)

            }

            if (titleData.isExpanded) {
                expandableLayout.visibility = View.VISIBLE
            } else {
                expandableLayout.visibility = View.GONE
            }
        }

    }
}