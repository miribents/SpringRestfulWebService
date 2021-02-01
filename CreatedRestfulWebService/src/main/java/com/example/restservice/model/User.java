package com.example.restservice.model;

public class User {
    private long id;
    private String username;
    private String address;


    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public void setUsername( String username ) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setAddress( String address ) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }




   /* public List<User> getAllUsers() {
        List<User> listOfUsers = new ArrayList<>(users.values());
        return listOfUsers;
    }

    public User createUser(User userDetails) {
        User user = new User();
        user.setId(counter.getAndIncrement());
        user.setUsername(userDetails.getUsername());
        user.setAddress(userDetails.getAddress());
        users.put(user.getId(), user);
        return user;
    } */

}