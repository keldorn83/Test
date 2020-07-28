package com.linxo.test.dto

import com.google.gson.annotations.SerializedName

class AlbumData {

    companion object {
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_AUTHOR = "author"
        const val COLUMN_URL_LIST = "url_list"
    }

    data class PictureDataInformation (
        @SerializedName(COLUMN_ID) val id: Int,
        @SerializedName(COLUMN_NAME) val name: String,
        @SerializedName(COLUMN_AUTHOR) val author: String,
        @SerializedName(COLUMN_URL_LIST) val urlList: ArrayList<String>
    )
}