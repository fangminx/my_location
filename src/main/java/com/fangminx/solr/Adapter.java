package com.fangminx.solr;

/**
 * Created by fangmin on 2017/9/15.
 */
public interface Adapter<T> {
    T convertToDTO();
}