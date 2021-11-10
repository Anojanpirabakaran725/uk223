package com.example.demo.domain.group;

import com.example.demo.domain.appUser.User;
import com.example.demo.domain.appUser.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Group>> findAll() {
        return new ResponseEntity<Collection<Group>>(groupService.findAll(), HttpStatus.OK);
    }

}