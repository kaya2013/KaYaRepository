package at.guigu.bootInitialzr.config;

import at.guigu.bootInitialzr.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public HelloService getHelloService(){
        return new HelloService();
    }
}
