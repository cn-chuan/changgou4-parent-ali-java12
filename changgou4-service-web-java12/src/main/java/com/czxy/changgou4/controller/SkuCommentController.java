package com.czxy.changgou4.controller;

import com.czxy.changgou4.service.SkuCommentService;
import com.czxy.changgou4.vo.BaseResult;
import com.czxy.changgou4.vo.CommentResult;
import com.czxy.changgou4.vo.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@RestController
@RequestMapping("/comments")
public class SkuCommentController {

    @Resource
    private SkuCommentService skuCommentService;

    @GetMapping("/spu/{spuId}")
    public BaseResult findComments(@PathVariable("spuId") Integer spuId, PageRequest pageRequest) {
        // 查询
        CommentResult commentList = skuCommentService.findCommentsBySpuId(spuId, pageRequest);

        // 返回
        return BaseResult.ok("查询成功", commentList);
    }
}
