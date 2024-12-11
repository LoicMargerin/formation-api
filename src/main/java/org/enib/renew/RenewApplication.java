package org.enib.renew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "org.enib.renew"})
public class RenewApplication {

	public static void main(String[] args) {
		SpringApplication.run(RenewApplication.class, args);
	}

}
