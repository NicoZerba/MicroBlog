/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marconi.repos;

import com.marconi.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nicolò
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User save(User user);
    Optional<User> findByUsername(String username);
    Optional<User> findUserByEmail(String email);

}
