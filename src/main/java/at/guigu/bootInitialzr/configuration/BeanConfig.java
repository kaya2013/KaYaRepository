package at.guigu.bootInitialzr.configuration;

import at.guigu.bootInitialzr.bean.LazyFirstBean;
import at.guigu.bootInitialzr.bean.LazySecondBean;
import at.guigu.bootInitialzr.bean.ResouceBean;
import at.guigu.bootInitialzr.service.OrderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
public class BeanConfig {

    @Value("classpath:data/input.txt")
    private Resource resource;
    @Bean
    public OrderService fetchOrderService(){
        return new OrderService();
    }


    @Bean
    public LazyFirstBean lazyFirstBean(){
        return new LazyFirstBean();
    }

    @Bean
    public ResouceBean resouceBean() throws IOException {
        return new ResouceBean(resource);
    }

    @Lazy(value = true)
    @Bean
    public LazySecondBean lazySecondBean(){
        return new LazySecondBean();
    }
}
