package com.shenith.sampleserver.Services;

import com.shenith.sampleserver.DAO.UserDAO;
import com.shenith.sampleserver.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            return new ResponseEntity<>( userDAO.findAll(), HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>( new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<User> getOneUser(int id) {
        try {
            return new ResponseEntity<>( userDAO.findById(id).orElse(null),HttpStatus.OK);

        }catch (Exception e){e.printStackTrace();   }
        return new ResponseEntity<>( new User(),HttpStatus.BAD_REQUEST);


    }

    public ResponseEntity<User> addUser(User user) {

        try {
            return new ResponseEntity<>(  userDAO.save(user),HttpStatus.CREATED);

        }catch (Exception e){e.printStackTrace();   }
        return new ResponseEntity<>( new User(),HttpStatus.BAD_REQUEST);


    }

    public String deleteUser(int id) {
        userDAO.deleteById(id);
        return id +" deleted";
    }

    public User updateUser (User user){
        User exchangeUser =userDAO.findById(user.getId()).orElse(null);
        exchangeUser.setUsername(user.getUsername());
        exchangeUser.setEmail(user.getEmail());
        exchangeUser.setPassword(user.getPassword());
        userDAO.save(exchangeUser);
        return exchangeUser;
    }
}
