package com.fdmgroup.RecipeManagementStstem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdmgroup.RecipeManagementStstem.model.User;
import com.fdmgroup.RecipeManagementStstem.repository.UserRepository;


@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired private UserRepository userRepo;

    @GetMapping("/info")
    public User getUserDetails(){
        String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepo.findByUserName(userName).get();
    }

}
