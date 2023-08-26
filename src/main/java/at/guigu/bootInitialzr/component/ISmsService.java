package at.guigu.bootInitialzr.component;

public interface ISmsService {
    void sendMessage(String phone, String message);

    void pubMessage(String phone, String message);
    void subMessage(String phone, String message);
}
