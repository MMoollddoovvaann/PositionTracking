package edu.utcluj.track.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Maria on 11.11.2018.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteUser(String userName, String password){

        ArrayList<User> userList = (ArrayList<User>) userRepository.findAll();
        for (User userInList : userList){
            if(userName.equals(userInList.getUserName())){
                if(password.equals(userInList.getPassword())){
                    userRepository.delete(userInList.getId());
                    return true;
                }
            }
        }
        return false;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public User updatePassword(String userName, String newPassword){

        ArrayList<User> usersList = (ArrayList<User>) userRepository.findAll();
        for (User userInList : usersList){
            if(userName.equals(userInList.getUserName())){
                userInList.setPassword(newPassword);
                return userRepository.save(userInList);
            }
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public User getUser(String userName, String password){
        //returns null if no position with this id was founds
        ArrayList<User> userList = (ArrayList<User>) userRepository.findAll();

        for (User userInList : userList){
            if(userName.equals(userInList.getUserName())){
                if(password.equals(userInList.getPassword())){
                    return userInList;
                }
            }
        }
        return null;
    }


}
