package com.linxo.test.utils

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import com.linxo.test.R

class ProgressBarHandler(activity: Activity, parentView: ViewGroup?) {

    private var mProgressBar: ProgressBar?

    fun show() {
        if (mProgressBar != null) {
            mProgressBar!!.isIndeterminate = true
            mProgressBar!!.visibility = View.VISIBLE
        }
    }

    fun hide() {
        if (mProgressBar != null) {
            mProgressBar!!.visibility = View.GONE
        }
    }

    init {

        mProgressBar = parentView!!.findViewById(R.id.progress_bar_indicator)
        if (mProgressBar == null) {
            mProgressBar = ProgressBar(activity, null, android.R.attr.progressBarStyleHorizontal)
            mProgressBar!!.id = R.id.progress_bar_indicator
            mProgressBar!!.isIndeterminate = true
            val progressParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT)
            progressParams.addRule(RelativeLayout.ALIGN_PARENT_TOP)
            progressParams.topMargin = activity.resources.getDimensionPixelSize(
                    R.dimen.progress_bar_handler_margin_top)
            val rl = RelativeLayout(activity)
            rl.addView(mProgressBar, progressParams)
            val params = CoordinatorLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT)
            params.behavior = AppBarLayout.ScrollingViewBehavior()
            parentView.addView(rl, params)
            hide()
        }
    }
}