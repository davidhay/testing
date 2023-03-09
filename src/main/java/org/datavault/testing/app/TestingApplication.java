package org.datavault.testing.app;

import java.time.Clock;
import org.datavault.testing.config.CalculatorConfig;
import org.datavault.testing.config.ClockConfig;
import org.datavault.testing.config.ControllerConfig;
import org.datavault.testing.config.DatabaseConfig;
import org.datavault.testing.config.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Import({ClockConfig.class, ControllerConfig.class, ServiceConfig.class, CalculatorConfig.class, DatabaseConfig.class})
public class TestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestingApplication.class, args);
	}

}
