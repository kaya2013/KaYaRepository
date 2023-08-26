package at.guigu.bootInitialzr.controller;

import at.guigu.bootInitialzr.bean.GROUPIDFieldEnum;
import at.guigu.bootInitialzr.bean.USERFieldEnum;
import at.guigu.bootInitialzr.bean.User;
import at.guigu.bootInitialzr.bean.UserField;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.ListIndexesIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/userList")
public class UserControllerAdvanced {

    @Autowired
    private MongoTemplate mongoTemplate;
    private static final String COLLECTIONNAME_USER = "user";
    private static final String COLLECTIONNAME_GROUP = "group";

    public void loadUserListAggregateByGroupId(){
        Aggregation  aggregation = Aggregation.newAggregation(
            Aggregation.match(Criteria.where(UserField.GROUPID.getField()).is(2)),
            Aggregation.lookup(COLLECTIONNAME_GROUP, USERFieldEnum.GROUPID.getField(), GROUPIDFieldEnum.GROUPID.getField(), "userGroups")
            //,Aggregation.lookup("group", "groupId", "groupId", "userGroups")
            //,Aggregation.project("userID", "firtName", "lastName", "userGroups")
        );

        final AggregationResults<User> userAggregationResults = mongoTemplate.aggregate(aggregation, "user", User.class);
        for (User user : userAggregationResults) {
            System.out.println(user.getUserID());
            System.out.println(user.getFirstName());
            System.out.println(user.getLastName());
        }
    }

    public List<User> loadUsersByAggregateGroupId(String groudId){
        loadUsersByAggregateGroupId();
        return new ArrayList<>();
    }

    public AggregateIterable<Document> loadUsersByAggregateGroupId(){
        final MongoCollection<Document> collection = mongoTemplate.getCollection(COLLECTIONNAME_USER);
        final List<Document> aggregateList = List.of(getLookUpPipe());
        final AggregateIterable<Document> resultset = collection.aggregate(aggregateList);

        try (MongoCursor<Document> cursor = resultset.iterator()){
            while(cursor.hasNext()) {
                Document item_doc = cursor.next();
                Integer userID   = item_doc.getInteger("userID", 0);
                String firstName = item_doc.getString( "firstName" );
                String lastName =  item_doc.getString( "lastName" );
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return resultset;
    }

    private static Document getLookUpPipe() {
        Document lookUp = new Document();
        lookUp.put("$lookup", "db.user.aggregate([\n" +
                "    {\n" +
                "        $match:{\n" +
                "            groupId:2\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        $lookup:{\n" +
                "            from: \"group\",\n" +
                "            localField:\"groupId\",\n" +
                "            foreignField:\"groupId\",\n" +
                "            as :\"contract_group\"\n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "        $project:{\n" +
                "            _id      : 0,\n" +
                "            firstName: \"$firstName\",\n" +
                "            userID   : \"$userID\",\n" +
                "            lastName : \"$lastName\",\n" +
                "            groupId  : \"$groupId\",\n" +
                "            groupName: \"$contract_group\"   \n" +
                "        }\n" +
                "    }\n" +
                "]);");
        return lookUp;
    }

    @PostMapping(value = "/updateFirst")
    public boolean updateFirst(String firstName, Integer userID){
        final Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is(firstName));
        final Update update = new Update().set("userID", userID);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, COLLECTIONNAME_USER);
        return updateResult.getMatchedCount() >0;
    }

    @PostMapping(value = "/updateMany")
    public boolean updateMany(String gender, String newValue){
        final Query query = new Query();
        query.addCriteria(Criteria.where("gender").is(gender));
        final Update update = new Update().set("name", newValue);
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, COLLECTIONNAME_USER);
        return updateResult.getMatchedCount() >0;
    }

    @DeleteMapping(value = "/findAndRemove")
    public boolean findAndRemove(String firstName){
        final Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is(firstName));
        User user = mongoTemplate.findAndRemove(query, User.class, COLLECTIONNAME_USER);
        return user != null;
    }

    @DeleteMapping(value = "/reomve")
    public boolean reomve(String firstName, String lastName){
        final Query query = new Query();
        Criteria firstNameCriteria = Criteria.where("firstName").is(firstName);
        Criteria lastNameCriteria  = Criteria.where("lastName").is(lastName);
        query.addCriteria(new Criteria().andOperator(firstNameCriteria, lastNameCriteria));
        DeleteResult deleteResult  = mongoTemplate.remove(query, User.class, COLLECTIONNAME_USER);
        return deleteResult.getDeletedCount() > 0;
    }

    @DeleteMapping(value = "/findAllAndRemove")
    public boolean findAllAndRemove(String firstName){
        final Query query = new Query();
        Criteria firstNameCriteria = Criteria.where("firstName").is(firstName);
        query.addCriteria(new Criteria().andOperator(firstNameCriteria));
        List<User> users = mongoTemplate.findAllAndRemove(query, User.class, COLLECTIONNAME_USER);
        return users.size() > 0;
    }

    @GetMapping(value = "/getAll")
    public List<User> getAll(){
        return mongoTemplate.findAll(User.class, COLLECTIONNAME_USER);
    }

    @GetMapping(value = "/findUserByUserId/{userID}")
    public User getUserByUserId(@PathVariable("userID")  Integer userID){
        return mongoTemplate.findOne(
                new Query().addCriteria(Criteria.where("userID").is(userID)), User.class
        );
    }

    @PostMapping(value = "/insertUser")
    public String insertUser(User user){
        return "name: " + user.getFirstName() + " secondName:"+user.getLastName();
    }

    @PostMapping(value = "/insertUserWithPrefix")
    public String insertUserWithPrefix(@ModelAttribute("user") User user){
        return "name: " + user.getFirstName() + " secondName:"+user.getLastName();
    }

    @InitBinder("user")
    private void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("user.");
    }

    @GetMapping(value = "/findUsers")
    public List<User> findUsers(){
        return mongoTemplate.findAll(User.class);
    }
    @GetMapping(value = "/findUserByUserID/{val}")
    public User findUserByUserID(@PathVariable("val") Integer userID){
        final Query query = new Query(Criteria.where("userID").is(userID));
        return mongoTemplate.findOne(query, User.class, COLLECTIONNAME_USER);
    }
    @GetMapping(value = "/findUsersByFirstName/{val}")
    public List<User> findUsersByFirstName(String firstName){
        Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is(firstName));
        return mongoTemplate.find(query, User.class, COLLECTIONNAME_USER);
    }

    @GetMapping(value = "/findUserByLastName/{lastName}")
    public User findUserByLastName(@PathVariable("lastName") String lastName){
        Query query = new Query();
        query.addCriteria(Criteria.where("lastName").is(lastName));
        return mongoTemplate.findOne(query, User.class, COLLECTIONNAME_USER);
    }

    @GetMapping(value = "/findUsersByLastName/{lastName}")
    public List<User> findUsersByLastName(@PathVariable("lastName") String lastName){
        Query query = new Query();
        query.addCriteria(Criteria.where("lastName").is(lastName));
        return mongoTemplate.find(query, User.class, COLLECTIONNAME_USER);
    }

    @GetMapping(value = "/findUsersByFirstNameAndLastName")
    public List<User> findUsersByFirstNameAndLastName(@RequestParam(value = "firstName", required = false, defaultValue = "") String firstName,
                                                      @RequestParam(value = "lastName", required = false, defaultValue = "") String lastName){
        final Criteria firstNameCriteria = Criteria.where("firstName").is(firstName);
        final Criteria lastNameCriteria = Criteria.where("lastName").is(lastName);
        final Query query = new Query(new Criteria().andOperator(firstNameCriteria, lastNameCriteria));
        return mongoTemplate.find(query, User.class, COLLECTIONNAME_USER);
    }

    @GetMapping(value = "/findUsersByFirstNameOrLastName")
    public List<User> findUsersByFirstNameOrLastName(@RequestParam(value = "firstName", required = false, defaultValue = "") String firstName,
                                                     @RequestParam(value = "lastName", required = false, defaultValue = "") String lastName){

        Criteria firstNameCriteria = Criteria.where("firstName").is(firstName);
        Criteria lastNameCriteria = Criteria.where("lastName").is(lastName);
        Query query = new Query(new Criteria().orOperator(firstNameCriteria, lastNameCriteria));
        return mongoTemplate.find(query, User.class, COLLECTIONNAME_USER);
    }

    @GetMapping(value = "/findUsersByUserIDsInOperator")
    public List<User> findUsersByUserIDsInOperator(List<Integer> userIDs){
        Query query = new Query(Criteria.where("userID").in(userIDs));
        return mongoTemplate.find(query, User.class, COLLECTIONNAME_USER);

    }

    @GetMapping(value = "/findUsersByOperatorGtAndLt")
    public List<User> findUsersByOperatorGtAndLt(){
        Query query = new Query(Criteria.where("age").gt(45).lt(70));
        return mongoTemplate.find(query, User.class, COLLECTIONNAME_USER);
    }

    @GetMapping("/findUsersByRegexFirstName")
    public List<User> findUsersByRegexFirstName(){
        String regex = "^.*in.*$";  //contain
        regex        = "^h.*$";     //beginn with h
        regex        = "^.*g$";     //end with g
        Query query = new Query(Criteria.where("firstName").regex(regex));
        return mongoTemplate.find(query, User.class, COLLECTIONNAME_USER);
    }

    @GetMapping("/findUsersByRegexFirstNameAndSortByAge")
    public List<User> findUsersByRegexFirstNameAndSortByAge(){
        String regex = "^.*in.*$";  //contain
        regex        = "^h.*$";     //beginn with h
        regex        = "^.*g$";     //end with g
        Query query = new Query(Criteria.where("firstName").regex(regex)).with(Sort.by("age"));
        return mongoTemplate.find(query, User.class, COLLECTIONNAME_USER);
    }
    @GetMapping("/findUsersByConditionAndSortLimit")
    public List<User> findUsersByConditionAndSortLimit(){
        Query query = new Query(Criteria.where("gender").is("male")).with(Sort.by("age")).limit(2).skip(2);
        return mongoTemplate.find(query, User.class, COLLECTIONNAME_USER);
    }
    @GetMapping("/findUsersByConditionGenderCount")
    public long findUsersByConditionGenderCount(){
        Query query = new Query(Criteria.where("gender").is("male"));
        return mongoTemplate.count(query, User.class);
    }
    @GetMapping("/createAscendingIndexGender")
    public void createAscendingIndexGender(){
        final String field = "gender";
        String collection = "user";
        mongoTemplate.getCollection(collection).createIndex(Indexes.ascending(field));
    }

    @GetMapping("/createAscendingIndexGender/{field}")
    public void createAscendingIndexOnField(@PathVariable("field") String field){
        String collection = "user";
        mongoTemplate.getCollection(collection).createIndex(Indexes.ascending(field));
    }

    @GetMapping("/removeAscendingIndexOnField/{field}")
    public void removeAscendingIndexOnField(@PathVariable("field") String field){
        String collection = "user";
        mongoTemplate.getCollection(collection).dropIndex(Indexes.ascending(field));
    }

    @GetMapping("/createAscendingIndexOnFieldCollection/{field}/{collection}")
    public void createAscendingIndexOnFieldCollection(String field, String collection){
        mongoTemplate.getCollection(collection).createIndex(Indexes.ascending(field));
    }

    @PostMapping("/removeAscendingIndexOnFieldCollection/{field}")
    public void removeAscendingIndexOnFieldCollection(@PathVariable("field") String field) {
        mongoTemplate.getCollection("user").dropIndex(Indexes.ascending(field));
    }

    @GetMapping("/getUserIndexAll/{collection}")
    public ListIndexesIterable<Document> getUserIndexAll(String collection){
        return mongoTemplate.getCollection(collection).listIndexes();
    }


}
