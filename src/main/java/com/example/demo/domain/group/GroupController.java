package com.example.demo.domain.group;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceNotFoundException;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<Collection<Group>> findAll() {
        return new ResponseEntity<Collection<Group>>(groupService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Group> findById(@PathVariable("uuid") UUID uuid) {
            return new ResponseEntity<Group>(groupService.findById(uuid), HttpStatus.OK);
    }

    @PutMapping("/{uuid}")
    public void replaceById(@RequestBody Group group, @PathVariable UUID uuid){
        groupService.put(group, uuid);
    }

}