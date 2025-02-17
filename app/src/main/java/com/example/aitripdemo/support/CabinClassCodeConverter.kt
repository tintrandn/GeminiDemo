package com.example.aitripdemo.support

import androidx.annotation.StringRes
import com.example.aitripdemo.R
import javax.inject.Inject

class CabinClassCodeConverter @Inject constructor() {

    @StringRes
    fun getClassStringRes(@CabinClassCode cabinClassCode: String?): Int {
        return if (null == cabinClassCode) {
            R.string.cabin_class_default
        } else when (cabinClassCode) {
            CabinClassCode.ECONOMY -> R.string.cabin_class_economy
            CabinClassCode.PREMIUM_ECONOMY -> R.string.cabin_class_premium_economy
            CabinClassCode.BUSINESS -> R.string.cabin_class_business
            CabinClassCode.FIRST -> R.string.cabin_class_first_suites
            else -> R.string.cabin_class_default
        }
    }
}
