package id.thesis.shumishumi.core.configuration;

import com.midtrans.Config;
import com.midtrans.ConfigFactory;
import com.midtrans.service.MidtransCoreApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MidtransConfig {

    @Value("${midtrans.client.key}")
    private String clientKey;

    @Value("${midtrans.server.key}")
    private String serverKey;

    @Value("${midtrans.production.flag}")
    private boolean production;

    @Bean
    public MidtransCoreApi midtransCoreApi() {
        Config configOptions = Config.builder()
                .enableLog(true)
                .setIsProduction(production)
                .setServerKey(serverKey)
                .setClientKey(clientKey)
                .build();

        return new ConfigFactory(configOptions).getCoreApi();
    }
}
