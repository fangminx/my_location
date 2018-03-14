package com.fangminx.domain.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = SolrCoreProperties.PREFIX, ignoreUnknownFields = true)
public class SolrCoreProperties {

    public static final String PREFIX = "solrcore";

    private String location;
}
