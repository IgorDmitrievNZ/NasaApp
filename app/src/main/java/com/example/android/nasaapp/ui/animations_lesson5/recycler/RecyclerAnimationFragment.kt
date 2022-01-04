package com.example.android.nasaapp.ui.animations_lesson5.recycler

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*
import com.example.android.nasaapp.R
import com.example.android.nasaapp.databinding.FragmentRecyclerAnimationsBinding

class RecyclerAnimationFragment : Fragment() {

    private var _binding: FragmentRecyclerAnimationsBinding? = null
    private val binding: FragmentRecyclerAnimationsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerAnimationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.adapter = Adapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    inner class Adapter : RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_animations_recycler, parent, false)
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                val explodeTransition = Explode()
                val button = it as Button
                val fadeTransition = Fade()
                val setTransition = TransitionSet()
                explodeTransition.duration = 5000
                fadeTransition.duration = 5000
                explodeTransition.excludeTarget(button, true)
                explodeTransition.epicenterCallback = object : Transition.EpicenterCallback() {
                    override fun onGetEpicenter(transition: Transition): Rect {
                        val rect = Rect(
                            button.x.toInt(),
                            button.y.toInt(),
                            button.x.toInt(),
                            button.y.toInt()
                        )
                        //val rect = Rect()
                        //button.getGlobalVisibleRect(rect) // почти то же самое
                        return rect
                    }
                }

                setTransition.addTransition(fadeTransition)
                setTransition.addTransition(explodeTransition)
                TransitionManager.beginDelayedTransition(binding.recyclerView, setTransition)
                binding.recyclerView.adapter = null
            }
        }

        override fun getItemCount(): Int {
            return 32
        }

    }
    
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    companion object {
        @JvmStatic
        fun newInstance() = RecyclerAnimationFragment()
    }
}