package at.guigu.bootInitialzr.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
application.yaml:
    spring:
        data:
           mongodb:
             host: 127.0.0.1
             port: 27017
             database: test
             uri: mongodb://username:password@IP:27017/test
*/

@Component
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class MongoDBConfig {

    private String uri;

    private String database;

    private Integer port;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    private String host;

    //@Bean
    /*public MongoDBInit mongoDBInit(){
        return new MongoDBInit(uri, database);
    }*/
}
