package android.sdei.com.basicapp.model

import com.google.gson.annotations.SerializedName

/**
 *
 * @Base Model class :  This class contain the common response of every Api.
 **/


open class BaseModel{
    @SerializedName("error")
    var error: String = ""

    @SerializedName("message")
    var message: String = ""

}