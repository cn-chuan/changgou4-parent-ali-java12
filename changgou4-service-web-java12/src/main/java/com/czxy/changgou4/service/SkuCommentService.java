package com.czxy.changgou4.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czxy.changgou4.pojo.SkuComment;
import com.czxy.changgou4.vo.CommentResult;
import com.czxy.changgou4.vo.PageRequest;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
public interface SkuCommentService extends IService<SkuComment> {

    public CommentResult findCommentsBySpuId(Integer spuId, PageRequest pageRequest);
}
