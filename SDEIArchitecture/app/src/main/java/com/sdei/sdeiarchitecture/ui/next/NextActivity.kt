package com.sdei.sdeiarchitecture.ui.next

import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.sdei.sdeiarchitecture.R
import com.sdei.sdeiarchitecture.databinding.ActivityNextBinding
import com.sdei.sdeiarchitecture.ui.home.HomeFragment
import com.sdei.sdeiarchitecture.utils.base.BaseActivity
import com.sdei.sdeiarchitecture.utils.base.BaseVM
import com.sdei.sdeiarchitecture.utils.openFragment

class NextActivity : BaseActivity() {
    override val layoutId: Int
        get() = R.layout.activity_next
    override val mViewDataBinding: ViewDataBinding
        get() = setUpBinding() as ActivityNextBinding
    override var viewModel: ViewModel
        get() = setUpVM(this@NextActivity, BaseVM())
        set(value) {}
    override val context: Context
        get() = this


    private lateinit var viewBinding: ActivityNextBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewBinding = mViewDataBinding as ActivityNextBinding
        openHomePage()
    }

    override fun bindData() {

        configureToolbar()

        val packageInfo = packageManager.getPackageInfo(packageName, 0)
        val version = getString(R.string.version) + ": " + packageInfo.versionName

        val navHeaderView = viewBinding.navView.getHeaderView(0)
        navHeaderView.findViewById<TextView>(R.id.app_version_tv).text = version

        viewBinding.navView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            viewBinding.drawerLayout.closeDrawers()
            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here
            true
        }

        setTabItems()

    }


    override fun initListeners() {

    }

    private fun configureToolbar() {
        setSupportActionBar(viewBinding.myToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setupDrawerToggle()
    }

    private fun setupDrawerToggle() {
        val mDrawerToggle = ActionBarDrawerToggle(
            this,
            viewBinding.drawerLayout,
            viewBinding.myToolbar,
            0,
            0
        )
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState()
    }

    private fun setTabItems() {

        viewBinding.tabsTl.removeAllTabs()
        val tabItemsDrawable = resources.obtainTypedArray(R.array.home_tab_items_drawable)
        val tabItemsName = resources.obtainTypedArray(R.array.home_tab_items_name)
        for (i in 0 until tabItemsDrawable.length()) {
            // Add Tab
            val tab = viewBinding.tabsTl.newTab()
            tab.setCustomView(R.layout.tab)
            tab.text = tabItemsName.getText(i)
            val id = tabItemsDrawable.getResourceId(i, -1)
            val drawable = AppCompatResources.getDrawable(this, id)
            tab.icon = drawable
            viewBinding.tabsTl.addTab(tab)
        }
        tabItemsName.recycle()
        tabItemsDrawable.recycle()

    }

    private fun openHomePage() {
        openFragment(R.id.dashboard_fragment_container, HomeFragment())
    }

}
