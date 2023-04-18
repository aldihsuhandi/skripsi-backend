package id.thesis.shumishumi.core.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath*:bean-context.xml", "classpath*:facade-processor-context.xml"})
public class XmlConfiguration {
}
