package android.sdei.com.basicapp.utill

/**
 * It contain all the constant field values that are used into other classes
 */

val MUTE=15
val UN_MUTE=16
val ARCHIVE=17
val LONG_PRESS=100
val DEVICE_TYPE="Android"

object OptionConstant {
    val COPY = 1
    val DELETE = 2
    val PIN = 3
    val FORWORD = 4
    val UNPIN = 5
    val EDIT = 6
    val OPEN_PROFILE = 7
    val EDIT_PROFILE = 8
    val ADD_REACTION = 9
    val ADD_LOG = 10
    val VIEW_LOG = 11
    val ADD_TASK = 12
    val ADD_IMAGE = 13
    val VIEW_PROFILE = 14
    val CAMERA = 15
    val CHOOSE_EXISTING = 16
}


enum class eLinkDownloadType {
    NONE, LOADING, LOADED, ERROR
}