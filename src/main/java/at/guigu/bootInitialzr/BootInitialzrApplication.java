package at.guigu.bootInitialzr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@SpringBootApplication
public class BootInitialzrApplication extends AbstractMongoClientConfiguration {
	public static void main(String[] args) {
		String property = System.getProperty("user.dir");
		String profiles_active = System.getProperty("spring.profiles.active");
		String spring_profiles_active = System.getenv("spring.profiles.active");
		SpringApplication.run(BootInitialzrApplication.class, args);
	}

	@Override
	protected String getDatabaseName() {
		return "test";
	}
}
