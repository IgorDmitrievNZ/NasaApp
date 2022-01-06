package com.example.android.nasaapp.ui.lesson6_recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nasaapp.databinding.ItemRecyclerEarthBinding
import com.example.android.nasaapp.databinding.ItemRecyclerMarsBinding

class RecyclerLesson6Adapter(
    private val data: List<Data>,
    private val callbackListener: MyCallback
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //getting binding
        return when (viewType) {
            TYPE_EARTH -> {
                val bindingViewHolder =
                    ItemRecyclerEarthBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                EarthViewHolder(bindingViewHolder.root)
            }
            else -> {
                val bindingViewHolder = ItemRecyclerMarsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                MarsViewHolder(bindingViewHolder.root)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return when (getItemViewType(position)) {
            TYPE_EARTH -> {
                (holder as EarthViewHolder).bind(data[position])
            }
            else -> {
                (holder as MarsViewHolder).bind(data[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class EarthViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Data) {
            ItemRecyclerEarthBinding.bind(itemView).apply {
                someTextTextView.text = data.someText
                descriptionTextView.text = data.someDescription
                wikiImageView.setOnClickListener {
                    callbackListener.onClick(layoutPosition)
                }
            }
        }
    }

    inner class MarsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: Data) {
            ItemRecyclerMarsBinding.bind(itemView).apply {
                someTextTextView.text = data.someText
                marsImageView.setOnClickListener {
                    callbackListener.onClick(layoutPosition)
                }
            }
        }
    }
}