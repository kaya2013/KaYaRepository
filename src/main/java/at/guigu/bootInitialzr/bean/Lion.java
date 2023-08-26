package at.guigu.bootInitialzr.bean;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Lion {
    String name;
    Long id;
}
