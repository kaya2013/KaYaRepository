package at.guigu.bootInitialzr.bean;

public enum GROUPIDFieldEnum{
    GROUPID("groupId", "group"),
    NAME("name", "group");

    final String field;
    final String collection;
    GROUPIDFieldEnum(String field, String collection) {
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
