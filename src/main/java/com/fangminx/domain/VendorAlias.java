package com.fangminx.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by fangmin on 2017/9/13.
 */
@Entity
@Data
public class VendorAlias implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String vendorType;

    private String alias;

    private String language;

    private String vendorCode;

    private String vendorCountryCode;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "code", referencedColumnName = "code", insertable = false, updatable = false)
    @JsonIgnore
    private Location location;

}

