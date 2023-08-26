package at.guigu.bootInitialzr.component;

import org.springframework.stereotype.Component;

@Component("teacherInfo")
public class TeacherInfo implements UserInfo{
    @Override
    public String getUserName() {
        return "I am Teacher......";
    }
}
