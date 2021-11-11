package com.example.demo.domain.group;

import com.example.demo.domain.appUser.User;
import com.example.demo.domain.appUser.UserRepository;
import com.example.demo.domain.appUser.UserService;
import com.example.demo.domain.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private final GroupRepository groupRepository;
    @Autowired
    private final UserRepository userRepository;

    public GroupServiceImpl(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public void delete(UUID uuid) {
        groupRepository.delete(findById(uuid));
    }

    /*@Override
    public Group put(Group newGroup, UUID uuid) throws InstanceNotFoundException {
        Optional<Group> foundGroup = groupRepository.findById(uuid);
        if (foundGroup.isPresent()){
            foundGroup.get().setName(newGroup.getName());
            foundGroup.get().setDescription(newGroup.getDescription());
            foundGroup.get().setUsers(newGroup.getUsers());

        }else {
            throw new InstanceNotFoundException("");
        }

        return groupRepository.save(foundGroup.get());
    }*/

    @Override
    public Group saveGroup(Group group) throws InstanceAlreadyExistsException {
        if (groupRepository.findByName(group.getName()) != null){
            throw new InstanceAlreadyExistsException("Group already exists");
        }
        else {
            return groupRepository.save(group);
        }
    }

    @Override
    public Group getGroup(String groupName) {
        return groupRepository.findByName(groupName);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void addUserToGroup(String username, String groupName) {
        User user = userRepository.findByUsername(username);
        Group group = groupRepository.findByName(groupName);
        if (user.getGroup() == null){
            user.setGroup(group);
        }else {
            System.out.println("Already in group");
        }
    }

    @Override
    public Group findById(UUID id) {
            Optional<Group> group = groupRepository.findById(id);
            return group.orElse(null);
    }
}