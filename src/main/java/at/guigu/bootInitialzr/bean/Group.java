package at.guigu.bootInitialzr.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Group {
    @Id
    private String  _id;
    private String  name;
    private Integer groupId;
}
