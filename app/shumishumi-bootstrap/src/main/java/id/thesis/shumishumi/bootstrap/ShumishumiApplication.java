package id.thesis.shumishumi.bootstrap;

import id.thesis.shumishumi.core.configuration.ShumishumiConfiguration;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EntityScan("id.thesis.shumishumi")
@ComponentScan("id.thesis.shumishumi")
public class ShumishumiApplication {

    private static final Logger logger = LoggerFactory.getLogger(LogConstant.SERVER_BOOT);

    public static void main(String[] args) {
        try {

            String appHome = System.getProperty("user.dir");
            logger.info(ShumishumiApplication.class.getSimpleName() + " is running at location: " + appHome);
            long current = System.currentTimeMillis();

            SpringApplication springApplication = new SpringApplication(ShumishumiConfiguration.class);
            ApplicationContext applicationContext = springApplication.run(args);

            Environment environment = applicationContext.getEnvironment();

            String appName = environment.getProperty("app.name");
            String profiles = environment.getProperty("spring.profiles.active");
            logger.info(ShumishumiApplication.class.getSimpleName() + " loaded appName: " + appName);
            logger.info(ShumishumiApplication.class.getSimpleName() + " loaded active profiles: " + profiles);


            long timeCost = System.currentTimeMillis() - current;
            logger.info(String.format("shumishumi application started in %dms, loaded %d beans:", timeCost, applicationContext.getBeanDefinitionCount()));

            for (String beanName : applicationContext.getBeanDefinitionNames()) {
                Object bean = applicationContext.getBean(beanName);
                Class<?> targetClass = AopUtils.getTargetClass(bean);
                if (targetClass.getName().startsWith("id.thesis.shumishumi")) {
                    logger.info(String.format("\t >>> %s : %s", beanName, targetClass.getName()));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e.getCause(), e);
        }
    }

}
