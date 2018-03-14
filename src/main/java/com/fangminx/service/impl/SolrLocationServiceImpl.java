package com.fangminx.service.impl;

import com.fangminx.domain.Location;
import com.fangminx.domain.props.SolrCoreProperties;
import com.fangminx.repository.LocationRepository;
import com.fangminx.service.SolrLocationService;
import com.fangminx.solr.SolrLocationAdapter;
import com.fangminx.solr.SolrLocationDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by fangmin on 2017/9/15.
 */
@Service
@Slf4j
public class SolrLocationServiceImpl implements SolrLocationService{

    @Autowired
    private SolrTemplate solrTemplate;

    @Autowired
    private SolrClient solrClient;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private SolrCoreProperties solrCoreProperties;

    @Override
    public void addBeans(Collection<?> beans) {
        try {
            solrTemplate.saveBeans(solrCoreProperties.getLocation(), beans);
            solrTemplate.commit(solrCoreProperties.getLocation());
        } catch (Throwable t) {
            throw new RuntimeException("Solr Add Beans Error", t);
        }
    }

    @Override
    public void removeAll() {
        try {
            solrClient.deleteByQuery(solrCoreProperties.getLocation(),"*:*");
            solrTemplate.commit(solrCoreProperties.getLocation());
        } catch (Throwable t) {
            throw new RuntimeException("Solr Remove All Error", t);
        }
    }

    //转换所有location，并添加到solr服务中
    @Override
    public List<SolrLocationDTO> location2SolrLocationAll() {
        List<Location> locationList = locationRepository.findAll();
        if(CollectionUtils.isEmpty(locationList)){
            log.error("Location is empty");
            return null;
        }
        List<SolrLocationDTO> solrLocationDTOs = new ArrayList<>();
        locationList.stream().forEach(l->{
            l.getVendorAliasList().stream().forEach(v->{
                SolrLocationAdapter adapter = new SolrLocationAdapter(l,v.getLanguage(),v.getVendorType());
                solrLocationDTOs.add(adapter.convertToDTO());
            });
        });
        addBeans(solrLocationDTOs);
        return solrLocationDTOs;
    }
}
