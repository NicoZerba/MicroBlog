/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marconi.repos;

import com.marconi.entity.Comment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Nicolò
 */
@Repository
public interface CommentRepo extends JpaRepository<Comment, Long>{

    Comment save(Comment comment);
    @Transactional
    @Modifying(flushAutomatically = true)
    @Query("UPDATE Comment c SET c.contents = :contents WHERE c.id = :id") //Here we should not be using Table name, but Model/Entity name instead
    void update(@Param("id") Long id, @Param("contents") String contents);
    List<Comment> findAllByPost_Id(Long postId);
    @Transactional
    Optional<Long> deleteCommentById(Long id);
}
