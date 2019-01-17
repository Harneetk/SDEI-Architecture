package com.sdei.sdeiarchitecture.room

import androidx.room.*
import com.sdei.sdeiarchitecture.data.UserInfo

@Dao
interface UserInfoDAO {

    @Insert
    fun insert(info: UserInfo)

    @Delete
    fun delete(info: UserInfo)

    @Update
    fun update(info: UserInfo)

    @Query("Select * from UserInfo")
    fun getUserInfo(): UserInfo

}