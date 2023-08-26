package at.guigu.bootInitialzr.bean;

import org.springframework.stereotype.Component;

@Component
public class Student {
    private Long id;
    private String name;

    private String sex;

    private String job;

    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
