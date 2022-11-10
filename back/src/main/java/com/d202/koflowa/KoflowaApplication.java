package com.d202.koflowa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // JPA Auditing 활성화
//@EnableRedisRepositories(basePackages = {"com.d202.koflowa.repository.redisTest"})
public class KoflowaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KoflowaApplication.class, args);
	}
}
