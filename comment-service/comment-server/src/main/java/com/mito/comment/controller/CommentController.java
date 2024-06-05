package com.mito.comment.controller;


import com.mito.comment.pojo.po.Comment;
import com.mito.comment.pojo.vo.CommentVo;
import com.mito.comment.service.CommentService;
import com.mito.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author root
 * @since 2024-06-05
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    CommentService commentService;

    @GetMapping("/commentList")
    public Object commentList(
            @RequestParam("pageNum")Integer pageNum,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("articleId")Long articleId
    ) {

        return RestResult.ok().setContent(commentService.commentList(pageNum,pageSize,articleId));
    }

    @PostMapping
    public Object add(@RequestBody Comment comment){

        commentService.addComment(comment);

        return RestResult.ok();
    }
}
