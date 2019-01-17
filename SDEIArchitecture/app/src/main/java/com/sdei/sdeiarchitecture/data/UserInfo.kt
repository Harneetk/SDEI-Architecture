package com.sdei.sdeiarchitecture.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.annotation.JsonIgnore

@Entity(tableName = "UserInfo")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class UserInfo {

    @PrimaryKey(autoGenerate = true)
    var _id: Int = 0

    @ColumnInfo(name = "name")
    @JsonProperty("name")
    var name: String = ""

    @ColumnInfo(name = "job")
    @JsonProperty("job")
    var job: String = ""

    @ColumnInfo(name = "id")
    @JsonProperty("id")
    var id: String = ""

    @ColumnInfo(name = "createdAt")
    @JsonProperty("createdAt")
    var createdAt: String = ""

    @Ignore
    @JsonIgnore
    val additionalProperties = HashMap<String, Any>()

}