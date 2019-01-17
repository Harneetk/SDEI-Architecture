package com.sdei.sdeiarchitecture.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class UserListResponse {

    @JsonProperty("page")
    var page: Int = 0

    @JsonProperty("per_page")
    var per_page: Int = 0

    @JsonProperty("total")
    var total: Int = 0

    @JsonProperty("total_pages")
    var total_pages: Int = 0

    @JsonProperty("data")
    var data: ArrayList<User> = ArrayList()

}