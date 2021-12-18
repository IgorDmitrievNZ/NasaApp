package com.example.android.nasaapp.ui.picture

import BottomNavigationDrawerFragment
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.android.nasaapp.R
import com.example.android.nasaapp.databinding.FragmentPictureOfTheDayBinding
import com.example.android.nasaapp.ui.AppState
import com.example.android.nasaapp.ui.MainActivity
import com.google.android.material.bottomappbar.BottomAppBar

class PictureOfTheDayFragment : Fragment() {

    private var isMain = true
    private var url: String? = null
    private var urlHD: String? = null

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding: FragmentPictureOfTheDayBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.chipGroupPicture.setOnCheckedChangeListener { group, _ ->
            when (group.checkedChipId) {

                R.id.chipTodayPhoto -> {
                    Toast.makeText(context, "Normal", Toast.LENGTH_SHORT).show()
                    loadImage(url)

                }
                R.id.chipHDdPhoto -> {
                    Toast.makeText(context, "HD", Toast.LENGTH_SHORT).show()
                    loadImage(urlHD)
                }
            }
        }
        viewModel.getData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.sendServerRequest()

        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }

        setBottomAppBar()
    }

    private fun renderData(state: AppState) = with(binding) {
        when (state) {
            is AppState.Error -> {
                imageView.load(R.drawable.ic_load_error_vector)
                Toast.makeText(context, "No data from server", Toast.LENGTH_SHORT).show()
            }
            is AppState.Loading -> {
                imageView.load(R.drawable.ic_no_photo_vector)
            }
            is AppState.Success -> {
                val pictureOfTheDayResponseData = state.pictureOfTheDayResponseData
                url = pictureOfTheDayResponseData.url
                urlHD = pictureOfTheDayResponseData.hdURL
                val description = pictureOfTheDayResponseData.explanation
                val title = pictureOfTheDayResponseData.title

                includeBottomSheet.bottomSheetDescription.setText(description).toString()
                includeBottomSheet.bottomSheetDescriptionHeader.setText(title).toString()
                loadImage(url)
            }
        }
    }

    private fun loadImage(url: String?) {
        binding.imageView.load(url) {
            lifecycle(this@PictureOfTheDayFragment)
            error(R.drawable.ic_load_error_vector)
            placeholder(R.drawable.ic_no_photo_vector)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_fav -> Toast.makeText(context, "Favourite", Toast.LENGTH_SHORT).show()
            R.id.app_bar_settings -> Toast.makeText(context, "Settings", Toast.LENGTH_SHORT).show()
            android.R.id.home -> BottomNavigationDrawerFragment().show(
                requireActivity().supportFragmentManager,
                ""
            )
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBottomAppBar() {
        val context = activity as MainActivity
        context.setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)

        binding.fab.setOnClickListener {
            if (isMain) {
                isMain = false
                binding.bottomAppBar.navigationIcon = null
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_back_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar_2)
            } else {
                isMain = true
                binding.bottomAppBar.navigationIcon =
                    ContextCompat.getDrawable(context, R.drawable.ic_hamburger_menu_bottom_bar)
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_plus_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
            }
        }

        //back pressed button
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d(TAG, "Fragment back pressed invoked")
                    if (isMain) {
                        System.exit(0)
                    } else {
                        isMain = true
                        binding.bottomAppBar.navigationIcon = ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_hamburger_menu_bottom_bar
                        )
                        binding.bottomAppBar.fabAlignmentMode =
                            BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                        binding.fab.setImageDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_plus_fab
                            )
                        )
                        binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
                    }
                }
            })
    }

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }

}