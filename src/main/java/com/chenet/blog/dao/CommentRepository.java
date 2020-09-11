/**
 * FileName: CommentRepository
 * Author:   嘉平十七
 * Date:     2020/8/23 15:08
 * Description: 评论
 */
package com.chenet.blog.dao;

import com.chenet.blog.domain.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}