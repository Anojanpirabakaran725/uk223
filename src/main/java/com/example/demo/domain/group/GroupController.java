package com.example.demo.domain.group;

import com.example.demo.domain.appUser.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
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

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'GUEST')")
    public ResponseEntity<Collection<Group>> findAll() {
        return new ResponseEntity<Collection<Group>>(groupService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Group> findById(@PathVariable("uuid") UUID uuid) {
            return new ResponseEntity<Group>(groupService.findById(uuid), HttpStatus.OK);
    }

    @GetMapping("/users/{uuid}")
    public ResponseEntity<Collection<User>> getAllUsersOfGroup(@PathVariable("uuid") UUID uuid){
        return new ResponseEntity<Collection<User>>(groupService.getAllUsersOfGroup(uuid), HttpStatus.OK);
    }

    /*@PutMapping("/{uuid}")
    @PreAuthorize("hasAuthorize('READ')")
    public void replaceById(@RequestBody Group group, @PathVariable UUID uuid) throws InstanceNotFoundException {
        groupService.put(group, uuid);
    }*/

    @DeleteMapping("/{uuid}")
    @PreAuthorize("hasAuthority('DELETE')")
    public void delete(@PathVariable UUID uuid){
        groupService.delete(uuid);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Group> postMethod(@RequestBody Group group) throws InstanceAlreadyExistsException {
        return ResponseEntity.ok().body(groupService.saveGroup(group));
    }

}