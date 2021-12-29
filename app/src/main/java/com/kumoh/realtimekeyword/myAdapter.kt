package com.kumoh.realtimekeyword

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView


class myAdapter(val context: Context, private val dataSet: Array<Array<String>>) :
    RecyclerView.Adapter<myAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val cardItem = view
        private val cardViewNum: TextView = view.findViewById(R.id.card_num)
        private val cardViewText: TextView = view.findViewById(R.id.card_text)
        private val contentsLayout: ConstraintLayout = view.findViewById(R.id.contentsLayout)
        private val content1: TextView = view.findViewById(R.id.content1)
        private val content2: TextView = view.findViewById(R.id.content2)
        private val content3: TextView = view.findViewById(R.id.content3)
        private var isExpanded = false

        fun bind (rankNum:Int, rankText: Array<String>, context: Context) {
            cardViewNum.text = rankNum.toString()
            cardViewText.text = rankText[0]
            content1.text = rankText[1]
            content2.text = rankText[3]
            content3.text = rankText[5]

            cardItem.setOnClickListener {
                if (isExpanded) collapse() else expand()
            }

            content1.setOnClickListener {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(rankText[2])))
            }

            content2.setOnClickListener {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(rankText[4])))
            }

            content3.setOnClickListener {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(rankText[6])))
            }

            collapse()
        }

        private fun expand() {
            AnimationUtils.expand(contentsLayout)
            isExpanded = true
        }

        private fun collapse() {
            AnimationUtils.collapse(contentsLayout)
            isExpanded = false
        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_item, viewGroup, false)
        return ViewHolder(view)
    }


    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.bind(position+1, dataSet[position], context)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
