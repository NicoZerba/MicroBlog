/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marconi.controllerImp;

import com.marconi.controllers.UserController;
import com.marconi.dto.CreateUserDTO;
import com.marconi.entity.User;
import com.marconi.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nicolò
 */
@RestController
@RequestMapping("/users/")
public class UserControllerImpl implements UserController {

    @Autowired
    UserRepo userRepo;

    @Override
    @RequestMapping(value = "", method = POST)
    public HttpStatus addNewUser(@RequestBody CreateUserDTO user) {
        try {
            userRepo.save(new User(user.getUsername(), user.getPassword(), user.getEmail()));
            return HttpStatus.CREATED;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    @RequestMapping(value = "{username}", method = GET)
    public String findUserByName(@PathVariable("username") String username) {
        return userRepo.findByUsername(username).orElse(User.getDefaultUser()).toString();
    }


}
