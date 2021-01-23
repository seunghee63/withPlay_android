package com.song2.thenaun.ui.detailed

import android.os.Bundle
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import com.song2.thenaun.R
import com.song2.thenaun.base.BaseFragment
import com.song2.thenaun.databinding.FragmentDetailedBinding
import com.song2.thenaun.ui.MainActivity


class DetailedFragment : BaseFragment<FragmentDetailedBinding>() {
    override val layoutResId: Int
        get() = R.layout.fragment_detailed

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMotionLayout()
    }

    override fun initObserver() {}

    private fun initMotionLayout() {

        binding.videoMotionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
            }

            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {
                (activity as MainActivity).also {
                    it.binding.motionLayout.progress = kotlin.math.abs(progress)
                }
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
            }
        })
    }

}