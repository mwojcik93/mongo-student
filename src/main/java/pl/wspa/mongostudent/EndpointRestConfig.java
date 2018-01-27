package pl.wspa.mongostudent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EndpointRestConfig {

    @Bean
    public RestTemplate bitMarketRestTemplate() {
        return new RestTemplate();
    }


}
