/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marconi.controllers;

import com.marconi.dto.CreateBlogPostDTO;
import com.marconi.dto.EditBlogPostDTO;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Nicolò
 */
public interface BlogpostController {

    String getAllBlogPosts();

    String getOneBlogPost(Long postId);

    String getAllBlogPostsByUser(String username);

    HttpStatus saveBlogPost(CreateBlogPostDTO blogPost);

    HttpStatus editBlogPost(EditBlogPostDTO edit, Long postId);

}