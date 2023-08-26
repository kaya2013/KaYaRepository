package at.guigu.bootInitialzr.config;

import at.guigu.bootInitialzr.bean.Employee;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class PrimaryConfig {
    @Bean
    @Qualifier("zhangsanEmpolyee")
    public Employee zhangsanEmpolyee(){
        return Employee.builder().id(1234L).name("zhangsan").build();
    }

    @Bean
    @Qualifier("lisiEmpolyee")
    public Employee lisiEmpolyee(){
        return Employee.builder().id(5678L).name("lisi").build();
    }

    @Bean
    public Employee employee(){
        return Employee.builder().id(5678L).name("employee").build();
    }
}
