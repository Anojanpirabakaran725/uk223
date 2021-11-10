package com.example.demo.domain.group;

import com.example.demo.domain.appUser.User;
import com.example.demo.domain.appUser.UserRepository;
import com.example.demo.domain.appUser.UserService;
import com.example.demo.domain.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
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
        group.getUsers().add(user);
    }

    @Override
    public Optional<Group> findById(UUID id) throws InstanceNotFoundException {
        if (groupRepository.existsById(id)){
            return groupRepository.findById(id);
        }
        else{
            throw new InstanceNotFoundException("Group not found");
        }
    }

}
