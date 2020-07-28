package com.linxo.test.ui.gallery

import android.content.Context
import android.content.Intent
import androidx.annotation.LayoutRes
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.linxo.test.R
import com.linxo.test.ui.base.BaseActivity
import com.linxo.test.ui.base.BaseActivityPresenter
import com.linxo.test.ui.album.AlbumActivity
import com.linxo.test.ui.gallery.adapter.GalleryAdapter

class GalleryActivity : BaseActivity(), IGalleryActivityMvpView {

    @BindView(R.id.rv_gallery)
    lateinit var rvGallery: RecyclerView

    private lateinit var galleryAdapter: GalleryAdapter
    private lateinit var pictures : ArrayList<String>

    @get:LayoutRes
    override val layoutResourceId: Int
        get() = R.layout.activity_gallery

    override fun instantiatePresenter(): BaseActivityPresenter {
        return GalleryActivityPresenter()
    }

    override fun setUp() {
        super.setUp()
        setUpRecyclerView()
        (presenter as GalleryActivityPresenter).init(this@GalleryActivity)
    }

    private fun setUpRecyclerView() {
        ViewCompat.setNestedScrollingEnabled(rvGallery, false)
        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(applicationContext, AlbumActivity.GALLERY_SPAN_COUNT)
        rvGallery.layoutManager = layoutManager
        rvGallery.itemAnimator = DefaultItemAnimator()

        galleryAdapter = GalleryAdapter()
        rvGallery.adapter = galleryAdapter
    }

    override fun refreshGallery(pictures: ArrayList<String>) {
        this.pictures = pictures
        setUpValues()
    }

    /**
     * Add values to adapter
     */
    private fun setUpValues() {
        val hasData = pictures.size > 0
        if (hasData) {
            galleryAdapter.setData(pictures)
        }
    }

    companion object {

        fun getIntent(context: Context?, galleryId : Int): Intent {
            val intent = Intent(context, GalleryActivity::class.java)
            intent.putExtra(AlbumActivity.GALLERY_SELECTED_ITEM_ID, galleryId)
            return intent
        }
    }
}