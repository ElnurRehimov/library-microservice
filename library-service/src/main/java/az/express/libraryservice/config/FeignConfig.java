package az.express.libraryservice.config;

import az.express.libraryservice.config.decoder.SuperAppErrorDecoder;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public SuperAppErrorDecoder errorDecoder() {
        return new SuperAppErrorDecoder();
    }
}
