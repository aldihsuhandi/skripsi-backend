package id.thesis.shumishumi.core.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("id.thesis.shumishumi")
@ComponentScan("id.thesis.shumishumi")
@EnableJpaRepositories(basePackages = "id.thesis.shumishumi")
public class ShumishumiConfiguration {
}
