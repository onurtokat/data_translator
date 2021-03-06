package com.onurtokat;

import com.onurtokat.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author onurtokat
 */
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SpringBootMongodbApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootMongodbApplication.class, args);
	}
}
