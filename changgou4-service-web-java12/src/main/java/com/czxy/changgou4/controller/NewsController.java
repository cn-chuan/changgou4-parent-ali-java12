package com.czxy.changgou4.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czxy.changgou4.pojo.News;
import com.czxy.changgou4.service.NewsService;
import com.czxy.changgou4.vo.BaseResult;
import com.czxy.changgou4.vo.NewsVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@RestController
@RequestMapping("/news")
public class NewsController {

    @Resource
    private NewsService newsService;

    //  /news?current=1&size=3
    @GetMapping
    public BaseResult<List<News>> findAll(NewsVo newsVo) {
        // 查询
        Page<News> page = newsService.findAll(newsVo);

        // 返回
        return BaseResult.ok("查询成功", page);
    }
}
