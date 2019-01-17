package com.sdei.sdeiarchitecture

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import com.sdei.sdeiarchitecture.databinding.ActivityNextBinding
import com.sdei.sdeiarchitecture.fragment.HomeFragment
import com.sdei.sdeiarchitecture.helper.openFragment

class NextActivity : BaseActivity(){

    private lateinit var binding: ActivityNextBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_next)
        bindData()
        openHomePage()
    }

    private fun bindData() {

        configureToolbar()

        val packageInfo = packageManager.getPackageInfo(packageName, 0)
        val version = getString(R.string.version) + ": " + packageInfo.versionName

        val navHeaderView = binding.navView.getHeaderView(0)
        navHeaderView.findViewById<TextView>(R.id.app_version_tv).text = version

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            binding.drawerLayout.closeDrawers()
            // Add code here to update the UI based on the item selected
            // For example, swap UI fragments here
            true
        }

        setTabItems()

    }

    private fun configureToolbar() {
        setSupportActionBar(binding.myToolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        setupDrawerToggle()
    }

    private fun setupDrawerToggle() {
        val mDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.myToolbar,
            0,
            0
        )
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState()
    }

    private fun setTabItems() {

        binding.tabsTl.removeAllTabs()
        val tabItemsDrawable = resources.obtainTypedArray(R.array.home_tab_items_drawable)
        val tabItemsName = resources.obtainTypedArray(R.array.home_tab_items_name)
        for (i in 0 until tabItemsDrawable.length()) {
            // Add Tab
            val tab = binding.tabsTl.newTab()
            tab.setCustomView(R.layout.tab)
            tab.text = tabItemsName.getText(i)
            val id = tabItemsDrawable.getResourceId(i, -1)
            val drawable = AppCompatResources.getDrawable(this, id)
            tab.icon = drawable
            binding.tabsTl.addTab(tab)
        }
        tabItemsName.recycle()
        tabItemsDrawable.recycle()

    }

    private fun openHomePage() {
        openFragment(R.id.dashboard_fragment_container, HomeFragment())
    }

}