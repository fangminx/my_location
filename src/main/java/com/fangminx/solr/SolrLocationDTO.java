package com.fangminx.solr;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.solr.core.mapping.Indexed;

import java.io.Serializable;

/**
 * Created by fangmin on 2017/9/15.
 */
@Data
//@SolrDocument(solrCoreName = "locationSearch")
//@SolrDocument(solrCoreName = "locationSearchUAT")
@Builder
public class SolrLocationDTO implements Serializable{

    @Indexed("id")
    private String id;

    @Indexed("code")
    private String code;

    @Indexed("language")
    private String language;

    @Indexed("type")
    private String type;

    @Indexed("country")
    private String country;

    @Indexed("state")
    private String state;

    @Indexed("countryCode")
    private String countryCode;

    @Indexed("stateCode")
    private String stateCode;

    @Indexed("cityCode")
    private String cityCode;

    @Indexed("city")
    private String city;

    @Indexed("districtCode")
    private String districtCode;

    @Indexed("district")
    private String district;

    @Indexed("description")
    private String description;

    @Indexed("city_pinyin")
    private String cityPinyin;

    @Indexed("vendorType")
    private String vendorType;

    @Indexed("vendorAlias")
    private String vendorAlias;

    @Indexed("vendorCode")
    private String vendorCode;

//    @Indexed("vendorCountryCode")
//    private String vendorCountryCode;
}
