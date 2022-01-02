package com.example.android.nasaapp.ui.mars

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.nasaapp.R
import com.example.android.nasaapp.databinding.ItemMarsListBinding
import com.example.android.nasaapp.repository.mars_api.MarsPhotoDetails

class MarsPhotosAdapter(private val itemClickListener: MarsHomeWorkFragment.OnItemViewClickListener) :
    RecyclerView.Adapter<MarsPhotosAdapter.MarsPhotosListViewHolder>() {

    private var marsPhotoData: List<MarsPhotoDetails> = listOf()
    private lateinit var binding: ItemMarsListBinding

    @SuppressLint("NotifyDataSetChanged")
    fun setMarsPhoto(data: ArrayList<MarsPhotoDetails>?) {
        if (data != null) marsPhotoData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotosListViewHolder {
        binding = ItemMarsListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MarsPhotosListViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MarsPhotosListViewHolder, position: Int) {
        holder.bind(marsPhotoData[position])
    }

    override fun getItemCount() = marsPhotoData.size

    inner class MarsPhotosListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(marsPhoto: MarsPhotoDetails) = with(binding) {
            marsImage.load(marsPhoto.imgSrc) {
                error(R.drawable.ic_load_error_vector)
                placeholder(R.drawable.ic_no_photo_vector)
            }
            root.setOnClickListener { itemClickListener.onItemViewClick(marsPhoto) }
        }
    }
}