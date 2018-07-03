package android.sdei.com.basicapp.utill

import android.content.Context

import android.content.SharedPreferences


/**
 *  this class is  uses for the storage using the Shared prefference class .
 *  It contain all the methods for the fetching and storing the values as per requirement
 */

class PreferenceConnector
{
    companion object
    {
        val MODE = Context.MODE_PRIVATE
        val PREF_NAME = "DocsInk_V31"
        val ACCESS_TOKEN = "accesstoken"
        val isRemember = "" +
                ""

        fun writeBoolean(context: Context, key: String, value: Boolean) {
            getEditor(context).putBoolean(key, value).commit()
        }

        fun readBoolean(context: Context, key: String, defValue: Boolean): Boolean
        {
            return getPreferences(context)!!.getBoolean(key, defValue)
        }

        fun writeInteger(context: Context, key: String, value: Int) {
            getEditor(context).putInt(key, value).commit()
        }

        fun readInteger(context: Context, key: String, defValue: Int): Int {
            return getPreferences(context)!!.getInt(key, defValue)
        }

        fun writeString(context: Context, key: String, value: String) {
            getEditor(context).putString(key, value).commit()
        }


        fun readString(context: Context, key: String, defValue: String): String? {
            return getPreferences(context)!!.getString(key, defValue)
        }


        fun writeFloat(context: Context, key: String, value: Float) {
            getEditor(context).putFloat(key, value).commit()
        }

        fun readFloat(context: Context, key: String, defValue: Float): Float {
            return getPreferences(context)!!.getFloat(key, defValue)
        }

        fun writeLong(context: Context, key: String, value: Long) {
            getEditor(context).putLong(key, value).commit()
        }

        fun readLong(context: Context, key: String, defValue: Long): Long {
            return getPreferences(context)!!.getLong(key, defValue)
        }

        fun getPreferences(context: Context?): SharedPreferences? {
            return context?.getSharedPreferences(PREF_NAME, MODE)
        }

        fun getEditor(context: Context): SharedPreferences.Editor {
            return getPreferences(context)!!.edit()
        }

        fun clearSharePreferenceKey(context: Context, key: String) {
            getPreferences(context)!!.edit().remove(key).commit()
        }
    }


}