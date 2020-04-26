/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marconi.controllerImp;

import com.marconi.controllers.BlogpostController;
import com.marconi.dto.CreateBlogPostDTO;
import com.marconi.dto.EditBlogPostDTO;
import com.marconi.entity.BlogPost;
import com.marconi.entity.User;
import com.marconi.repos.BlogPostRepo;
import com.marconi.repos.UserRepo;
import java.util.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nicolò
 */
@RestController
@RequestMapping("/posts/")
public class BlogPostControllerImpl implements BlogpostController {

    @Autowired
    BlogPostRepo blogPostRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    @RequestMapping(value = "", method = GET)
    public String getAllBlogPosts() {
        return blogPostRepo.findAll()
                .stream()
                .sorted(Comparator.comparing(BlogPost::getDate))
                .map(post -> post.toString() + "\n")
                .reduce("", (a, b) -> a + b);
    }

    @Override
    @RequestMapping(value = "post/{id}", method = GET)
    public String getOneBlogPost(@PathVariable("id") Long postId) {
        return blogPostRepo.findOneById(postId)
                .orElse(BlogPost.getDefaultBlogPost())
                .toString();
    }

    @Override
    @RequestMapping(value = "{username}", method = GET)
    public String getAllBlogPostsByUser(@PathVariable("username") String username) {
        return blogPostRepo.findAllByAuthorUsername(username)
                .stream()
                .sorted(Comparator.comparing(BlogPost::getDate))
                .map(post -> post.toString() + "\n")
                .reduce("", (a, b) -> a + b);
    }


    @Override
    @RequestMapping(value = "/create", method = POST)
    public HttpStatus saveBlogPost(@RequestBody CreateBlogPostDTO blogPost) {
        try {
            blogPostRepo.save(new BlogPost(blogPost.getContents(),
                    userRepo.findUserByEmail(blogPost.getAuthorEmail()).orElse(User.getDefaultUser())))
                    .getUser().getEmail();
            return HttpStatus.CREATED;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }


    @Override
    @RequestMapping(value = "post/{postId}", method = PUT)
    public HttpStatus editBlogPost(@RequestBody EditBlogPostDTO edit,
                                   @PathVariable("postId") Long postId) {
        try {
            blogPostRepo.update(postId, edit.getContents());
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}