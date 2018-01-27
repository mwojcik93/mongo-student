package pl.wspa.mongostudent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
public class MongoStudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoStudentApplication.class, args);
	}
}
