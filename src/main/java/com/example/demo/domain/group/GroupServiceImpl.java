package com.example.demo.domain.group;

import com.example.demo.domain.appUser.User;
import com.example.demo.domain.appUser.UserRepository;
import com.example.demo.domain.appUser.UserService;
import com.example.demo.domain.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
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
    public Page<User> getAllUsersOfGroup(UUID uuid, int offset, int pageSize) {
        Pageable paging = PageRequest.of(offset, pageSize);
        Page<User> userPage = this.userRepository.findAllUserByGroupId(uuid, paging);
        userRepository.findAll();
        return userPage;
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
            group.getUsers().add(user);
            groupRepository.save(group);
        }else {
            System.out.println("Already in group");
        }
    }

    @Override
    public Group findById(UUID id) {
            Optional<Group> group = groupRepository.findById(id);
            return group.orElse(null);
    }

    @Override
    public Group updateGroup(UUID id, Group group) {
        return groupRepository.findById(id)
                .map(group1 -> {
                    group1.setName(group.getName());
                    group1.setDescription(group.getDescription());
                    group1.setUsers(group.getUsers());
                    return groupRepository.save(group);
                }).orElseGet(() -> {
                    return groupRepository.save(group);
                });
    }
}