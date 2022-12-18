package com.czxy.changgou4.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czxy.changgou4.pojo.Sku;
import com.czxy.changgou4.vo.ESData;
import com.czxy.changgou4.vo.OneSkuResult;

import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
public interface SkuService extends IService<Sku> {

    public List<ESData> findESData();

    public OneSkuResult findSkuById(Integer skuId);

    /**
     * 更新库存
     * @param skuId 商品的id
     * @param count 购买的数量
     * @author 桐叔
     * @email liangtong@itcast.cn
     * @return
     */
    public void updateSkuNum(Integer skuId, Integer count);
}
