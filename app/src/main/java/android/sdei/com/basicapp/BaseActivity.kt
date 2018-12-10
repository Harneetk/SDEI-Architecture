package android.sdei.com.basicapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import androidx.core.content.res.ResourcesCompat
import android.view.View
import android.widget.TextView

/**
 *
 *  this class uses by the other activities for inherit the common properties
 */

open class BaseActivity :  AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



    fun setUpToolBar(toolbar: Toolbar, toolbarTitle: TextView, title: String,showBackButton: Boolean) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(showBackButton)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(showBackButton)
        toolbarTitle.setText(title)

        if (showBackButton)
        {
            toolbar.setNavigationIcon(R.drawable.ic_menu_gallery)
    }
        toolbar.setNavigationOnClickListener{
            finish();
        }
    }



}

