package at.guigu.bootInitialzr.bean;

import at.guigu.bootInitialzr.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuyerBean {
    @Autowired
    private HelloWorldService helloWorldService;

    public void BuyerBeanSomething() {
        final String servicesHelloWorld = helloWorldService.getServicesHelloWorld();
        System.out.println(servicesHelloWorld);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
