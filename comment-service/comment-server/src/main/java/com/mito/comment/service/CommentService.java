package com.mito.comment.service;

import com.mito.comment.pojo.po.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mito.comment.pojo.vo.CommentListVo;
import com.mito.comment.pojo.vo.CommentQueryListVo;
import com.mito.comment.pojo.vo.CommentVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author root
 * @since 2024-06-05
 */
public interface CommentService extends IService<Comment> {


    CommentListVo commentList(String commentType,Integer pageNum, Integer pageSize, Long articleId);

    void addComment(Comment comment);

    CommentQueryListVo getPage(Long pageNum, Long pageSize, String content);

    void deleteById(Long id);
}
