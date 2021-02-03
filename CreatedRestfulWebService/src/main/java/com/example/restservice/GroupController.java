package com.example.restservice.controller;

import com.example.restservice.exceptions.GroupNameExistsException;
import com.example.restservice.exceptions.NotFoundException;
import com.example.restservice.model.Group;
import com.example.restservice.service.GroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService service;

    @GetMapping("/{id}")
    public ResponseEntity<Group> getGroup(@PathVariable long id) {
        Group foundGroup = null;
        try {
            foundGroup = service.getGroup(id);
        } catch (GroupNameExistsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(foundGroup);
    }

    @GetMapping
    public @ResponseBody List<Group> getAllGroups() {
        return service.getAllGroups();
    }

    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody Group groupDetails) {
        if (isValid(groupDetails)) {
            Group createdGroup = null;
            try {
                createdGroup = service.createGroup(groupDetails);
            } catch (GroupNameExistsException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return ResponseEntity.ok(createdGroup);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
    }

    private boolean isValid(Group groupDetails) {
        return groupDetails != null && groupDetails.getGroupName() != null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Group> updateGroup(@PathVariable Long id, @RequestBody Group groupDetails) {

        Group updatedGroup = null;

        try {
            updatedGroup = service.updateGroup(id, groupDetails);
        } catch (GroupNameExistsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Group> deleteGroup(@PathVariable Long id) {
        Group group = null;
        try {
            group = service.deleteGroup(id);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(group);
    }

}
