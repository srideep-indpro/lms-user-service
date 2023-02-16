package com.vlabs.lmsuser.controller;

import com.vlabs.lmsuser.model.Role;
import com.vlabs.lmsuser.model.User;
import com.vlabs.lmsuser.model.UserDto;
import com.vlabs.lmsuser.model.UserLoginDto;
import com.vlabs.lmsuser.repository.RoleRepository;
import com.vlabs.lmsuser.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.apache.logging.log4j.util.Base64Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("${lms.rest.base-url}")
@Slf4j
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private final String ROLE_USER = "ROLE_USER";


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        Role role_user = createRoleIfNotFound(ROLE_USER);
        user.setRoles( Set.of(role_user));
        role_user.setUsers(Set.of(user));
        user.setUserPassword(Base64.getEncoder().encodeToString(user.getUserPassword().getBytes()));
        log.info("Inside registerUser method of UserController");
        User savedUser = userRepository.save(user);
        log.info("New user saved with id: " + savedUser.getUserID());
        return ResponseEntity.ok("User saved successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDto user) {
        User userByName = userRepository.findByUserName(user.getUserName());
        if(userByName==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist.");
        }
        if(Base64.getDecoder().decode(userByName.getUserPassword()).equals(user.getPassword())){
            log.info("User login successful" + user.getUserName());
        }
        Set<Role> roles = userByName.getRoles();
        List<String> userRolesToDisplay = new ArrayList<>();
        roles.forEach(role->{
            userRolesToDisplay.add(role.getRoleName());
        });

        UserDto build = UserDto.builder().userID(userByName.getUserID())
                .userName(userByName.getUserName())
                .userEmail(userByName.getUserEmail())
                .userRole(userRolesToDisplay)
                .build();

        return ResponseEntity.ok(build);
    }

    @GetMapping("/getUserInfo")
    public ResponseEntity<?> getUserRoleInfo(String userName){
        User userByName = userRepository.findByUserName(userName);
        if(userByName == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User could not be found");
        }

        System.out.println(userByName);
        Set<Role> roles = userByName.getRoles();
        List<String> userRolesToDisplay = new ArrayList<>();
        roles.forEach(role->{
            userRolesToDisplay.add(role.getRoleName());
        });

        UserDto build = UserDto.builder().userID(userByName.getUserID())
                .userName(userByName.getUserName())
                .userEmail(userByName.getUserEmail())
                .userRole(userRolesToDisplay)
                .build();

        return ResponseEntity.ok(build);
    }

    private Role createRoleIfNotFound(String name) {
        Role roleByName = roleRepository.findByRoleName(name);
        if(roleByName == null){
            Role role = new Role();
            role.setRoleName(name);
            roleByName = roleRepository.save(role);
        }
        return roleByName;
    }


}
