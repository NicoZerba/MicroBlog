/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marconi.dto;

/**
 *
 * @author Nicolò
 */
public class EditUserDTO {

    private String username;
    private String email;
    private String passwd;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() { return passwd; }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
