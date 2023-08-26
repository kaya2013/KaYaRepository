package at.guigu.bootInitialzr.client;

import at.guigu.bootInitialzr.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Buyer {
    @Autowired
    private HelloWorldService helloWorldService;

    public void buySomething() {
        final String servicesHelloWorld = helloWorldService.getServicesHelloWorld();
        System.out.println(servicesHelloWorld);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
