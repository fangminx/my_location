package com.fangminx.service;

import org.apache.solr.common.SolrDocumentList;

/**
 * Created by fangmin on 2017/9/13.
 */
public interface LocationService {
    SolrDocumentList searchBySolr(String language, String country, String vendorType, String keyWord, String page, String size, String type);

    SolrDocumentList searchByCode(String code, String language, String vendorType);

}
