package com.linxo.test.ui.album

import android.content.Context
import android.content.Intent
import androidx.annotation.LayoutRes
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.linxo.test.R
import com.linxo.test.app.DataManager
import com.linxo.test.dto.AlbumModel
import com.linxo.test.ui.base.BaseActivity
import com.linxo.test.ui.base.BaseActivityPresenter
import com.linxo.test.ui.album.adapter.FolderDetailAdapter
import com.linxo.test.ui.gallery.GalleryActivity

class AlbumActivity :
    BaseActivity(),
    IAlbumActivityMvpView,
    FolderDetailAdapter.Callback {

    @BindView(R.id.rv_folder)
    lateinit var rvFolder: RecyclerView

    private lateinit var folderDetailAdapter: FolderDetailAdapter
    private lateinit var albums : ArrayList<AlbumModel>

    @get:LayoutRes
    override val layoutResourceId: Int
        get() = R.layout.activity_folder_detail

    override fun instantiatePresenter(): BaseActivityPresenter {
        return AlbumActivityPresenter()
    }

    override fun setUp() {
        super.setUp()
        setUpRecyclerView()
        (presenter as AlbumActivityPresenter).init(this@AlbumActivity)
    }

    private fun setUpRecyclerView() {
        ViewCompat.setNestedScrollingEnabled(rvFolder, false)
        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(applicationContext, ALBUM_SPAN_COUNT)
        rvFolder.layoutManager = layoutManager
        rvFolder.itemAnimator = DefaultItemAnimator()

        folderDetailAdapter = FolderDetailAdapter()
        rvFolder.adapter = folderDetailAdapter
        folderDetailAdapter.setCallback(this@AlbumActivity)
    }

    private fun setUpValues() {
        val hasData = albums.size > 0
        if (hasData) {
            folderDetailAdapter.setData(albums)
        }
    }

    override fun refreshFolder() {
        this.albums = DataManager.instance!!.pictures
        setUpValues()
    }

    override fun onDetailItemClick(position: Int) {
        startActivity(GalleryActivity.getIntent(
            this@AlbumActivity,
            position))
    }

    companion object {

        const val ALBUM_SPAN_COUNT = 1
        const val GALLERY_SPAN_COUNT = 2
        const val GALLERY_SELECTED_ITEM_ID = "GALLERY_SELECTED_ITEM_ID"

        fun getIntent(context: Context?): Intent {
            return Intent(context, AlbumActivity::class.java)
        }
    }
}