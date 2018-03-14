package com.fangminx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableConfigurationProperties({SolrCoreProperties.class})
public class MyLocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyLocationApplication.class, args);
	}
}
