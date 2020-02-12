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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.title_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the data_list
    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bindItems(suggestedTitlesDataList[position], this)
    }

    //this method is giving the size of the data_list
    override fun getItemCount(): Int {
        return suggestedTitlesDataList.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bindItems(data : TitleData, adapter: RecyclerViewAdapter){
            val expandableLayout: ConstraintLayout = itemView.findViewById(R.id.expandableLayout)

            val movieTitleTextView: TextView = itemView.findViewById(R.id.movie_title)
            val posterImageView: ImageView = itemView.findViewById(R.id.posterImageView)
            val markAsSeenCheckBox: CheckBox = itemView.findViewById(R.id.markAsSeenCheckBox)
            val plotTextView: TextView = itemView.findViewById(R.id.plot_text)
            val posterTitleAndCheckbox: LinearLayout = itemView.findViewById(R.id.poster_title_and_checkbox)

            movieTitleTextView.text = data.title
            posterImageView.setImageBitmap(data.posterImage)
            markAsSeenCheckBox.isClickable = data.isClickable
            markAsSeenCheckBox.setChecked(data.isChecked)
            plotTextView.text = data.plot


            posterTitleAndCheckbox.setOnClickListener {
                data.isExpanded = !data.isExpanded
                adapter.notifyItemChanged(adapterPosition)
            }

            if (data.isExpanded) {
                expandableLayout.visibility = View.VISIBLE
            } else {
                expandableLayout.visibility = View.GONE
            }
        }

    }
}