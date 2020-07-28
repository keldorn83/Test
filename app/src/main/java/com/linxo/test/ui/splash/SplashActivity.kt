package com.linxo.test.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.linxo.test.R
import com.linxo.test.ui.album.AlbumActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startFolderDetailActivity()
    }

    private fun startFolderDetailActivity() {
        startActivity(AlbumActivity.getIntent(this@SplashActivity))
        finish()
    }
}