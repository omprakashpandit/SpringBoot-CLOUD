/**
 * 
 */
package com.carbonrider.gcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Carbonrider
 *
 */
@SpringBootApplication
public class SpringGCPApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringGCPApplication.class, args);
	}

}
//http://localhost:8080/storage --Reader
//http://localhost:8080/write-file/one.txt --Writer