package at.guigu.bootInitialzr.bean;

public class UserField {
    public static final String collection = "user";
    public static final FieldElement USERID = FieldElement.builder().field("userID").collection(collection).build();
    public static final FieldElement FIRSTNAME = FieldElement.builder().field("firstName").collection(collection).build();
    public static final FieldElement LASTNAME = FieldElement.builder().field("lastName").collection(collection).build();
    public static final FieldElement GENDER = FieldElement.builder().field("gender").collection(collection).build();
    public static final FieldElement OLD = FieldElement.builder().field("old").collection(collection).build();
    public static final FieldElement AGE = FieldElement.builder().field("age").collection(collection).build();
    public static final FieldElement GROUPID = FieldElement.builder().field("groupId").collection(collection).build();
    public static final FieldElement ADDRESS = FieldElement.builder().field("address").collection(collection).build();
}

