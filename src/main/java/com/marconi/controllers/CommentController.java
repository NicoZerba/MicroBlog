/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marconi.controllers;

import com.marconi.dto.CreateCommentDTO;
import com.marconi.dto.EditCommentDTO;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Nicolò
 */
public interface CommentController {

    HttpStatus saveComment(CreateCommentDTO comment, Long postId);

    HttpStatus editComment(EditCommentDTO edit, Long commentId);

    HttpStatus deleteComment(Long id);

    String allCommentsOfOneBlogPost(Long postId);
}

