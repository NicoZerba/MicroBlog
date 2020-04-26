/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marconi.controllerImp;

import com.marconi.controllers.CommentController;
import com.marconi.dto.CreateCommentDTO;
import com.marconi.dto.EditCommentDTO;
import com.marconi.entity.BlogPost;
import com.marconi.entity.Comment;
import com.marconi.entity.User;
import com.marconi.repos.BlogPostRepo;
import com.marconi.repos.CommentRepo;
import com.marconi.repos.UserRepo;
import java.util.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nicolò
 */
@RestController
@RequestMapping("/comments/")
public class CommentControllerImpl implements CommentController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    BlogPostRepo blogPostRepo;

    @Override
    @RequestMapping(value = "{postId}", method = POST)
    public HttpStatus saveComment(@RequestBody CreateCommentDTO comment,
                                  @PathVariable Long postId) {
        try {
            commentRepo.save(new Comment(comment.getContents(),
                    userRepo.findUserByEmail(comment.getAuthorEmail()).orElse(User.getDefaultUser()),
                    blogPostRepo.findOneById(postId).orElse(BlogPost.getDefaultBlogPost())));
            return HttpStatus.CREATED;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    @RequestMapping(value = "{commentId}", method = PUT)
    public HttpStatus editComment(@RequestBody EditCommentDTO edit,
                                  @PathVariable("commentId") Long commentId) {
        try {
            commentRepo.update(commentId, edit.getContents());
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    @RequestMapping(value = "{id}", method = DELETE)
    public HttpStatus deleteComment(@PathVariable(name = "id") Long id) {
        try {
            commentRepo.deleteCommentById(id);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    @RequestMapping(value = "{postId}", method = GET)
    public String allCommentsOfOneBlogPost(@PathVariable("postId") Long postId) {
        return commentRepo.findAllByPost_Id(postId)
                .stream()
                .sorted(Comparator.comparing(Comment::getDate))
                .map(comment -> comment.toString() + "\n")
                .reduce("", (a, b) -> a + b);
    }
}

