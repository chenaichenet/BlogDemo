/**
 * FileName: CommemtServiceImpl
 * Author:   嘉平十七
 * Date:     2020/8/23 15:08
 * Description:
 */
package com.chenet.blog.service.impl;

import com.chenet.blog.dao.CommentRepository;
import com.chenet.blog.domain.Comment;
import com.chenet.blog.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = Sort.by(Sort.Direction.ASC,"createTime");
        List<Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(blogId,sort);
        return eachComment(comments);
    }

    @Transactional
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId != -1){
            comment.setParentComment(commentRepository.getOne(parentCommentId));
        }else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }


    /*处理评论区的层级关系*/

    //存放迭代找出的所有子代集合
    private List<Comment> tempReplys = new ArrayList<>();
    /**
     * 循环每个顶级评论节点
     * @param comments
     * @return
     */
    private List<Comment> eachComment(List<Comment> comments){
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments){
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        //合并评论的各级子代到第一级子代集合中
        comblinChildren(commentsView);
        return commentsView;
    }

    /**
     *
     * @param comments root根节点，blog不为空的对象集合
     */
    private void comblinChildren(List<Comment> comments){
        for (Comment comment : comments){
            List<Comment> replys1 = comment.getReplyComments();
            for (Comment reply1 : replys1){
                //循环迭代，找出子代，存放到tempReplys中
                recurisvely(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    /**
     * 递归迭代
     * @param comment
     */
    private void recurisvely(Comment comment){
        tempReplys.add(comment);    //顶节点添加到临时存放的集合
        if (comment.getReplyComments().size()>0){
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys){
                tempReplys.add(reply);
                if (reply.getReplyComments().size()>0){
                    recurisvely(reply);
                }
            }
        }
    }
}