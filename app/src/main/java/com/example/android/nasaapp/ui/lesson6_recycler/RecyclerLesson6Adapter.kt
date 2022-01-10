package com.example.android.nasaapp.ui.lesson6_recycler

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nasaapp.databinding.ItemRecyclerEarthBinding
import com.example.android.nasaapp.databinding.ItemRecyclerHeaderBinding
import com.example.android.nasaapp.databinding.ItemRecyclerMarsBinding

class RecyclerLesson6Adapter(
    private val data: MutableList<Pair<Data, Boolean>>,
    private val callbackListener: MyCallback,
    private val onStartDragListener: OnStartDragListener
) : RecyclerView.Adapter<BaseViewHolder>(), ItemTouchHelperAdapter {

    interface OnStartDragListener {
        fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
    }

    fun setItems(newItems: List<Pair<Data, Boolean>>) {
        val result = DiffUtil.calculateDiff(DiffUtilCallback(data, newItems))
        result.dispatchUpdatesTo(this)
        data.clear()
        data.addAll(newItems)
    }

    fun appendItem() {
        data.add(generateItem())
        notifyItemInserted(itemCount - 1)
    }

    private fun generateItem(): Pair<Data, Boolean> {
        return Data((0..9999999).random(), someText = "Mars") to false
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
        return data[position].first.type
    }

//    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
//
////        return when (getItemViewType(position)) {
////            TYPE_EARTH -> {
////                (holder as EarthViewHolder).bind(data[position])   //old one version
////            }
////            else -> {
////                (holder as MarsViewHolder).bind(data[position])
////            }
////        }
//
//        holder.bind(data[position])      // new version
//    }

    /*override fun onBindViewHolder(
       holder: BaseViewHolder,
       position: Int,
       payloads: MutableList<Any>
   ) {
       if (payloads.isEmpty())
           super.onBindViewHolder(holder, position, payloads)
       else {
           val combinedChange =
               createCombinedPayload(payloads as MutableList<Change<Pair<Data, Boolean>>>)
           val oldData = combinedChange.oldData
           val newData = combinedChange.newData
           if (newData.first.someText != oldData.first.someText) {
               ActivityRecyclerItemMarsBinding.bind(holder.itemView).marsTextView.text = newData.first.someText
           }
       }
   }*/


    override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val combinedChange =
                createCombinedPayload(payloads as MutableList<Change<Pair<Data, Boolean>>>)
            val oldData = combinedChange.oldData
            val newData = combinedChange.newData
            Log.d(
                "mylogs", "${(1..9999999).random()} ${
                    newData.first.someText != oldData.first.someText
                }"
            )


            ItemRecyclerMarsBinding.bind(holder.itemView).someTextTextView.text =
                newData.first.someText

        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class EarthViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Pair<Data, Boolean>) {
            ItemRecyclerEarthBinding.bind(itemView).apply {
                someTextTextView.text = data.first.someText
                descriptionTextView.text = data.first.someDescription
                wikiImageView.setOnClickListener {
                    callbackListener.onClick(layoutPosition)
                }
            }
        }
    }

    inner class MarsViewHolder(view: View) : BaseViewHolder(view), ItemTouchHelperViewHolder {
        @SuppressLint("ClickableViewAccessibility")
        override fun bind(data: Pair<Data, Boolean>) {
            ItemRecyclerMarsBinding.bind(itemView).apply {
                someTextTextView.text = data.first.someText

                marsImageView.setOnClickListener {
                    callbackListener.onClick(layoutPosition)
                }
                addItemImageView.setOnClickListener {
                    addItemToPosition()
                }
                removeItemImageView.setOnClickListener {
                    removeItem()
                }
                moveItemDown.setOnClickListener {
                    moveDown()
                }
                moveItemUp.setOnClickListener {
                    moveUp()
                }
                marsDescriptionTextView.visibility = if (data.second) View.VISIBLE else View.GONE

                someTextTextView.setOnClickListener {
                    toggleDescription()
                }

                fun onTouch(motionEvent: MotionEvent): Int {
                    val action = motionEvent.actionMasked;
                    return action
                }

                /** The are performing with the touch event is only visual.
                 * It doesn't affect the actual working of the app.
                 * That is the reason why we used "android.annotation.SuppressLint" to silence the warning **/

                dragHandleImageView.setOnTouchListener { v, event ->
                    Log.d("mylogs", "setOnTouchListener $event")
                    if (onTouch(event) == MotionEvent.ACTION_DOWN) {
                        onStartDragListener.onStartDrag(this@MarsViewHolder)
                    }
                    false
                }
            }
        }


        private fun toggleDescription() {
            data[layoutPosition] = data[layoutPosition].run {
                first to !second
            }
            notifyItemChanged(layoutPosition)
        }

        /**
         * Notice the "it> 1" predicate in the moveUp method. We deliberately leave the first list item
         * untouched because it is a header and should always stay at the top. If the position allows
         * the element to be moved, then we change the data in the array: we delete and place the element
         * again at the desired position. And we call the notifyItemMoved method with arguments:
         * current position, desired position. The animation for moving the item is taken over by the RecyclerView.
         **/

        private fun moveUp() {
            layoutPosition.takeIf { it > 1 }?.also { currentPosition ->
                data.removeAt(currentPosition).apply {
                    data.add(currentPosition - 1, this)
                }
                notifyItemMoved(currentPosition, currentPosition - 1)
            }
        }

        private fun moveDown() {
            layoutPosition.takeIf { it < data.size - 1 }?.also { currentPosition ->
                data.removeAt(currentPosition).apply {
                    data.add(currentPosition + 1, this)
                }
                notifyItemMoved(currentPosition, currentPosition + 1)
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

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.RED)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

    inner class HeaderViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Pair<Data, Boolean>) {
            ItemRecyclerHeaderBinding.bind(itemView).apply {
                header.text = data.first.someText

                root.setOnClickListener {
                    callbackListener.onClick(layoutPosition)
                }
            }
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        data.removeAt(fromPosition).apply {
            data.add(toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

}