package com.serend.blog.service;

import com.serend.blog.dao.CommentRepository;
import com.serend.blog.po.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class CommentServiceImp implements CommentService{

    @Autowired
    CommentRepository commentRepository;

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
        if (parentCommentId!=-1){
            comment.setParentComment(commentRepository.getOne(parentCommentId));
        }else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }

    private List<Comment> eachComment(List<Comment> comments){
        List<Comment> commentView = new ArrayList<>();
        for (Comment comment:comments){
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentView.add(c);
        }
        combineChildren(commentView);
        return commentView;
    }

    private List<Comment> tempReplys = new ArrayList<>();

    private void combineChildren(List<Comment> commentView) {
        for (Comment comment:commentView){
            List<Comment> replys1 = comment.getReplyComment();
            for (Comment reply1:replys1){
                recursively(reply1);
            }
            comment.setReplyComment(tempReplys);
            tempReplys = new ArrayList<>();
        }
    }

    private void recursively(Comment comment) {
        tempReplys.add(comment);
        if (comment.getReplyComment().size()>0){
            List<Comment> replys = comment.getReplyComment();
            for (Comment reply:replys){
                tempReplys.add(reply);
                if (reply.getReplyComment().size()>0){
                    recursively(reply);
                }
            }
        }
    }
}
