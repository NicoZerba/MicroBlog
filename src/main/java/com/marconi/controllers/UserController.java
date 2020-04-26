/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marconi.controllers;

import com.marconi.dto.CreateUserDTO;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Nicolò
 */
public interface UserController {

    HttpStatus addNewUser(CreateUserDTO user);

    String findUserByName(String username);
}

