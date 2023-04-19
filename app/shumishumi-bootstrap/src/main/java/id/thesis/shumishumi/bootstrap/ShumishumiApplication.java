package id.thesis.shumishumi.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ComponentScan("id.thesis.shumishumi")
public class ShumishumiApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(ShumishumiApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
