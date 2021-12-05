package com.farmit.utils.gatewayutility

import java.util.*

class ServiceUtility {

    fun chkNull(pData: Any?): Any {
        return pData ?: ""
    }

    fun addToPostParams(paramKey: String, paramValue: String?): String {
        return if (paramValue != null) paramKey + PARAMETER_EQUALS + paramValue + PARAMETER_SEP else ""
    }

    fun randInt(min: Int, max: Int): Int {
        // Usually this should be a field rather than a method variable so
        // that it is not re-seeded every call.
        val rand = Random()

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        return rand.nextInt(max - min + 1) + min
    }
}