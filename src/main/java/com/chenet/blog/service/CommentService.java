/**
 * FileName: CommentService
 * Author:   嘉平十七
 * Date:     2020/8/23 15:06
 * Description:
 */
package com.chenet.blog.service;

import com.chenet.blog.domain.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
