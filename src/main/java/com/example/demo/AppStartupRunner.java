package com.example.demo;
import com.example.demo.domain.appUser.User;
import com.example.demo.domain.appUser.UserService;
import com.example.demo.domain.authority.Authority;
import com.example.demo.domain.authority.AuthorityRepository;
import com.example.demo.domain.group.Group;
import com.example.demo.domain.group.GroupService;
import com.example.demo.domain.role.Role;
import com.example.demo.domain.role.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;


@Component
//ApplicationListener used to run commands after startup
class AppStartupRunner implements ApplicationRunner {
    @Autowired
    private final UserService userService;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final AuthorityRepository authorityRepository;
    @Autowired
    private final GroupService groupService;

    AppStartupRunner(UserService userService, RoleRepository roleRepository, AuthorityRepository authorityRepository, GroupService groupService) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.authorityRepository = authorityRepository;
        this.groupService = groupService;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
//        RUN YOUR STARTUP CODE HERE
//        e.g. to add a user or role to the DB (only for testing)

//        Authorities
        Authority read_auth = new Authority(null,"READ");
        authorityRepository.save(read_auth);

        Authority write_auth = new Authority(null,"WRITE");
        authorityRepository.save(write_auth);

        Authority delete_auth = new Authority(null,"DELETE");
        authorityRepository.save(delete_auth);

//        Roles
        Role admin_role = new Role(null, "ADMIN",Arrays.asList(read_auth, write_auth, delete_auth));
        roleRepository.save(admin_role);

        Role user_role = new Role(null, "USER",Arrays.asList(read_auth, write_auth));
        roleRepository.save(user_role);

        Role guest_role = new Role(null, "GUEST",Arrays.asList(read_auth));
        roleRepository.save(guest_role);

        //Groups
        Group group1 = new Group(null, "Admins", "Good group", Set.of());
        Group group2 = new Group(null, "Users", "Good group", Set.of());

        group1 = groupService.saveGroup(group1);
        group2 = groupService.saveGroup(group2);

        //Users
        User user1 = new User(null, "james","james.bond@mi6.com","bond", Set.of(admin_role), null);
        User user2 = new User(null, "john","john.doe@yahoo.com","doe", Set.of(guest_role), null);

        userService.saveUser(user1);
        userService.saveUser(user2);

        groupService.addUserToGroup("james", "Admins");

        //groupService.put(group1, group1.getId());
    }
}