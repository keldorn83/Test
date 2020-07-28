package com.linxo.test.utils

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class DisposableManager {

    private var compositeDisposable: CompositeDisposable? = null

    fun add(disposable: Disposable?) {
        if (disposable != null) {
            getCompositeDisposable().add(disposable)
        }
    }

    fun dispose() {
        if (!getCompositeDisposable().isDisposed) {
            getCompositeDisposable().dispose()
        }
    }

    private fun getCompositeDisposable(): CompositeDisposable {
        if (compositeDisposable == null || compositeDisposable!!.isDisposed) {
            compositeDisposable = CompositeDisposable()
        }
        return compositeDisposable!!
    }
}