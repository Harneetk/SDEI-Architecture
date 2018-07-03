package android.sdei.com.basicapp.utill

import java.text.ParseException

import java.text.SimpleDateFormat

import java.util.*

/**
 * Use this class for Date conversion formats as per the requirement
 */

    var serverDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    var alohaDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    var checkInDate = SimpleDateFormat("yyyy-MM-dd HH:mm")
    private val dayOnlyFormat = SimpleDateFormat("EEE")
    var reservationDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    var reservationDateToShow = SimpleDateFormat("MM/dd/yyyy")
    var suggestionDateWeGet = SimpleDateFormat("MM/dd/yyyy HH:mm")
    var suggestionDateToShow = SimpleDateFormat("MM/dd/yyyy hh:mm a")
    var reservationTime = SimpleDateFormat("h:mm a")
    var reservationTime12 = SimpleDateFormat("h:mma")
    var reservationTime24 = SimpleDateFormat("HH")
    var reservationTime12WithoutMin = SimpleDateFormat("hh a")
    var DOBsmall = SimpleDateFormat("MMMM dd")
    //    public static SimpleDateFormat DOB         = new SimpleDateFormat("dd-MM-yyyy");
    var DOB = DOBsmall
    var DOBfacebook = SimpleDateFormat("MM/dd/yyyy")
    var dayHour = SimpleDateFormat("hh")
    var month = SimpleDateFormat("MMM")


    @Throws(ParseException::class)


    fun parseToDOBfromFb(fbDate: String): Date {

        return DOBfacebook.parse(fbDate)

    }


    fun currentHourDay(): Int {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    }

    fun currentDate(): Int {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    }

    fun currentAmPm(): String {
        return Calendar.getInstance().get(Calendar.AM_PM).toString()
    }


    fun currentMonth(): String {
        return month.format(Calendar.getInstance().time)
    }

    fun getMonth(date: Date): String {
        return month.format(date)
    }

    fun getDayName(time: Date): String {
        return dayOnlyFormat.format(time)
    }

    fun getDayName(date: String): String {

        try {
            val date1 = serverDate.parse(date)
            return dayOnlyFormat.format(date1)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return getDayName(Calendar.getInstance().time)
    }

    fun displayReservationDate(appointment_date: String?): String? {
        try {
            if (appointment_date == null) {
                return ""
            }
            val date1 = serverDate.parse(appointment_date)
            return reservationDateToShow.format(date1)
        } catch (e: ParseException) {
            try {
                val date1 = checkInDate.parse(appointment_date)
                return reservationDateToShow.format(date1)
            } catch (e1: Exception) {
                e1.printStackTrace()
            }

        }

        return appointment_date

    }

    fun displayAlohaDate(appointment_date: String?): String? {
        try {
            if (appointment_date == null) {
                return ""
            }
            val date1 = alohaDate.parse(appointment_date)
            return reservationTime.format(date1)
        } catch (e: ParseException) {
            try {
                val date1 = alohaDate.parse(appointment_date)
                return reservationTime.format(date1)
            } catch (e1: Exception) {
                e1.printStackTrace()
            }

        }

        return appointment_date

    }

    fun displaySuggestionDate(appointment_date: String?): String? {
        try {
            if (appointment_date == null) {
                return ""
            }
            val date1 = suggestionDateWeGet.parse(appointment_date)
            return suggestionDateToShow.format(date1)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return appointment_date

    }

    fun isExpiredDate(appointmentDate: String): Boolean {
        try {
            val instance = Calendar.getInstance()
            instance.timeInMillis = System.currentTimeMillis()
            return reservationDate.parse(appointmentDate).before(instance.time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return false
    }

    fun getServerDate(appointment_date: String): String {
        try {
            return serverDate.format(checkInDate.parse(appointment_date))
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return appointment_date
    }

    fun getCurrentDateTime(): String {
        return SimpleDateFormat("yyyy-MM-dd HH:mm").format(Date())
    }

    @Throws(ParseException::class)
    fun getHourIn24(date12Hr: String): Int {
        return Integer.parseInt(reservationTime24.format(reservationTime12.parse(date12Hr)))
    }

    @Throws(ParseException::class)
    fun getHourIn12(date24Hr: Int): String {
        return reservationTime12WithoutMin.format(reservationTime24.parse(date24Hr.toString() + ""))
    }



