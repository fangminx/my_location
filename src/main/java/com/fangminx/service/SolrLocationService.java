package com.fangminx.service;

import com.fangminx.solr.SolrLocationDTO;

import java.util.Collection;
import java.util.List;

/**
 * Created by fangmin on 2017/9/15.
 */
public interface SolrLocationService {

    public void addBeans(Collection<?> beans);

    public void removeAll();

    public List<SolrLocationDTO> location2SolrLocationAll();
}
