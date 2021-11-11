package com.example.demo.domain.group;

import com.example.demo.domain.appUser.User;
import com.example.demo.domain.role.Role;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GroupService {
    Group saveGroup(Group group) throws InstanceAlreadyExistsException;
    Group getGroup(String username);
    User saveUser(User user);
    void addUserToGroup(String username, String groupName);
    Group findById(UUID id);
    List<Group> findAll();
    Group put(Group group, UUID uuid);
}
