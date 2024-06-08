package com.mito.comment.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mito.comment.pojo.po.Board;
import com.mito.comment.service.BoardService;
import com.mito.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author root
 * @since 2024-06-08
 */
@RestController
@RequestMapping("/board")
public class BoardController {

    @Resource
    BoardService boardService;

    @GetMapping
    public Object getNotices(){
        LambdaQueryWrapper<Board> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Board::getUpdateTime);
        List<Board> list = boardService.list(wrapper);

        return RestResult.ok().setContent(list.get(0));
    }

}
