package com.sdei.sdeiarchitecture.repository.room

import androidx.room.*
import com.sdei.sdeiarchitecture.model.data.UserInfo

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