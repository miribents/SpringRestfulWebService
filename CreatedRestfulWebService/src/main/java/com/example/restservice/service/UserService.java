package com.example.restservice.service;

import com.example.restservice.exceptions.NotFoundException;
import com.example.restservice.exceptions.UsernameExistsException;
import com.example.restservice.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {

    private final AtomicLong counter = new AtomicLong(1);
    public HashMap<Long, User> users = new HashMap<>();

    public List<User> getAllUsers() {
        List<User> listOfUsers = new ArrayList<>(users.values());
        return listOfUsers;
    }

    public User getUser(long id) throws UsernameExistsException {
        if(userExist(id) == false){
            throw new UsernameExistsException();
        }
        return users.get(id);
    }

    public User createUser(User userDetails) throws UsernameExistsException {
        if(usernameExist(userDetails.getUsername())){
            throw new UsernameExistsException();
        }
        User user = new User();
        user.setId(counter.getAndIncrement());
        user.setUsername(userDetails.getUsername());
        user.setAddress(userDetails.getAddress());
        users.put(user.getId(), user);
        return user;
    }

    public User updateUser(long id, User userDetails ) throws UsernameExistsException, NotFoundException {
        if(userExist(id) == false){
            throw new NotFoundException();
        }
        if(usernameExist(userDetails.getUsername())){
            throw new UsernameExistsException();
        }

        User user = getUser(id);
        user.setUsername(userDetails.getUsername());
        user.setAddress(userDetails.getAddress());
        users.put(user.getId(), user);
        return user;
    }

    public User deleteUser(long id) throws NotFoundException {
        if(userExist(id) == false){
            throw new NotFoundException();
        }
        return users.remove(id);
    }

    private boolean usernameExist(String username){
        for (User user1 : getAllUsers()) {
            if(username.contains(user1.getUsername())) {
                return true;
            }
        }
        return false;
    }

    private boolean userExist(long id){
       return users.containsKey(id);
    }
}
