package com.scofen.l78z.xiaochuan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class XiaochuanApplication {

	public static void main(String[] args) {
		SpringApplication.run(XiaochuanApplication.class, args);
	}

}
