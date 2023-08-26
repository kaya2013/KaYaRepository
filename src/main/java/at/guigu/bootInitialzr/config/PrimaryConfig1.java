package at.guigu.bootInitialzr.config;

import at.guigu.bootInitialzr.bean.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class PrimaryConfig1 {
    @Bean
    public Employee xxxEmpolyee(){
        return Employee.builder().id(1234L).name("xxxEmpolyee").build();
    }

    @Bean
    public Employee yyyEmpolyee(){
        return Employee.builder().id(5678L).name("yyyEmpolyee").build();
    }
}
