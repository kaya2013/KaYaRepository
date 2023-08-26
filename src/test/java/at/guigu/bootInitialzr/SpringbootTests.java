package at.guigu.bootInitialzr;

import at.guigu.bootInitialzr.bean.BuyerBean;
import at.guigu.bootInitialzr.client.Buyer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = {"at.guigu.bootInitialzr.client", "at.guigu.bootInitialzr.service"})
public class SpringbootTests {
    @Autowired
    private ApplicationContext ioc;

    @Test
    void test_ISmsService(){
        Buyer buyer = ioc.getBean(Buyer.class);
        buyer.buySomething();
        System.out.println(buyer);
        BuyerBean buyerBean = ioc.getBean(BuyerBean.class);
        buyerBean.BuyerBeanSomething();
    }

}
