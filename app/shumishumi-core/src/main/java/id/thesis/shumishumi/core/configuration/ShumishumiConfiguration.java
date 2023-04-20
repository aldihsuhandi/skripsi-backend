package id.thesis.shumishumi.core.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "id.thesis.shumishumi")
public class ShumishumiConfiguration {
}
