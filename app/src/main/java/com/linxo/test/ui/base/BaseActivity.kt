package com.linxo.test.ui.base

import android.os.Bundle
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.Unbinder
import com.linxo.test.utils.ProgressBarHandler

abstract class BaseActivity : AppCompatActivity(), IBaseActivityMvpView {

    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    protected var presenter: BaseActivityPresenter? = null
    private var unbinder: Unbinder? = null
    protected var progressBarHandler: ProgressBarHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResourceId)
        presenter = instantiatePresenter()
        setUp()
    }

    abstract fun instantiatePresenter() : BaseActivityPresenter

    protected open fun setUp() {
        unbinder = ButterKnife.bind(this)
        setUpProgressBar()
    }

    private fun setUpProgressBar() {
        progressBarHandler = ProgressBarHandler(this,
            window.decorView.findViewById(android.R.id.content) as ViewGroup
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        unbinder!!.unbind()
    }

    override fun onStart() {
        super.onStart()
        presenter!!.onStart()
    }

    override fun onResume() {
        super.onResume()
        presenter!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter!!.onPause()
    }

    override fun onStop() {
        super.onStop()
        presenter!!.onStop()
    }

    override fun showLoading() {
        progressBarHandler!!.show()
    }

    override fun hideLoading() {
        progressBarHandler!!.hide()
    }
}