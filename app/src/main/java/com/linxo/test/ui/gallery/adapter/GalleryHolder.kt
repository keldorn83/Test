package com.linxo.test.ui.gallery.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.linxo.test.R

class GalleryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.iv_picture)
    lateinit var ivPicture: ImageView

    init {
        ButterKnife.bind(this, itemView)
    }
}