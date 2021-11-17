package com.example.demo.domain.group;

import com.example.demo.domain.appUser.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger log = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    /**
     * Endpoint to get all groups
     *
     * @return -> All groups
     */
    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('READ', 'WRITE', 'DELETE', 'ALL_PRIVILEGES')")
    public ResponseEntity<Collection<Group>> findAll() {
        log.info("findAll ENDPOINT GOT USED");
        return new ResponseEntity<Collection<Group>>(groupService.findAll(), HttpStatus.OK);
    }

    /**
     * Endpoint to get a group by its id
     *
     * @return -> Group which has the id
     */
    @GetMapping("/{uuid}")
    @PreAuthorize("hasAnyAuthority('READ', 'WRITE', 'DELETE', 'ALL_PRIVILEGES')")
    public ResponseEntity<Group> findById(@PathVariable("uuid") UUID uuid) {
        log.info("findById ENDPOINT GOT USED");
        return new ResponseEntity<Group>(groupService.findById(uuid), HttpStatus.OK);
    }

    /**
     * Endpoint to get all users of a group
     *
     * @return -> All users of group
     */
    @GetMapping("/{uuid}/{offset}/{pageSize}")
    @PreAuthorize("@groupServiceImpl.isUserAuthorizedForGroup(#uuid)")
    public ResponseEntity<Page<User>> getAllUsersOfGroup(@PathVariable("uuid") UUID uuid, @PathVariable("offset") int offset, @PathVariable("pageSize") int pageSize) {
        log.info("getAllUsersOfGroup ENDPOINT GOT USED");
        return new ResponseEntity<>(groupService.getAllUsersOfGroup(uuid, offset, pageSize), HttpStatus.OK);
    }

    /**
     * Endpoint to delete a group by its id
     *
     * @return -> Message if it got deleted
     */
    @DeleteMapping("/{uuid}")
    @PreAuthorize("hasAuthority('ALL_PRIVILEGES')")
    public ResponseEntity<String> delete(@PathVariable UUID uuid) throws InstanceNotFoundException {
        log.info("DELETE ENDPOINT GOT USED");
        return ResponseEntity.ok().body(groupService.delete(uuid));
    }

    /**
     * Endpoint to post a group
     *
     * @return -> Data of group that got inserted
     */
    @PostMapping("/")
    @PreAuthorize("hasAuthority('ALL_PRIVILEGES')")
    public String postMethod(@RequestBody Group group) throws InstanceAlreadyExistsException {
        log.info("POST ENDPOINT GOT USED");
        ResponseEntity.ok().body(groupService.saveGroup(group));
        return "Group got inserted";
    }

    /**
     * Endpoint to update a group by its id
     *
     * @return -> Data of group that got updated
     */
    @PutMapping("/{uuid}")
    @PreAuthorize("@groupServiceImpl.isUserAuthorizedForGroup(#uuid)")
    public String updateGroup(@PathVariable UUID uuid, @RequestBody Group group) {
        log.info("PUT ENDPOINT GOT USED");
        return groupService.updateGroup(uuid, group);
    }

}