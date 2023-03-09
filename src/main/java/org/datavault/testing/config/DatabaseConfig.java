package org.datavault.testing.config;

import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages="org.datavault.testing.entity")
@EnableJpaRepositories(basePackages = "org.datavault.testing.repo")
public class DatabaseConfig {

}
