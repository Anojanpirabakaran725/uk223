package com.example.demo.domain.group;

import com.example.demo.domain.appUser.User;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import java.util.Collection;
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

    @GetMapping("/users/{uuid}/{offset}/{pageSize}")
    @PreAuthorize("hasRole('ADMIN') || #")
    public ResponseEntity<Page<User>> getAllUsersOfGroup(@PathVariable("uuid") UUID uuid, @PathVariable("offset") int offset, @PathVariable("pageSize") int pageSize) {
        return new ResponseEntity<>(groupService.getAllUsersOfGroup(uuid, offset, pageSize), HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    @PreAuthorize("hasAuthority('DELETE')")
    public void delete(@PathVariable UUID uuid) {
        groupService.delete(uuid);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Group> postMethod(@RequestBody Group group) throws InstanceAlreadyExistsException {
        return ResponseEntity.ok().body(groupService.saveGroup(group));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Group updateGroup(@PathVariable UUID id, @RequestBody Group group) {
        return groupService.updateGroup(id, group);
    }

}