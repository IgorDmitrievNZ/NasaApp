package com.example.android.nasaapp.ui.lesson6_recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nasaapp.databinding.ItemRecyclerEarthBinding
import com.example.android.nasaapp.databinding.ItemRecyclerHeaderBinding
import com.example.android.nasaapp.databinding.ItemRecyclerMarsBinding

class RecyclerLesson6Adapter(
    private val data: MutableList<Data>,
    private val callbackListener: MyCallback
) : RecyclerView.Adapter<BaseViewHolder>() {

    fun appendItem() {
        data.add(generateItem())
        notifyItemInserted(itemCount - 1)
    }

    private fun generateItem(): Data {
        return Data(someText = "Mars")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
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
            TYPE_HEADER -> {
                val bindingViewHolder = ItemRecyclerHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HeaderViewHolder(bindingViewHolder.root)
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

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

//        return when (getItemViewType(position)) {
//            TYPE_EARTH -> {
//                (holder as EarthViewHolder).bind(data[position])   //old one version
//            }
//            else -> {
//                (holder as MarsViewHolder).bind(data[position])
//            }
//        }

        holder.bind(data[position])      // new version
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class EarthViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Data) {
            ItemRecyclerEarthBinding.bind(itemView).apply {
                someTextTextView.text = data.someText
                descriptionTextView.text = data.someDescription
                wikiImageView.setOnClickListener {
                    callbackListener.onClick(layoutPosition)
                }
            }
        }
    }

    inner class MarsViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Data) {
            ItemRecyclerMarsBinding.bind(itemView).apply {
                someTextTextView.text = data.someText

                marsImageView.setOnClickListener {
                    callbackListener.onClick(layoutPosition)
                }
                addItemImageView.setOnClickListener {
                    addItemToPosition()
                }
                removeItemImageView.setOnClickListener {
                    removeItem()
                }
            }
        }
        private fun addItemToPosition() {
            data.add(layoutPosition, generateItem())
            notifyItemInserted(layoutPosition)
        }

        private fun removeItem() {
            data.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }
    }

    inner class HeaderViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Data) {
            ItemRecyclerHeaderBinding.bind(itemView).apply {
                header.text = data.someText
                root.setOnClickListener {
                    callbackListener.onClick(layoutPosition)
                }
            }
        }
    }


}