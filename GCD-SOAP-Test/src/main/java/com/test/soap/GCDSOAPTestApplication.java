package com.test.soap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The Class GCDSOAPTestApplication.
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.test.soap.config", "com.test.soap.ws" })
public class GCDSOAPTestApplication {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(GCDSOAPTestApplication.class, args);
	}
}
