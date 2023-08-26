package at.guigu.bootInitialzr.service;

import at.guigu.bootInitialzr.bean.User;
import at.guigu.bootInitialzr.component.UserInfo;
import at.guigu.bootInitialzr.configuration.PositionConfig;
import at.guigu.bootInitialzr.dao.UserIntegerRepository;
import at.guigu.bootInitialzr.dao.UserStringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    @Qualifier("teacherInfo")
    private UserInfo userInfo;
    @Autowired
    private UserIntegerRepository userIntegerRepository;

    @Autowired
    private UserStringRepository userStringRepository;

    @Autowired
    private PositionConfig positionConfig;

    public PositionConfig getPositionConfig(){
        return positionConfig;
    }

    public List<User> findAll() {
        return userIntegerRepository.findAll();
    }

    public List<User> findByUserID(Integer userID){
        return userIntegerRepository.findByUserID(userID);
    }

    public User findUserByUserID(String userId){
        return userStringRepository.findUserByUserID(userId);
    }

    public User findUserByUserID(Integer userId){
        return userIntegerRepository.findUserByUserID(userId);
    }

    public User findUserByGender(String gender){
        return userStringRepository.findUserByGender(gender);
    }

    public List<User> findUsersByGender(String gender){
        return userStringRepository.findUsersByGender(gender);
    }

    public List<User> findByUserID(String userID){
        return userStringRepository.findByUserID(userID);
    }

    public User findById(String id){
        return userStringRepository.findById(id).orElse(new User());

    }

    public List<User> findByLastName(String lastName){
        return userStringRepository.findByLastName(lastName);
    }

    public List<User> findUsersByUserIDIs(String userID){
        return userStringRepository.findUsersByUserIDIs(userID);
    }

    public List<User> findUsersByUserIDIs(Integer UserID){
        return userIntegerRepository.findUsersByUserIDIs(UserID);
    }

    public List<User> findByFirstName(String firstName){
        return userStringRepository.findByFirstName(firstName);
    }

    public User addNewUser(User user) {
        // TODO Auto-generated method stub
        return userIntegerRepository.insert(user);
    }
}
