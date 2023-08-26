package at.guigu.bootInitialzr.dao;

import at.guigu.bootInitialzr.bean.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserIntegerRepository extends MongoRepository<User, Integer> {
    List<User> findByUserID(Integer userID);
    List<User> findUsersByUserIDIs(Integer userID);

    User findUserByUserID(Integer userId);

    @Override
    Optional<User> findById(Integer integer);
}
