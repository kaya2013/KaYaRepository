package at.guigu.bootInitialzr.dao;

import at.guigu.bootInitialzr.bean.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserStringRepository extends MongoRepository<User, String> {
    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastName);

    List<User> findByUserID(String userID);

    List<User> findUsersByUserIDIs(String userID);

    User findUserByGender(String gender);

    List<User> findUsersByGender(String gender);

    Optional<User> findById(String id);

    User findUserByUserID(String userID);
}
