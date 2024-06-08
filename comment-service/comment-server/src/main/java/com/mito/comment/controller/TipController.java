package com.mito.comment.controller;


import com.mito.comment.pojo.po.Tip;
import com.mito.comment.service.TipService;
import com.mito.common.result.RestResult;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author root
 * @since 2024-06-08
 */
@RestController
@RequestMapping("/tip")
public class TipController {

    @Resource
    TipService tipService;

    @GetMapping
    public Object tip(){

        return RestResult.ok().setContent(tipService.tip());
    }

}
