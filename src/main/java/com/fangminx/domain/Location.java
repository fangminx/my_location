package com.fangminx.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * Created by fangmin on 2017/9/13.
 */
@Entity
@Data
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //HLY_Loc
    private String code;

    //数据类型
    private String type;

    //国家编码
    private String countryCode;

    //州、省编号
    private String stateCode;

    //城市编号
    private String cityCode;

    private String districtCode;

    private String isEnabled;

    private Date createdDate;

    private String createdBy;

    private Date lastModifiedDate;

    private String lastModifiedBy;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
    @JsonIgnore
    private List<LocationDetail> locationDetailList;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
    @JsonIgnore
    private List<VendorAlias> vendorAliasList;

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, code='%s', type='%s']",
                id, code, type);
    }
}