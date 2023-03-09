package org.datavault.testing.app;

import org.datavault.testing.config.CalculatorConfig;
import org.datavault.testing.config.ClockConfig;
import org.datavault.testing.config.ControllerConfig;
import org.datavault.testing.config.DatabaseConfig;
import org.datavault.testing.config.ServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ClockConfig.class, ControllerConfig.class, ServiceConfig.class, CalculatorConfig.class, DatabaseConfig.class})
public class TestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestingApplication.class, args);
	}

}
