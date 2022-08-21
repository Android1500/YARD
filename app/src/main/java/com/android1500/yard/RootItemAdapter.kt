package com.android1500.yard

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RootItemAdapter(private val context: Context) : RecyclerView.Adapter<RootItemAdapter.RootItemVH>() {

    private val items: MutableList<RootItemResult> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun update(results: List<RootItemResult>) {
        items.clear()
        items.addAll(results)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RootItemVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rootcheck_view, parent, false)
        return RootItemVH(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RootItemVH, position: Int) = holder.bind(items[position])

    fun add(rootItemResult: RootItemResult) {
        items.add(rootItemResult)
        notifyItemInserted(items.size - 1)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    inner class RootItemVH(containerView: View) : RecyclerView.ViewHolder(containerView){
        private val text: TextView = containerView.findViewById(R.id.rootItemText)
        private val resultIcon: ImageView = containerView.findViewById(R.id.rootItemResultIcon)

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(item: RootItemResult) {
            text.text = item.text
             if (item.result){
                 resultIcon.setImageDrawable(context.resources.getDrawable(R.drawable.ic_outline_cancel_24))
            } else {
                 resultIcon.setImageDrawable(context.resources.getDrawable(R.drawable.ic_baseline_check_circle_outline_24))

            }


        }
    }


}

