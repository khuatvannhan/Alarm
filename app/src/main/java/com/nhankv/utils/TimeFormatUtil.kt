package com.nhankv.utils

object TimeFormatUtil {
    fun toDisplayString(timeHundreds: Int): String {
        var time = timeHundreds
        val hundreds: Int = timeHundreds % 100
        val seconds: Int
        val minutes: Int
        // Used to add a '0' in the .03(a one digit number) so it becomes .03
        // So if the result of timeHundreds%100 equals a one digit number add 0
        // to the end of the time string
        // Avoids unnecessary if statements
        val formatterArrayMillis = arrayOfNulls<String>(2)
        val formattedSeconds: String
        val formattedMinutes: String

        val milliSecStr = Integer.toString(hundreds)
        formatterArrayMillis[0] = "0$milliSecStr"
        formatterArrayMillis[1] = milliSecStr

        time /= 100
        seconds = time % 60
        time /= 60
        minutes = time % 60

        //format output
        formattedSeconds = Integer.toString(seconds / 10) + Integer.toString(seconds % 10)
        formattedMinutes = Integer.toString(minutes / 10) + Integer.toString(minutes % 10)

        val millSecDigitsCnt = milliSecStr.length

        return formattedMinutes + ":" +
                formattedSeconds + "." +
                formatterArrayMillis[millSecDigitsCnt - 1]
    }

}
