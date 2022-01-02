package com.example.android.nasaapp.ui.mars.mars_photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.android.nasaapp.R
import com.example.android.nasaapp.databinding.FragmentDetailsPhotoBinding
import com.example.android.nasaapp.repository.mars_api.MarsPhotoDetails

class DetailsPhotoFragment : Fragment() {

    private var _binding: FragmentDetailsPhotoBinding? = null
    private val binding: FragmentDetailsPhotoBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<MarsPhotoDetails>(BUNDLE_EXTRA)?.let {
            with(binding) {
                loadImage(it.imgSrc)
            }
        }
    }

    private fun loadImage(url: String?) {
        binding.marsImageDetail.load(url) {
            lifecycle(this@DetailsPhotoFragment)
            error(R.drawable.ic_load_error_vector)
            placeholder(R.drawable.ic_no_photo_vector)
        }
    }

    companion object {

        const val BUNDLE_EXTRA = "photos"

        fun newInstance(bundle: Bundle): DetailsPhotoFragment {
            val fragment = DetailsPhotoFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}