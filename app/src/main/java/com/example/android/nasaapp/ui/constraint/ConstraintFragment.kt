package com.example.android.nasaapp.ui.constraint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android.nasaapp.databinding.FragmentConstraintBinding

class ConstraintFragment : Fragment() {

    private var _binding: FragmentConstraintBinding? = null
    private val binding: FragmentConstraintBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConstraintBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
//        binding.group1.visibility = View.GONE
//        binding.group2.visibility = View.INVISIBLE

        //аналогично
        /*binding.materialButton1.visibility  =View.GONE
        binding.materialButton2.visibility  =View.GONE
        binding.materialButton11.visibility  =View.VISIBLE
        binding.materialButton22.visibility  =View.VISIBLE*/

//        binding.materialButton3.setOnClickListener {
//            binding.group1.visibility = View.VISIBLE
//            binding.group2.visibility = View.VISIBLE
//        }
        
                //fragment_constraint_group_barrier
//        binding.materialButton33.setOnClickListener {
//            binding.group1.visibility = View.GONE
//            binding.group2.visibility = View.INVISIBLE
//        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = ConstraintFragment()
    }
}