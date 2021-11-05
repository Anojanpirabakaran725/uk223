package com.example.demo;
import com.example.demo.domain.appUser.User;
import com.example.demo.domain.appUser.UserService;
import com.example.demo.domain.authority.Authority;
import com.example.demo.domain.authority.AuthorityRepository;
import com.example.demo.domain.role.Role;
import com.example.demo.domain.role.RoleRepository;
import com.example.demo.domain.role.RoleServiceImpl;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;


@Component
@RequiredArgsConstructor
//ApplicationListener used to run commands after startup
class AppStartupRunner implements ApplicationRunner {
    @Autowired
    private final UserService userService;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final AuthorityRepository authorityRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        RUN YOUR STARTUP CODE HERE
//        e.g. to add a user or role to the DB (only for testing)

//        Authorities
        Authority read_auth = new Authority(null,"READ");
        authorityRepository.save(read_auth);

        Authority write_auth = new Authority(null, "WRITE");
        authorityRepository.save(write_auth);

        Authority all_auth = new Authority(null,"ALLRIGHTS");
        authorityRepository.save(all_auth);

//        Roles
        Role admin_role = new Role(null, "Admin", Arrays.asList(all_auth));
        roleRepository.save(admin_role);

        Role user_role = new Role(null, "User", Arrays.asList(read_auth, write_auth, write_auth));
        roleRepository.save(user_role);

        Role guest_role = new Role(null, "Guest", Arrays.asList(read_auth, write_auth, read_auth));
        roleRepository.save(guest_role);

        userService.saveUser(new User(null, "james","james.bond@mi6.com","bond", Set.of(admin_role)));
        userService.addRoleToUser("james", "Admin");
    }
}

