package com.linxo.test.ui.base

import android.app.Activity
import com.linxo.test.utils.DisposableManager

abstract class BaseActivityPresenter : IBaseActivityMvpPresenter {

    protected var disposableManager: DisposableManager = DisposableManager()
    private lateinit var mvpView : IBaseActivityMvpView
    lateinit var activity : Activity

    init {
        disposableManager = DisposableManager()
    }

    override fun init(activity : BaseActivity) {
        this.activity = activity
        mvpView = activity
    }

    open fun onStart() {}

    open fun onResume() {}

    open fun onPause() {}

    open fun onStop() {
        disposableManager.dispose()
    }

    open fun showLoading() {
        mvpView.showLoading()
    }

    open fun hideLoading() {
        mvpView.hideLoading()
    }
}