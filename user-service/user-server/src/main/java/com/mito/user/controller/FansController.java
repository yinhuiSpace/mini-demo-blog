package com.mito.user.controller;


import com.mito.common.result.RestResult;
import com.mito.user.service.FansService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author root
 * @since 2024-06-10
 */
@RestController
@RequestMapping("/fans")
public class FansController {

    @Resource
    FansService fansService;

    @GetMapping("/count")
    public Object count(@RequestParam("id")String id){

        return RestResult.ok().setContent(fansService.fansCount(Long.parseLong(id)).toString());
    }

}
