package com.mito.comment.controller;


import com.mito.comment.pojo.po.Comment;
import com.mito.comment.pojo.vo.CommentQueryListVo;
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
            @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
            @RequestParam("articleId")Long articleId
    ) {

        return RestResult.ok().setContent(commentService.commentList("0",pageNum,pageSize,articleId));
    }

    @PostMapping
    public Object add(@RequestBody Comment comment){

        commentService.addComment(comment);

        return RestResult.ok();
    }

    @GetMapping("/linkCommentList")
    public Object linkCommentList(@RequestParam("pageNum")Integer pageNum,
                                  @RequestParam("pageSize") Integer pageSize){

        return RestResult.ok().setContent(commentService.commentList("1",pageNum,pageSize,null));
    }

    @GetMapping("/page")
    public Object getPage(
            @RequestParam(value = "pageNum",defaultValue = "1")Long pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10")Long pageSize,
            @RequestParam(value = "content",required = false)String content){


        return RestResult.ok().setContent(commentService.getPage(pageNum,pageSize,content));
    }

    @DeleteMapping("/{id}")
    public Object deleteById(@PathVariable("id")String id){

        commentService.deleteById(Long.parseLong(id));

        return RestResult.ok();
    }
}
