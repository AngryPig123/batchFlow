package com.batchflow.config;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * packageName    : com.batchflow.config
 * fileName       : JpaConfig
 * author         : AngryPig123
 * date           : 26. 1. 1.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 1. 1.        AngryPig123       최초 생성
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.batchflow")
@EntityScan(basePackages = "com.batchflow")
public class JpaConfig {
}
