package com.example.demo.domain.group;

import com.example.demo.domain.appUser.User;
import com.example.demo.domain.appUser.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupServiceImplTest {

    @Autowired
    private GroupController groupController;

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void delete() {
        Group group1 = new Group(UUID.randomUUID(), "newUser", "newUser@gmail.com", Set.of());
        int count = groupRepository.findAll().size();

        try {
            groupService.delete(group1.getId());
        } catch (InstanceNotFoundException e) {
        }
        int after = groupRepository.findAll().size();
        assertFalse(after == count - 1);
    }

    @Test
    void saveGroup() {
        Group group1 = new Group(null, "newUser", "newUser@gmail.com", Set.of());
        int count = groupRepository.findAll().size();

        Group group2 = null;
        try {
            group2 = groupService.saveGroup(group1);
        } catch (InstanceAlreadyExistsException e) {
        }
        assert group2 != null;

        int after = groupRepository.findAll().size();
        assertTrue(after == count + 1);
    }

    @Test
    void findById() {
        Group group1 = new Group(UUID.randomUUID(), "newUser", "newUser@gmail.com", Set.of());

        try {
            group1 = groupService.saveGroup(group1);
        } catch (InstanceAlreadyExistsException e) {
        }

        assertTrue(groupRepository.existsById(group1.getId()));
    }

}