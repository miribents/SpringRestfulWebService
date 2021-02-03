package com.example.restservice.service;

import com.example.restservice.exceptions.GroupNameExistsException;
import com.example.restservice.exceptions.NotFoundException;
import com.example.restservice.model.Group;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GroupService {

    private final AtomicLong counter = new AtomicLong(1);
    public HashMap<Long, Group> groups = new HashMap<>();

    public List<Group> getAllGroups() {
        List<Group> listOfGroups = new ArrayList<>(groups.values());
        return listOfGroups;
    }

    public Group getGroup(long id) throws GroupNameExistsException {
        if(groupExist(id) == false){
            throw new GroupNameExistsException();
        }
        return groups.get(id);
    }

    public Group createGroup(Group groupDetails) throws GroupNameExistsException {
        if(groupNameExist(groupDetails.getGroupName())){
            throw new GroupNameExistsException();
        }
        Group group = new Group();
        group.setId(counter.getAndIncrement());
        group.setGroupName(groupDetails.getGroupName());
        group.setAdmin(groupDetails.getAdmin());
        groups.put(group.getId(), group);
        return group;
    }

    public Group updateGroup(long id, Group groupDetails ) throws GroupNameExistsException, NotFoundException {
        if(groupExist(id) == false){
            throw new NotFoundException();
        }
        if(groupNameExist(groupDetails.getGroupName())){
            throw new GroupNameExistsException();
        }

        Group group = getGroup(id);
        group.setGroupName(groupDetails.getGroupName());
        group.setAdmin(groupDetails.getAdmin());
        groups.put(group.getId(), group);
        return group;
    }

    public Group deleteGroup(long id) throws NotFoundException {
        if(groupExist(id) == false){
            throw new NotFoundException();
        }
        return groups.remove(id);
    }

    private boolean groupNameExist(String groupName){
        for (Group group1 : getAllGroups()) {
            if(groupName.contains(group1.getGroupName())) {
                return true;
            }
        }
        return false;
    }

    private boolean groupExist(long id){
        return groups.containsKey(id);
    }

}