package at.guigu.bootInitialzr.bean;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ResouceBean {

    Resource resource;
    public ResouceBean(Resource resource) {
        this.resource = resource;
    }

    public void test_ResourceBean() throws IOException {
        System.out.println(new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8));
    }
}
