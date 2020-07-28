package com.linxo.test.ui.gallery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.linxo.test.R
import com.squareup.picasso.Picasso

/**
 * Adapter class of Gallery Activity
 */
class GalleryAdapter : RecyclerView.Adapter<GalleryHolder>() {

    private var items: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.cell_gallery, parent, false)
        return GalleryHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: GalleryHolder, position: Int) {
        val item: String = items[position]

        Picasso.get()
            .load(item)
            .into(holder.ivPicture)
    }

    /**
     * Set data from request
     */
    fun setData(items: List<String>) {
        if (this.items.count() > 0) {
            this.items.clear()
            this.items.addAll(items)
        } else {
            this.items.addAll(items)
        }
        notifyDataSetChanged()
    }
}