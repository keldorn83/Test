package com.linxo.test.dto

/**
 * Model class of Album
 */
class AlbumModel {

    var id : Int = 0
    var name : String = ""
    var author : String = ""
    var urlList : ArrayList<String> = ArrayList()

    companion object {

        /**
         * Build Album Model from HTTP request's retrieved data
         */
        fun buildPictureModel(albumData: AlbumData.PictureDataInformation)
                : AlbumModel {

            val pictureModel = AlbumModel()
            pictureModel.id = albumData.id
            pictureModel.name = albumData.name
            pictureModel.author = albumData.author
            pictureModel.urlList = albumData.urlList

            return pictureModel
        }
    }

}