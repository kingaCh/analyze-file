package pl.kinga.exercise;

import java.time.format.DateTimeFormatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

	public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

}
