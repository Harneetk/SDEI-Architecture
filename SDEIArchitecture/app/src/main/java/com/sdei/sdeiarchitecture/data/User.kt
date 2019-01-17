package com.sdei.sdeiarchitecture.data

import androidx.databinding.BaseObservable
import com.fasterxml.jackson.annotation.JsonProperty

class User : BaseObservable() {

    var email: String = ""

    var password: String = ""

    @JsonProperty("id")
    var id: Int = -1

    @JsonProperty("first_name")
    var first_name: String = ""

    @JsonProperty("last_name")
    var last_name: String = ""

    @JsonProperty("avatar")
    var avatar: String = ""

}