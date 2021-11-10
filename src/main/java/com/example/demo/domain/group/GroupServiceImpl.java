package com.example.demo.domain.group;

import com.example.demo.domain.appUser.User;
import com.example.demo.domain.appUser.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    @Autowired
    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

}
