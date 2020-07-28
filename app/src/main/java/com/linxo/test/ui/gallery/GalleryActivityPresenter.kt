package com.linxo.test.ui.gallery

import android.util.Log
import com.linxo.test.app.DataManager
import com.linxo.test.ui.base.BaseActivityPresenter
import com.linxo.test.ui.album.AlbumActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class GalleryActivityPresenter : BaseActivityPresenter(), IGalleryActivityMvpPresenter {

    private lateinit var mvpView : IGalleryActivityMvpView

    override fun init(activity: GalleryActivity) {
        super.init(activity)
        mvpView = activity
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        mvpView.showLoading()
        disposableManager.add(Observable.fromCallable {
            DataManager.instance!!.pictures[activity.intent.getIntExtra(
                AlbumActivity.GALLERY_SELECTED_ITEM_ID, 0)].urlList
        }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                mvpView.hideLoading()
                mvpView.refreshGallery(result)
            }) { throwable ->
                mvpView.hideLoading()
                Log.e("GALLERY_LOAD_DATA", throwable.message!!)
            })
    }
}