package com.example.lesson1_mvp

import android.os.Bundle
import android.os.PersistableBundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.lesson1_mvp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var vb: ActivityMainBinding

    val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)

        
        vb.btnCounter1.setOnClickListener {
            presenter.counterClick(1)
        }

        vb.btnCounter2.setOnClickListener{
            presenter.counterClick(2)
        }

        vb.btnCounter3.setOnClickListener{
            presenter.counterClick(3)
        }
    }

    override fun setButtonText1(text: String) {
        vb.btnCounter1.text = text
    }

    override fun setButtonText2(text: String) {
        vb.btnCounter2.text = text
    }

    override fun setButtonText3(text: String) {
        vb.btnCounter3.text = text
    }


}