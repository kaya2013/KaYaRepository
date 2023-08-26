package at.guigu.bootInitialzr.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Document(collection = "user")
public class User {
    @Id
    private String _id;
    private String userID;
    private String firstName;
    private String lastName;
    private String gender;
    private String name;

    private String[] address;

    private Hobby hobby;
    private Integer old;
    private Integer age;
    private Integer groupId;
    private List<Group> userGroups;
}
