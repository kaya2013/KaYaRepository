package at.guigu.bootInitialzr.configuration;

import at.guigu.bootInitialzr.bean.Position;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class PositionConfig {

    @Bean(name = "xxxPosition")
    public Position xxxPosition(){
        Position position = new Position();
        position.setDesc("xxxPosition");
        return position;
    }

    @Primary
    @Bean(name = "yyyPosition")
    public Position yyyPosition(){
        Position position = new Position();
        position.setDesc("yyyPosition");
        return position;
    }

}
