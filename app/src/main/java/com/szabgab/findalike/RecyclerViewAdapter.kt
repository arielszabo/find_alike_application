package com.szabgab.findalike

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val suggestedTitlesDataList: ArrayList<TitleData>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(suggestedTitlesDataList[position], this)
    }

//    override fun getItemId(position: Int): Long {
//        return super.getItemId(position)
//        return suggestedTitlesDataList[position].imdbID // TODO maybe hash this to a long?
//    }

    override fun getItemCount() = suggestedTitlesDataList.size

    class ViewHolder private constructor (view: View): RecyclerView.ViewHolder(view) {
        val expandableLayout: ConstraintLayout = itemView.findViewById(R.id.expandableLayout)

        val movieTitleTextView: TextView = itemView.findViewById(R.id.movie_title)
        val posterImageView: ImageView = itemView.findViewById(R.id.posterImageView)
        val markAsSeenCheckBox: CheckBox = itemView.findViewById(R.id.markAsSeenCheckBox)
        val plotTextView: TextView = itemView.findViewById(R.id.plot_text)
        val ratingValueText: TextView = itemView.findViewById(R.id.rating_value_text)
        val posterTitleAndCheckbox: LinearLayout = itemView.findViewById(R.id.poster_title_and_checkbox)


        fun bindItems(titleData : TitleData, adapter: RecyclerViewAdapter){
            movieTitleTextView.text = titleData.title
            posterImageView.setImageBitmap(titleData.posterImage)
            markAsSeenCheckBox.isChecked = titleData.isSeen
            plotTextView.text = titleData.plot
            ratingValueText.text = "9.9" // todo get from titleData

            posterTitleAndCheckbox.setOnClickListener {
                titleData.isLayoutExpanded = !titleData.isLayoutExpanded
                adapter.notifyItemChanged(adapterPosition)
            }

            markAsSeenCheckBox.setOnClickListener {
                val checkBoxStatus = markAsSeenCheckBox.isChecked
                titleData.isSeen = checkBoxStatus // todo send to DB
                // markAsSeenCheckBox.isChecked = checkBoxStatus
                adapter.notifyItemChanged(adapterPosition)

            }

            if (titleData.isLayoutExpanded) {
                expandableLayout.visibility = View.VISIBLE
            } else {
                expandableLayout.visibility = View.GONE
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.title_item, parent, false)
                return ViewHolder(view)
            }
        }
    }
}