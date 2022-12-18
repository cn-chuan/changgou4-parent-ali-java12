package com.czxy.changgou4.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czxy.changgou4.mapper.ImpressionMapper;
import com.czxy.changgou4.mapper.SkuCommentMapper;
import com.czxy.changgou4.pojo.Impression;
import com.czxy.changgou4.pojo.SkuComment;
import com.czxy.changgou4.service.SkuCommentService;
import com.czxy.changgou4.vo.CommentResult;
import com.czxy.changgou4.vo.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Service
@Transactional
public class SkuCommentServiceImpl extends ServiceImpl<SkuCommentMapper, SkuComment> implements SkuCommentService {
    @Resource
    private ImpressionMapper impressionMapper;
    @Override
    public CommentResult findCommentsBySpuId(Integer spuId, PageRequest pageRequest) {
        //1 创建封装对象
        CommentResult commentResult = new CommentResult();

        //2 封装：好评度
        // 2.1 查询指定标识好评度个数 + 评论总数
        Integer goodsCount = baseMapper.findCommentCountByRatio(spuId, 0);
        Integer commonCount = baseMapper.findCommentCountByRatio(spuId, 1);
        Integer badCount = baseMapper.findCommentCountByRatio(spuId, 2);
        Integer total = baseMapper.findTotalCommentBySpuId(spuId);

        // 2.2 处理好评度数据
        Map<String, Object> ratioMap = new HashMap<>();
        ratioMap.put("goods", String.format("%.2f", goodsCount * 100d / total));
        ratioMap.put("common", String.format("%.2f", commonCount * 100d / total));
        ratioMap.put("bad", String.format("%.2f", badCount * 100d / total));
        commentResult.setRatio(ratioMap);

        //3 封装：印象
        List<Impression> impressionList = impressionMapper.findImpressionsBySpuId(spuId);
        commentResult.setImpressions(impressionList);

        //4 封装：评论详情
        // 4.1 总数
        commentResult.setComment_count(total);
        // 4.2 列表（分页）
        // * 计算开始索引
        int startIndex = (pageRequest.getCurrent() - 1) * pageRequest.getSize();
        List<SkuComment> commentList = baseMapper.findCommentsBySpuId(spuId, startIndex, pageRequest.getSize());
        commentResult.setComments(commentList);

        //5 返回
        return commentResult;
    }
}
