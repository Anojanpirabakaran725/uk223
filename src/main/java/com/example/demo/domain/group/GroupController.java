package com.example.demo.domain.group;

import com.example.demo.domain.appUser.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('READ', 'WRITE', 'DELETE', 'ALL_PRIVILEGES')")
    public ResponseEntity<Collection<Group>> findAll() {
        return new ResponseEntity<Collection<Group>>(groupService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    @PreAuthorize("hasAnyAuthority('READ', 'WRITE', 'DELETE', 'ALL_PRIVILEGES')")
    public ResponseEntity<Group> findById(@PathVariable("uuid") UUID uuid) {
        return new ResponseEntity<Group>(groupService.findById(uuid), HttpStatus.OK);
    }

    @GetMapping("/{uuid}/{offset}/{pageSize}")
    @PreAuthorize("@groupServiceImpl.isUserAuthorizedForGroup(#uuid)")
    public ResponseEntity<Page<User>> getAllUsersOfGroup(@PathVariable("uuid") UUID uuid, @PathVariable("offset") int offset, @PathVariable("pageSize") int pageSize) {
        return new ResponseEntity<>(groupService.getAllUsersOfGroup(uuid, offset, pageSize), HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    @PreAuthorize("hasAuthority('ALL_PRIVILEGES')")
    public ResponseEntity<String> delete(@PathVariable UUID uuid) throws InstanceNotFoundException {
        return ResponseEntity.ok().body(groupService.delete(uuid));
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ALL_PRIVILEGES')")
    public ResponseEntity<Group> postMethod(@RequestBody Group group) throws InstanceAlreadyExistsException {
        return ResponseEntity.ok().body(groupService.saveGroup(group));
    }

    @PutMapping("/{uuid}")
    @PreAuthorize("@groupServiceImpl.isUserAuthorizedForGroup(#uuid)")
    public Group updateGroup(@PathVariable UUID uuid, @RequestBody Group group) {
        return groupService.updateGroup(uuid, group);
    }

}