package com.tf.tfgfabackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;



// @SpringBootApplication
// // @SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
// public class TfgfaBackendApplication extends SpringBootServletInitializer {

//  public static void main(String[] args) {
//      SpringApplication.run(TfgfaBackendApplication.class, args);
//  }
// }

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class TfgfaBackendApplication extends SpringBootServletInitializer {
	private static final Logger logger = LoggerFactory.getLogger(TfgfaBackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TfgfaBackendApplication.class, args);
		logger.info("TfgfaBackendApplication has started successfully.");
	}
}