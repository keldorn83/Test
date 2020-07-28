package com.linxo.test.ui.gallery

import com.linxo.test.ui.base.IBaseActivityMvpView

interface IGalleryActivityMvpView : IBaseActivityMvpView {

    fun refreshGallery(pictures: ArrayList<String>)
}