package at.guigu.bootInitialzr.bean;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.bson.Document;

@Data
@AllArgsConstructor
@Builder
public class MongoDBInit {
    private String uri;
    private String databaseName;

    public void testConnection() {
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        MongoCollection<Document> usersCollection = database.getCollection("user");
        Document document = usersCollection.find().first();
        System.out.println(document);
    }
}
