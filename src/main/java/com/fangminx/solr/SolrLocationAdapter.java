package com.fangminx.solr;

import com.fangminx.domain.Location;
import com.fangminx.domain.LocationDetail;
import com.fangminx.domain.VendorAlias;
import lombok.AllArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by fangmin on 2017/9/15.
 */
@AllArgsConstructor
public class SolrLocationAdapter implements Adapter<SolrLocationDTO>{

    private Location location;
    private String language;
    private String vendorType;

    @Override
    public SolrLocationDTO convertToDTO() {

        if (location == null) {return null;}
        SolrLocationDTO solrLocationDTO;

    try {
        List<LocationDetail> locationDetailList = location.getLocationDetailList().stream()
                .filter(p -> p.getLanguage().equals(language))
                .collect(Collectors.toList());
        LocationDetail locationDetail = CollectionUtils.isEmpty(locationDetailList)?null:locationDetailList.get(0);

        List<VendorAlias> vendorAliasList = location.getVendorAliasList().stream()
                .filter(a -> a.getVendorType().equals(vendorType))
                .filter(b -> b.getLanguage().equals(language))
                .collect(Collectors.toList());
        VendorAlias vendorAlias = CollectionUtils.isEmpty(vendorAliasList)?null:vendorAliasList.get(0);

        solrLocationDTO = SolrLocationDTO.builder()
                .language(language)
                .vendorType(vendorType)
                .code(location.getCode())
                .type(location.getType())
                .countryCode(location.getCountryCode())
                .stateCode(location.getStateCode())
                .cityCode(location.getCityCode())
                .districtCode(location.getDistrictCode())
                .country(locationDetail==null?null:locationDetail.getCountry())
                .state(locationDetail==null?null:locationDetail.getState())
                .city(locationDetail==null?null:locationDetail.getCity())
                .district(locationDetail==null?null:locationDetail.getDistrict())
                .description(locationDetail==null?null:locationDetail.getDescription())
                .vendorAlias(vendorAlias==null?null:vendorAlias.getAlias().replace(" ","").replace(" ","").replace("，",","))
                .vendorCode(vendorAlias==null?null:vendorAlias.getVendorCode())
//                .vendorCountryCode(vendorAlias==null?null:vendorAlias.getVendorCountryCode())
                .cityPinyin("zh_cn".equals(language) ? vendorAlias.getAlias() : null) //和vendorAlias字段一致，solr用了拼音分词器
                .id(vendorAlias==null?null:vendorAlias.getId() + vendorAlias.getAlias()+ vendorType + language) //id要保证唯一
                .build();
        }catch (Exception e){
            throw new RuntimeException("SolrLocationDTO transform Error",e);
        }

        return solrLocationDTO;
    }
}
