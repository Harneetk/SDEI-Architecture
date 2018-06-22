package android.sdei.com.basicapp.model

import com.google.gson.annotations.SerializedName

/**
 * Created by parmil.sharma on 17/05/18.
 */
open class BaseModel{
    @SerializedName("error")
    var error: String = ""

    @SerializedName("message")
    var message: String = ""

}