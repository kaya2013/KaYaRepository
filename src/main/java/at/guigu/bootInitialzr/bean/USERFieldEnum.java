package at.guigu.bootInitialzr.bean;

public enum USERFieldEnum {
    USERID("userID", "user"),
    GENDER("gender", "user"),
    FIRSTNAME("firstName", "user"),
    LASTNAME("lastName", "user"),
    OLD("old", "user"),
    AGE("age", "user"),
    USERGROUPS("userGroups", "user"),
    GROUPID("groupId", "user"),
    ADDRESS("address", "user");

    final String field;
    final String collection;
    USERFieldEnum(String field, String collection) {
        this.field      = field;
        this.collection = collection;
    }

    public String getCollection() {
        return collection;
    }

    public String getField() {
        return field;
    }
}
