package at.guigu.bootInitialzr;

import at.guigu.bootInitialzr.bean.*;
import at.guigu.bootInitialzr.component.ISmsService;
import at.guigu.bootInitialzr.configuration.MongoDBConfig;
import at.guigu.bootInitialzr.controller.UserControllerAdvanced;
import at.guigu.bootInitialzr.dao.UserIntegerRepository;
import at.guigu.bootInitialzr.service.UserService;
import ch.qos.logback.core.status.Status;
import com.mongodb.client.*;
import lombok.extern.slf4j.Slf4j;
import net.joshka.junit.json.params.JsonFileSource;
import net.joshka.junit.json.params.JsonSource;
import org.bson.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class BootInitialzrApplicationTests {

    @Autowired
    private Student person;

    @Autowired
    private Address address;

    @Autowired
    private Person2 person2;

    @Autowired
    private PersonWithValue personWithValue;

    @Autowired
    private UserIntegerRepository userRepository;


    @Autowired
    private ApplicationContext ioc;

    @Autowired
    private MongoDBConfig mongoDBConfig;

    @Value("${spring.data.mongodb.uri}")
    private String uri;

    @Autowired
    private UserService userService;

    @Autowired
    private Position position;


    @Autowired
    private UserControllerAdvanced userControllerAdvanced;

    @Autowired
    private Employee zhangsanEmpolyee;


    @Autowired
    private Employee lisiEmpolyee;

    @Autowired
    private Employee xxxEmpolyee;

    @Autowired
    private Employee yyyEmpolyee;

    //@Autowired
    private Employee empolyee;

    @Autowired
    private ISmsService service;

    @Test
    void test_ISmsService(){
        service.sendMessage("send017676775939", "the message is arriving");
        service.pubMessage( "pub017676775939",  "the message is arriving");
        service.subMessage( "sub017676775939",  "the message is arriving");
    }

    @Test
    void test_empoyee(){
        System.out.println(empolyee.getId());
        System.out.println(empolyee.getName());
    }

    @Test
    void test_primaryEmpoyee1(){
        System.out.println(xxxEmpolyee.getId());
        System.out.println(xxxEmpolyee.getName());
        System.out.println(yyyEmpolyee.getId());
        System.out.println(yyyEmpolyee.getName());
    }

    @Test
    void test_primaryEmpoyee(){
        System.out.println(zhangsanEmpolyee.getId());
        System.out.println(zhangsanEmpolyee.getName());
        System.out.println(lisiEmpolyee.getId());
        System.out.println(lisiEmpolyee.getName());
    }

    @Test
    void test_loadUserListAggregateByGroupId(){
        userControllerAdvanced.loadUserListAggregateByGroupId();
    }

    @Test
    void test_loadUsersByAggregateGroupId(){
        userControllerAdvanced.loadUsersByAggregateGroupId(null);
    }

    @Test
    void test_userService_positionConfig(){
       /* Position bean = ioc.getBean(Position.class);;*/
        System.out.println(position.getDesc());
    }

    @Test
    void test_Primary_position(){
        boolean hasYyyPosition = ioc.containsBean("yyyPosition");
        boolean hasXxxPosition = ioc.containsBean("xxxPosition");
        Position position = ioc.getBean("yyyPosition", Position.class);
        System.out.println(position);
    }
    
    @Test
    void test_getAllIndexes(){
        final ListIndexesIterable<Document> indexList = userControllerAdvanced.getUserIndexAll(USERFieldEnum.USERID.getCollection());
        for (Document document : indexList) {
            final String json = document.toJson();
            Object v    = document.get("v");
            Object key  = document.get("key");
            Object name = document.get("name");
            Object ns   = document.get("ns");

            System.out.println("json of document: "+ json);
            System.out.println("v    of document: "+ v);
            System.out.println("key  of document: "+ key);
            System.out.println("name of document: "+ name);
            System.out.println("ns   of document: "+ ns);

            boolean hasV    = document.containsKey("v");
            boolean hasKey  = document.containsKey("key");
            boolean hasName = document.containsKey("name");
            boolean hasNs   = document.containsKey("ns");
            System.out.println("hasV     of document: "+ hasV);
            System.out.println("hasKey   of document: "+ hasKey);
            System.out.println("hasName  of document: "+ hasName);
            System.out.println("hasNs    of document: "+ hasNs);

            Document keyDocument = document.get("key", Document.class);
            Set<String> keySet = keyDocument.keySet();
            System.out.println(keySet);
        }
    }

    @Test
    void test_createAscendingIndexOnField(){
        Stream.of(USERFieldEnum.USERID, USERFieldEnum.FIRSTNAME, USERFieldEnum.GENDER).forEach(fieldEnum->{
            userControllerAdvanced.createAscendingIndexOnFieldCollection(fieldEnum.getField(), fieldEnum.getCollection());
        });
    }

    @Test
    void test_removeAscendingIndexOnField(){
        userControllerAdvanced.removeAscendingIndexOnField("gender");
    }

    @Test
    void test_createAscendingIndexGender(){
        userControllerAdvanced.createAscendingIndexGender();
    }

    @Test
    void test_findUsersByConditionGenderCount(){
        long count = userControllerAdvanced.findUsersByConditionGenderCount();
        System.out.println(count);
    }

    @Test
    void test_findUsersByConditionAndSortLimit(){
        List<User> users = userControllerAdvanced.findUsersByConditionAndSortLimit();
        System.out.println(users);
    }

    @Test
    void test_findUsersByRegexFirstNameAndSortByAge(){
        List<User> users = userControllerAdvanced.findUsersByRegexFirstNameAndSortByAge();
        System.out.println(users);
    }

    @Test
    void test_findUsersByRegexFirstName(){
        List<User> users = userControllerAdvanced.findUsersByRegexFirstName();
        System.out.println(users);
    }

    @Test
    void test_findUsersByOperatorGtAndLt(){
        List<User> users = userControllerAdvanced.findUsersByOperatorGtAndLt();
        System.out.println(users);
    }

    @Test
    void test_findUsersWithUserIDs(){
        List<Integer> userIDs = List.of(1, 2, 3, 4, 5, 6);
        System.out.println(userControllerAdvanced.findUsersByUserIDsInOperator(userIDs));
    }

    @Test
    void test_findUsersByFirstNameOrLastName(){
        List<User> users = userControllerAdvanced.findUsersByFirstNameOrLastName("kai", "Yang");
        System.out.println(users);
    }

    @Test
    void test_findUsersByFirstNameAndLastName(){
        List<User> users = userControllerAdvanced.findUsersByFirstNameAndLastName("kai", "Yang");
        System.out.println(users);
    }

    @Test
    void test_findUsersByLastName(){
        List<User> users = userControllerAdvanced.findUsersByLastName("Yang");
        System.out.println(users);
    }

    @Test
    void test_findUserByLastName(){
        User user = userControllerAdvanced.findUserByLastName("Yang");
        System.out.println(user);
    }

    @Test
    void test_findUsersByFirstName(){
        List<User> users= userControllerAdvanced.findUsersByFirstName("goujing");
        System.out.println(users);
    }

    @Test
    void test_findUserByUserID(){
        User user = userControllerAdvanced.findUserByUserID(1);
        System.out.println(user);
    }

    @Test
    void test_findAllUsers(){
        List<User> users = userControllerAdvanced.findUsers();
        Assertions.assertFalse( users.isEmpty());
    }

    @Test
    public void test_Uri(){
        System.out.println(uri);
    }

    @Test
    void test_Annotations(){
        Lion lion = new Lion().setId(11L).setName("kaiyang");
        System.out.println(mongoDBConfig.getHost());
        System.out.println(mongoDBConfig.getPort());
        System.out.println(mongoDBConfig.getDatabase());
        System.out.println(mongoDBConfig.getUri());

        MongoClient mongoClient = MongoClients.create(mongoDBConfig.getUri());
        MongoDatabase user = mongoClient.getDatabase("user");
    }

    @Test
    void mongoConnection() {
        String connectionString = "mongodb://localhost:27017/test";
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
            databases.forEach(db -> System.out.println(db.toJson()));
            MongoDatabase database = mongoClient.getDatabase("test");
            MongoCollection<Document> userDocuments = database.getCollection("user");
            Document first = userDocuments.find().first();
            String json = first.toJson();
            System.out.println(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testLazy() throws IOException {
        final boolean has_lazyResouceBean = ioc.containsBean("resouceBean");

        if (has_lazyResouceBean) {
            ResouceBean resouceBean = (ResouceBean) ioc.getBean("resouceBean");
            resouceBean.test_ResourceBean();
        }
    }

    @Test
    void testPandaBean() {
        Panda panda = new Panda();
        panda.setName("nana");
        panda.setZoo("xian");
        panda.setId(1234L);
    }

    @Test
    void testUserInfo() {
        final boolean hasUserInfo = ioc.containsBean("studentInfo");
        Assertions.assertTrue(hasUserInfo);
        final boolean hasFetchOrderService = ioc.containsBean("fetchOrderService");
        Assertions.assertTrue(hasFetchOrderService);

        System.out.println(hasFetchOrderService);
    }

    @Test
    void contextLoads() {
        System.out.println(person.getId());
        System.out.println(person.getName());

        System.out.println(person2.getId());
        System.out.println(person2.getName());

        System.out.println(personWithValue.getId());
        System.out.println(personWithValue.getName());

        System.out.println(address.getStreetNumber());
        System.out.println(address.getStreetName());

        System.out.println(address.getStreetNumber());
        System.out.println(address.getStreetName());

        boolean has = ioc.containsBean("HelloService");
        System.out.println(has);
    }

    @Test
    void contextLoads2() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("./dbText"));
        String line = "";
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }

    @Test
    void contextLoads3() {
        VxsPosition vxsPosition = new VxsPosition();
        vxsPosition.setVxsID(12345L);
        vxsPosition.setDesc("Mobile");
        vxsPosition.setPrice(67.98d);
        vxsPosition.setDate(new Date());
        Position position = new Position();
        PConverter pConverter = new PConverter(vxsPosition, position);
        pConverter.execute();
        System.out.println(position);

        vxsPosition.setVxsID(99999L);
        vxsPosition.setDesc("Immobile");
        vxsPosition.setPrice(67.98d);
        vxsPosition.setDate(new Date());
        PConverter210723.builder().position(position).vxsPosition(vxsPosition).build().execute();
        System.out.println(position);
    }

    @ParameterizedTest
    @CsvSource(value = "NULL, IN_PROGRESS, 2020-12-20", nullValues = "NULL")
    void customNullArgument(String title, Status status, LocalDate date) {
        assertNull(title);
        assertNull(status);
        assertNull(date);
    }

    @ParameterizedTest
    @CsvSource({
            "15, abc",
            "16, bcd",
            "17, def"
    })
    void test_ParameterizedTest_CsvSource(int index, String str) {
        System.out.println(index);
    }

    @ParameterizedTest
    @CsvSource("12, kaiyang")
    void test_test_ParameterizedTest_CsvSource_Person(Person2 person) {
        System.out.println(person.getId() + ":" + person.getName());
    }


    @ParameterizedTest
    @ValueSource(strings = {"level", "mama", "baba"})
    public void test_dataProvider(String input) {
        System.out.println(input);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2018-01-01", "2018-01-31"})
    void convertStringToLocalDate(LocalDate localDate) {
        System.out.println(localDate.getMonth());
    }

    @ParameterizedTest
    @MethodSource("methodSourceTestFetchPositions")
    public void methodSourceTestFetchPositions(int index, String input) {
        System.out.println(index);
        System.out.println(input);
    }

    public static Stream<Arguments> methodSourceTestFetchPositions() {
        return Stream.of(
                Arguments.of(1, "dd"),
                Arguments.of(2, "ee"),
                Arguments.of(3, "ff")
        );
    }

    @ParameterizedTest
    @MethodSource
    void numberToMonth(String month, boolean name) {
        System.out.println(month);
        System.out.println(name);
    }

    private static Stream<Arguments> numberToMonth() {
        return Stream.of(
                Arguments.of("dd", true),
                Arguments.of("ee", false),
                Arguments.of("ff", true)
        );
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/positionTestData.csv"}, numLinesToSkip = 1, encoding = "UTF-8")
    void test_ParameterizedTest_CsvFileSource_positionTest(String name, Integer age) {
        System.out.println(name);
        System.out.println(age);
    }

    @DisplayName("jsonFileSource_try")
    @ParameterizedTest
    @JsonFileSource(resources = {"/positionTestData.json"})
    void test_ParameterizedTest_jsonFileSource_positionTest(Object object) {
        System.out.println(object);
    }

    @DisplayName("ParameterizedTest_JsonSource")
    @ParameterizedTest
    @JsonSource("[{\"key\":\"value1\"},{\"key\":\"value2\"}]")
    void test_ParameterizedTest_JsonSource_positionTest(Object json) {
        System.out.println(json);
    }

    @Test
    public void read_txtFiles() throws Exception {
        Map<String, Boolean> fileHasHeaderMap = new HashMap<>();
        String path = "src/test/resources";
        final String fileName1 = path + "/address-de.txt";
        final String fileName2 = path + "/address-ht.txt";
        final String fileName3 = path + "/address-it.txt";
        fillFileHasHeaderMapAdvanced(fileHasHeaderMap, fileName1, fileName2, fileName3);
        System.out.println(fileHasHeaderMap);
    }

    private static void fillFileHasHeaderMap(final Map<String, Boolean> fileHasHeaderMap, String... fileNames) {
        final String header = "Art;Name;Gender;SecondName";
        Arrays.stream(fileNames).forEach(fileName -> {
            try (FileInputStream fs = new FileInputStream(fileName)) {
                BufferedReader br = new BufferedReader(new InputStreamReader(fs));
                fileHasHeaderMap.put(fileName, header.equals(br.readLine()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void fillFileHasHeaderMapAdvanced(final Map<String, Boolean> fileHasHeaderMap, String... fileNames) throws Exception {
        final String header = "Art;Name;Gender;SecondName";
        for (String fileName : fileNames) {
            final FileInputStream fs = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            fileHasHeaderMap.put(fileName, header.equals(br.readLine()));
            fs.close();
        }
    }
}
