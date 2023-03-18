package id.thesis.shumishumi.configuration;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterceptorConfiguration {

    public MethodInterceptor controllerLogInterceptor() {
        return null;
    }

    @Bean
    public BeanNameAutoProxyCreator controllerInvocationLogProxyCreator() {
        BeanNameAutoProxyCreator proxyCreator = new BeanNameAutoProxyCreator();

        return proxyCreator;
    }
}