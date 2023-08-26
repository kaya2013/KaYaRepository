package at.guigu.bootInitialzr.component;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class SmsServiceAliImpl implements ISmsService{
    @Override
    public void sendMessage(String phone, String message) {
        System.out.println("sendMessage of SmsServiceAliImpl is calling....." + phone + "  :  "+message);
    }

    @Override
    public void pubMessage(String phone, String message) {
        System.out.println("pubMessage of SmsServiceAliImpl is calling....." + phone + "  :  "+message);
    }

    @Override
    public void subMessage(String phone, String message) {
        System.out.println("subMessage of SmsServiceAliImpl is calling....." + phone + "  :  "+message);
    }
}
