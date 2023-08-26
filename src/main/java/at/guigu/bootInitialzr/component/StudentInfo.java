package at.guigu.bootInitialzr.component;

import org.springframework.stereotype.Component;

@Component("studentInfo")
public class StudentInfo implements UserInfo{
    @Override
    public String getUserName() {
        return "I am student......";
    }
}
