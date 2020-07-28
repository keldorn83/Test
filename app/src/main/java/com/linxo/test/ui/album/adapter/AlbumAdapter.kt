package com.linxo.test.ui.album.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.linxo.test.R
import com.linxo.test.dto.AlbumModel
import com.squareup.picasso.Picasso

/**
 * Adapter class of Album Activity
 */
class AlbumAdapter : RecyclerView.Adapter<AlbumHolder>() {

    private lateinit var callback: Callback
    private var items: ArrayList<AlbumModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_folder_detail, parent, false)
        return AlbumHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        val item: AlbumModel = items[position]

        if (item.urlList.size > 0) {
            Picasso.get()
                .load(item.urlList[0])
                .into(holder.ivPicture)
        }

        holder.tvTitle.text = item.name
        holder.tvAuthor.text = item.author

        holder.itemView.setOnClickListener {
            callback.onAlbumItemClick(position)
        }
    }

    /**
     * Set data from request
     */
    fun setData(items: List<AlbumModel>) {
        if (this.items.count() > 0) {
            this.items.clear()
            this.items.addAll(items)
        } else {
            this.items.addAll(items)
        }
        notifyDataSetChanged()
    }

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    interface Callback {
        fun onAlbumItemClick(position: Int)
    }
}