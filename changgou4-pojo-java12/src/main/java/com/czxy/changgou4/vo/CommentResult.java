package com.czxy.changgou4.vo;

import com.czxy.changgou4.pojo.Impression;
import com.czxy.changgou4.pojo.SkuComment;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Data
public class CommentResult {
    private List<Impression> impressions;       //印象
    private Map<String,Object> ratio;           //好评度
    private Integer comment_count;              //评论数
    private List<SkuComment> comments;          //评论

}

