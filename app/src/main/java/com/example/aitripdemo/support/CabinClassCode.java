package com.example.aitripdemo.support;

import static java.lang.annotation.RetentionPolicy.SOURCE;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;

@Retention(SOURCE)
@StringDef({CabinClassCode.FIRST, CabinClassCode.BUSINESS, CabinClassCode.PREMIUM_ECONOMY, CabinClassCode.ECONOMY})
public @interface CabinClassCode {

    String FIRST = "FIRST";
    String BUSINESS = "BUSINESS";
    String PREMIUM_ECONOMY = "PREMIUM_ECONOMY";
    String ECONOMY = "ECONOMY";
}
