package com.example.restservice.model;

public class Group {
    private long id;
    private String groupName;
    private String admin;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setAdmin( String admin ) {
        this.admin = admin;
    }

    public String getAdmin() {
        return admin;
    }


}
