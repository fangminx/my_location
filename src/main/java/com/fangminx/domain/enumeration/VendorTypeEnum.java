package com.fangminx.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

//select v.vendor_type from vendor_alias v GROUP BY v.vendor_type

@Getter
@AllArgsConstructor
public enum VendorTypeEnum {
    baoku_air,
    ctrip_air,
    ctrip_hotel,
    ctrip_train,
    ctsho_air,
    didi,
    meiya_air,
    meiya_hotel,
    standard,
    standard_air,

    ;
}

