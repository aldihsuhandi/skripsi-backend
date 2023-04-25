package id.thesis.shumishumi.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("id.thesis.shumishumi")
@ComponentScan("id.thesis.shumishumi")
public class TestRunner {
}
