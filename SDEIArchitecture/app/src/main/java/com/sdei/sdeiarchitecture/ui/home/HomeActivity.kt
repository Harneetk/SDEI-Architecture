package com.sdei.sdeiarchitecture.ui.home

import android.content.Context
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.sdei.sdeiarchitecture.R
import com.sdei.sdeiarchitecture.databinding.ActivityHomeBinding
import com.sdei.sdeiarchitecture.model.SideMenu
import com.sdei.sdeiarchitecture.model.Toolbar
import com.sdei.sdeiarchitecture.utils.base.BaseActivity
import com.sdei.sdeiarchitecture.utils.base.BaseVM
import com.sdei.sdeiarchitecture.utils.common.recyclerviewbase.RecyclerBindingList
import com.sdei.sdeiarchitecture.utils.common.recyclerviewbase.RecyclerCallback


/**
 * Created by Vishal Sharma on 2019-12-06.
 */
class HomeActivity : BaseActivity<ActivityHomeBinding, BaseVM>(), RecyclerCallback {
    override val binding: ActivityHomeBinding
        get() = setUpBinding()
    override val layoutId: Int
        get() = R.layout.activity_home
    override var viewModel: BaseVM
        get() = setUpVM(this, BaseVM(application))
        set(value) {}
    override val context: Context
        get() = this

    private val bindList = RecyclerBindingList<SideMenu>()
    private var mDrawerToggle: ActionBarDrawerToggle? = null
    private val toolbar = Toolbar()

    override fun bindData() {
        setUpToolbar()
        setUpSideMenu()
    }

    /**
     * Function - Set Up Toolbar ...
     */
    private fun setUpToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setHomeButtonEnabled(true)
        setupDrawerToggle()
        binding.toolbarModel = toolbar
    }

    /**
     *  SetUp Side Menu items ...
     */
    private fun setUpSideMenu() {
        val list = ArrayList<SideMenu>()
        val menuArray = resources.getStringArray(R.array.menu_array)
        for (i in menuArray.indices) {
            list.add(SideMenu(menuArray[i]))
        }
        bindList.itemsList = list
        binding.list = bindList
        binding.click = this
    }

    override fun initListeners() {

    }

    /**
     *  SetUp Drawer Toggle ...
     */
    private fun setupDrawerToggle() {
        mDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.app_name,
            R.string.app_name
        )
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle?.syncState()
    }

    /**
     *  Show Fragment on Menu List Click ...
     */
    private fun displayView(position: Int) {
        var fragment: Fragment? = null

        when (position) {
            0 -> fragment = HomeFragment()
        }

        fragment?.let {
            supportFragmentManager
                .beginTransaction()
                .replace(binding.dashboardContainer.id, it)
                .commit()
            toolbar.title = bindList.itemsList[position].title
            binding.drawerLayout.closeDrawer(binding.leftDrawer)
        }
    }

    override fun onChildItemClick(view: View?, parentIndex: Int, childIndex: Int) {
    }

    override fun itemAction(type: String?, position: Int) {

    }

    override fun onItemClick(view: View?, position: Int) {
        displayView(position)
    }
}