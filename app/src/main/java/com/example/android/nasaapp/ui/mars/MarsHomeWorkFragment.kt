package com.example.android.nasaapp.ui.mars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.nasaapp.databinding.FragmentMarsHomeWorkBinding

class MarsHomeWorkFragment : Fragment() {

    private var adapter: MarsPhotosAdapter? = null

    private val viewModel: MarsHomeWorkViewModel by lazy {
        ViewModelProvider(this).get(MarsHomeWorkViewModel::class.java)
    }

    private var _binding: FragmentMarsHomeWorkBinding? = null
    private val binding: FragmentMarsHomeWorkBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMarsHomeWorkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.sendServerRequest()
        marsFragmentRecyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun renderData(state: MarsHomeWorkViewModel.State) = with(binding) {
        when (state) {
            MarsHomeWorkViewModel.State.ERROR -> {
                progressBar.isVisible = true
                Toast.makeText(requireContext(), "NO DATA FROM SERVER", Toast.LENGTH_SHORT).show()
            }
            MarsHomeWorkViewModel.State.LOADING -> {
                progressBar.isVisible = true
            }
            MarsHomeWorkViewModel.State.SUCCESS -> {
                progressBar.isVisible = false
                adapter = MarsPhotosAdapter().apply {
                    setMarsPhoto(viewModel.getMarsPhotos())
                }
                marsFragmentRecyclerView.adapter = adapter
                marsFragmentRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MarsHomeWorkFragment()
    }
}
