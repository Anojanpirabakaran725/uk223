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

import java.util.Arrays;
import java.util.Set;


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

        Authority exec_auth = new Authority(null,"EXECUTE");
        authorityRepository.save(exec_auth);

//        Roles
        Role admin_role = new Role(null, "Admin",Arrays.asList(read_auth, write_auth, exec_auth));
        roleRepository.save(admin_role);

        Role user_role = new Role(null, "User",Arrays.asList(read_auth, write_auth));
        roleRepository.save(user_role);

        Role guest_role = new Role(null, "Guest",Arrays.asList(read_auth));
        roleRepository.save(guest_role);

        //Users
        User user1 = new User(null, "james","james.bond@mi6.com","bond", Set.of(admin_role));
        userService.saveUser(user1);
        userService.addRoleToUser("james", "Admin");

        User user2 = new User(null, "max","max.muster@gmail.com","muster", Set.of(user_role));
        userService.saveUser(user2);
        userService.addRoleToUser("max", "Admin");

        User user3 = new User(null, "john","john.doe@yahoo.com","doe", Set.of(guest_role));
        userService.saveUser(user3);
        userService.addRoleToUser("john", "Admin");

        //Groups
        Group group1 = new Group(null, "Admins", "Good group", Set.of(user1));
        groupService.saveGroup(group1);

        Group group2 = new Group(null, "Users", "Good group", Set.of(user2));
        groupService.saveGroup(group2);

        Group group3 = new Group(null, "Guests", "Good group", Set.of(user3));
        groupService.saveGroup(group3);


        groupService.addUserToGroup(user1.getUsername(), group1.getName());
        groupService.addUserToGroup(user2.getUsername(), group2.getName());
        groupService.addUserToGroup(user3.getUsername(), group3.getName());

    }
}

