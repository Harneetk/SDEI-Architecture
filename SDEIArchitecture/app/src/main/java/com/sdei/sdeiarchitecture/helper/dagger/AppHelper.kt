package com.sdei.sdeiarchitecture.helper.dagger

import android.app.Activity
import android.content.Context
import android.provider.Settings
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.sdei.sdeiarchitecture.R
import com.sdei.sdeiarchitecture.callback.SnackbarEventCallback
import com.sdei.sdeiarchitecture.data.User
import com.sdei.sdeiarchitecture.data.UserInfo
import com.sdei.sdeiarchitecture.dialog.ProgressDialog
import com.sdei.sdeiarchitecture.helper.dagger.scope.AppScope
import com.sdei.sdeiarchitecture.room.AppDatabase
import com.sdei.sdeiarchitecture.room.UserInfoDAO

import java.util.regex.Pattern

@AppScope
class AppHelper {

    private var user = User()

    private var progressDialog: ProgressDialog? = null

    fun setUser(user: User) {
        this.user = user
    }

    fun getUser(): User {
        return user
    }

    /**
     * This method  validates entered email according to EMAIL_PATTERN regular expression.
     *
     * @param email email to validate
     * @return true, if email is valid ,else returns false
     */
    fun validEmail(email: String?): Boolean {
        return if (email.isNullOrBlank()) {
            false
        } else {
            val regex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,4})$"
            val pattern = Pattern.compile(regex)
            val matcher = pattern.matcher(email)
            matcher.matches()
        }
    }

    fun showSnackBarToast(message: String, coordinatorLayout: ViewGroup) {

        val snackBar = Snackbar
            .make(coordinatorLayout, message, Snackbar.LENGTH_SHORT)
        val snackBarView = snackBar.view
        val textView = snackBarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.maxLines = 5
        snackBar.show()

    }

    fun showSnackBarWithAction(message: String, view: ViewGroup): Snackbar {

        val snackBar = Snackbar
            .make(view, message, Snackbar.LENGTH_INDEFINITE)
        val snackBarView = snackBar.view
        val textView = snackBarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.maxLines = 5
        snackBar.setAction(view.context.getString(R.string.ok)) {
            snackBar.dismiss()
        }
        snackBar.show()

        return snackBar

    }

    fun showSnackBarWithActionListener(
        message: String,
        actionLabel: String,
        view: ViewGroup,
        callback: SnackbarEventCallback
    ): Snackbar {

        val snackBar = Snackbar
            .make(view, message, Snackbar.LENGTH_INDEFINITE)
        val snackBarView = snackBar.view
        val textView = snackBarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.maxLines = 5
        snackBar.setAction(actionLabel) {
            //snackBar.dismiss()
            callback.onClick()
        }
        snackBar.show()

        return snackBar

    }

    fun hideKeyboard(activity: Activity) {
        try {
            val view = activity.window.currentFocus
            if (view != null && view.windowToken != null) {
                val binder = view.windowToken
                val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binder, 0)
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }

    fun showProgressDialog(context: Context) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(context, context.getString(R.string.please_wait))
            progressDialog!!.window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            )
            progressDialog!!.show()
        }
    }

    fun showProgressDialog(context: Context, message: String) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(context, message)
            progressDialog!!.window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            )
            progressDialog!!.show()
        }
    }

    fun hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog!!.dismiss()
            progressDialog = null
        }
    }

    fun saveUserInfo(context: Context, userInfo: UserInfo) {
        val appDb: AppDatabase? = AppDatabase.getInstance(context)
        if (appDb != null) {
            val userDAO: UserInfoDAO = appDb.userInfo()
            userDAO.insert(userInfo)
        }
    }

    fun getUserInfo(context: Context): UserInfo? {
        val appDb: AppDatabase? = AppDatabase.getInstance(context)
        if (appDb != null) {
            val userDAO: UserInfoDAO = appDb.userInfo()
            return userDAO.getUserInfo()
        }
        return null
    }

    fun deleteUserInfo(context: Context, userInfo: UserInfo?) {
        val appDb: AppDatabase? = AppDatabase.getInstance(context)
        if (appDb != null) {
            val userDAO: UserInfoDAO = appDb.userInfo()
            return userDAO.delete(userInfo!!)
        }
    }

}