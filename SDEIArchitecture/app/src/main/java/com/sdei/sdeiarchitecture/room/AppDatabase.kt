package com.sdei.sdeiarchitecture.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.sdei.sdeiarchitecture.R
import com.sdei.sdeiarchitecture.data.UserInfo

@Database(entities = arrayOf(UserInfo::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userInfo(): UserInfoDAO

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    context.getString(R.string.db_name)
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }

    }

}