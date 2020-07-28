package com.linxo.test.ui.album.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.linxo.test.R

class FolderDetailHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.iv_picture)
    lateinit var ivPicture: ImageView

    @BindView(R.id.tv_title)
    lateinit var tvTitle: TextView

    @BindView(R.id.tv_author)
    lateinit var tvAuthor: TextView

    init {
        ButterKnife.bind(this, itemView)
    }
}