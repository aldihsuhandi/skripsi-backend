package id.thesis.shumishumi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ShumishumiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShumishumiApplication.class, args);
	}

}
