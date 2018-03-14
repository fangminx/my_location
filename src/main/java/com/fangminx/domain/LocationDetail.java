package com.fangminx.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by fangmin on 2017/9/13.
 */
@Data
@Entity
public class LocationDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String language;

    private String country;

    private String state;

    private String city;

    private String district;

    private String description;

    private String abbreviation;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "code", referencedColumnName = "code", insertable = false, updatable = false)
    private Location location;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "code", referencedColumnName = "code", insertable = false, updatable = false)
    private List<VendorAlias> vendorAliasList;

}
