package android.sdei.com.basicapp

import android.content.res.Configuration
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

import android.support.v4.content.res.ResourcesCompat
import android.view.View
import android.widget.TextView

/**
 * Created by parmil.sharma on 08/05/18.
 */
open class BaseActivity :  AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



    fun setUpToolBar(toolbar: Toolbar, toolbarTitle: TextView, title: String,showBackButton: Boolean) {
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowHomeEnabled(showBackButton)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(showBackButton)
        toolbarTitle.setText(title)
        if (showBackButton){
            toolbar.setNavigationIcon(R.drawable.ic_menu_gallery)
    }
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish();
        })
    }



}

