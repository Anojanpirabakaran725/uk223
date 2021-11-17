package com.example.demo.domain.group;

import com.example.demo.domain.appUser.User;
import com.example.demo.domain.appUser.UserRepository;
import com.example.demo.domain.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
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
    public String delete(UUID uuid) throws InstanceNotFoundException {
        if (groupRepository.existsById(uuid)){
            groupRepository.delete(findById(uuid));
            return "Group deleted";
        } else {
            throw new InstanceNotFoundException("Group not found");
        }
    }

    @Override
    public Group saveGroup(Group group) throws InstanceAlreadyExistsException {
        if (groupRepository.findByName(group.getName()) != null) {
            throw new InstanceAlreadyExistsException("Group already exists");
        } else {
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
        if (user.getGroup() == null) {
            group.getUsers().add(user);
            groupRepository.save(group);
        } else {
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
                    groupRepository.deleteById(id);
                    group1.setName(group.getName());
                    group1.setDescription(group.getDescription());
                    group1.setUsers(group.getUsers());
                    return groupRepository.save(group1);
                }).orElseGet(() -> {
                    group.setId(id);
                    return groupRepository.save(group);
                });
    }

    @Override
    public boolean isUserAuthorizedForGroup(UUID uuid) {
        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findByUsername(((UserDetails)auth).getUsername());

        for (Role role : currentUser.getRoles()) {
            if (role.getName().equals("ADMIN")){
                return true;
            }
        }

        if (currentUser.getGroup().getId().equals(uuid)){
            return true;
        }else {
            return false;
        }
    }
}