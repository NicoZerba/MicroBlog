/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marconi.entity;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Nicolò
 */
@Entity
@Table(name="comments")
public class Comment implements Serializable {

    private static final long serialVersionUID = -3009157732242241606L;
    private static final Comment defaultComment
            = new Comment("Default post", User.getDefaultUser(), BlogPost.getDefaultBlogPost());

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "contents")
    private String contents;

    @ManyToOne(targetEntity = BlogPost.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private BlogPost post;

    @ManyToOne(targetEntity = User.class,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="author_email", referencedColumnName = "email")
    private User author;

    @Column(name = "date")
    private Date date;

    public Comment() {} 

    public Comment(String contents, User user, BlogPost post) {
        //this();
        this.contents = contents;
        this.author = user;
        this.post = post;
        this.date = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public BlogPost getPost() {
        return post;
    }

    public void setPost(BlogPost post) {
        this.post = post;
    }

    public User getUser() {
        return author;
    }

    public void setUser(User user) {
        this.author = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static Comment getDefaultComment() {
        return defaultComment;
    }

    @Override
    public String toString() {
        return "{\'id\':" + id + ","
                + "\'contents\':\'" + contents + "\',"
                + "\'email\':\'"+ author.getEmail() + "\',"
                + "\'date\':\'" + date + "\',"
                + "\'postId\':" + post.getId() + "}";
    }

}
