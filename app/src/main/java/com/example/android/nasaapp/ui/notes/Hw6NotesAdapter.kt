package com.example.android.nasaapp.ui.notes

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.nasaapp.databinding.ItemHw6NoteBinding
import com.example.android.nasaapp.ui.lesson6_recycler.ItemTouchHelperAdapterInterface
import com.example.android.nasaapp.ui.lesson6_recycler.ItemTouchHelperViewHolder

class Hw6NotesAdapter(
    private val data: MutableList<DataNote>,
    private val clickListener: NoteClickListener
) : RecyclerView.Adapter<BaseNoteViewHolder>(), ItemTouchHelperAdapterInterface {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseNoteViewHolder {
        val bindingViewHolder =
            ItemHw6NoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(bindingViewHolder.root)
    }

    fun appendItem() {
        data.add(generateItem())
        notifyItemInserted(itemCount - 1)
    }

    private fun generateItem(): DataNote {
        return DataNote(noteTitle = "Note")
    }

    override fun onBindViewHolder(holder: BaseNoteViewHolder, position: Int) {

        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }


    inner class NoteViewHolder(view: View) : BaseNoteViewHolder(view), ItemTouchHelperViewHolder {
        override fun bind(data: DataNote) {
            ItemHw6NoteBinding.bind(itemView).apply {
                noteNameTextView.text = data.noteTitle

                //press root
                root.setOnClickListener {
                    noteEditText.visibility = View.VISIBLE
                    saveImageView.visibility = View.VISIBLE
                }

                //press delete image
                removeItemImageView.setOnClickListener {
                    removeItem()
                }

                //press down arrow image
                moveItemDown.setOnClickListener {
                    moveDown()
                }

                //press up arrow image
                moveItemUp.setOnClickListener {
                    moveUp()
                }

                //press save image
                saveImageView.setOnClickListener {
                    noteEditText.visibility = View.GONE
                    saveImageView.visibility = View.GONE
                }

                //press edit image
                editItemImageView.setOnClickListener {
                    noteEditText.visibility = View.VISIBLE
                    saveImageView.visibility = View.VISIBLE
                }


            }

        }

        private fun moveUp() {
            layoutPosition.takeIf { it > 0 }?.also { currentPosition ->
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

        private fun removeItem() {
            data.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.parseColor("#66FF0000"))
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
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