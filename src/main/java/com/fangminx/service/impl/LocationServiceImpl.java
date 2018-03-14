package com.fangminx.service.impl;

import com.fangminx.domain.props.SolrCoreProperties;
import com.fangminx.service.LocationService;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fangmin on 2017/9/13.
 */
@Service
public class LocationServiceImpl implements LocationService{

    @Autowired
    private SolrClient solrClient;

    @Autowired
    private SolrCoreProperties solrCoreProperties;

    @Override
    public SolrDocumentList searchBySolr(String language, String country, String vendorType, String keyWord, String page, String size, String type) {
        SolrQuery query = new SolrQuery();
        QueryResponse res = new QueryResponse();
        if(StringUtils.isNotEmpty(keyWord)){
            keyWord = keyWord.replace(" ","");
        }
        query.setRequestHandler("/browse");
        query.addFilterQuery("vendorType:"+vendorType);
        if(type!=null) {
            query.addFilterQuery("type:" + type);
        }
        if("zh_cn".equals(language)) {
            query.setQuery("\""+keyWord+"\"");
            query.addFilterQuery("language:zh_cn");
        }else if("en_us".equals(language)){
            query.setQuery(keyWord+"*");
            query.addFilterQuery("language:en_us");
        }
        if("china".equals(country)){
            query.addFilterQuery("countryCode:CHN");
        }else if("foreign".equals(country)){
            query.addFilterQuery("-countryCode:CHN");
        }
        query.set("fl","code,language,type,country,state,countryCode,stateCode,cityCode,city,districtCode,district,description,vendorAlias,vendorCode");
        query.set("pf","city_pinyin vendorAlias");
        query.set("qf","city_pinyin^4 vendorAlias^10");
        query.setStart(Integer.parseInt(page)*Integer.parseInt(size));
        query.setRows(Integer.parseInt(size));
        try {
            res = solrClient.query(solrCoreProperties.getLocation(),query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res.getResults();
    }

    @Override
    public SolrDocumentList searchByCode(String code, String language, String vendorType) {
        SolrQuery query = new SolrQuery();
        query.setRequestHandler("/browse");
        query.addFilterQuery("language:"+language);
        query.addFilterQuery("code:"+code);
        query.addFilterQuery("vendorType:"+vendorType);
        query.set("fl","code,language,type,country,state,countryCode,stateCode,cityCode,city,districtCode,district,description,vendorAlias,vendorCode");
        query.set("pf","city city_pinyin country state district vendorAlias");
        QueryResponse res = null;
        try {
            res = solrClient.query(solrCoreProperties.getLocation(),query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res.getResults();
    }
}
