package id.thesis.shumishumi.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath*:controller-context.xml", "classpath*:facade-processor-context.xml"})
public class XmlConfiguration {
}
