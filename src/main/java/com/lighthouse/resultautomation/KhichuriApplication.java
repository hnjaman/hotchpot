package com.lighthouse.resultautomation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//@Configuration
//@SpringBootApplication(scanBasePackages = {"com.lighthouse.resultautomation.common", "com.lighthouse.resultautomation.security","com.lighthouse.resultautomation"})
//@EntityScan(basePackages = {"com.lighthouse.resultautomation.common", "com.lighthouse.resultautomation.model"})
//@EnableCaching
//@EnableRedisRepositories(basePackages = {"com.lighthouse.resultautomation.common.repository.redis"})
@EnableJpaRepositories(basePackages = {"com.lighthouse.resultautomation.repository"})
//@EnableElasticsearchRepositories(basePackages = {"com.lighthouse.resultautomation.repository.elasticsearch"})
@SpringBootApplication
		//(exclude = ElasticsearchDataAutoConfiguration.class)
//@EnableElasticsearchRepositories(basePackages = "com.lighthouse.resultautomation.repository")
public class KhichuriApplication {

	public static void main(String[] args) {
		SpringApplication.run(KhichuriApplication.class, args);
	}
}
