package com.linxo.test.app

import android.util.Log
import com.google.gson.GsonBuilder
import com.linxo.test.api.Webservice
import com.linxo.test.dto.AlbumData
import com.linxo.test.dto.AlbumModel
import org.json.JSONObject

class DataManager {

    var pictures = ArrayList<AlbumModel>()

    /**
     * Creating a model object from the response
     */
    fun onSyncResponse(response : String) {
        try {
            pictures.clear()
            val jSonObject = JSONObject(response)
            val array = convertJsonResponse(jSonObject)
            for (picture in array) {
                val pictureModel = AlbumModel.buildPictureModel(picture)
                pictures.add(pictureModel)
            }
            pictures.sortBy { it.name }
        } catch (e : Exception) {
            Log.e("SYNC",e.message!!)
        }
    }

    /**
     * Convert Json object to list, using AlbumData
     */
    private fun convertJsonResponse(sync : JSONObject) : Array<AlbumData.PictureDataInformation> {
        val data = sync.getString(Webservice.SYNC_ID)
        val gSon = GsonBuilder().create()

        return gSon.fromJson(data, Array<AlbumData.PictureDataInformation>::class.java)
    }

    companion object {

        var instance: DataManager? = null
            get() {
                if (null == field) {
                    field = DataManager()
                }
                return field
            }
    }
}