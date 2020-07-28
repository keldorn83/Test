package com.linxo.test.ui.album

import android.util.Log
import com.linxo.test.api.Webservice
import com.linxo.test.ui.base.BaseActivityPresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AlbumActivityPresenter : BaseActivityPresenter(), IAlbumActivityMvpPresenter {

    private var selectedFolderId = 0
    private lateinit var mvpView: IAlbumActivityMvpView

    override fun init(activity: AlbumActivity) {
        super.init(activity)
        mvpView = activity
        selectedFolderId = activity.intent.getIntExtra(
            AlbumActivity.GALLERY_SELECTED_ITEM_ID, 0)
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        mvpView.showLoading()
        disposableManager.add(Observable.fromCallable {
            Webservice.instance!!.sync()
        }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mvpView.hideLoading()
                mvpView.refreshFolder()
            }) { throwable ->
                mvpView.hideLoading()
                Log.e("FOLDER_LOAD_DATA", throwable.message!!)
            })
    }
}