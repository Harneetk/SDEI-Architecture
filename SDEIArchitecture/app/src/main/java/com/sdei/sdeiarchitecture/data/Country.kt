package com.sdei.sdeiarchitecture.data

import androidx.databinding.BaseObservable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class Country : BaseObservable() {

    @JsonProperty("name")
    val name = ""

    @JsonProperty("code")
    val code = ""

}