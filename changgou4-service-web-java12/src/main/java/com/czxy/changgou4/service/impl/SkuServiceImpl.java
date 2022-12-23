package com.czxy.changgou4.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czxy.changgou4.mapper.*;
import com.czxy.changgou4.pojo.*;
import com.czxy.changgou4.service.CategoryService;
import com.czxy.changgou4.service.SkuService;
import com.czxy.changgou4.vo.ESData;
import com.czxy.changgou4.vo.OneSkuResult;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

    @Resource
    private SkuCommentMapper skuCommentMapper;

    @Resource
    private SpuMapper spuMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private SkuPhotoMapper skuPhotoMapper;

    @Resource
    private SpecMapper specMapper;


    @Override
    public List<ESData> findESData() {
        //1 查询所有的sku
        List<Sku> skuList = baseMapper.findAllSkus();

        //2 处理数据：SKU --> ESData
        List<ESData> esDataList = new ArrayList<>();
        // 2.1 遍历sku，进行处理
        for (Sku sku : skuList) {
            ESData esData = new ESData();
            // 2.2 基本数据
            esData.setId(sku.getId());
            esData.setLogo(sku.getSpu().getLogo());
            esData.setSkuName(sku.getSkuName());
            // 用于全文检索：sku名称 + 规格列表文本 + 品牌
            esData.setAll(sku.getSkuName() + " " + sku.getSpecInfoIdTxt() + " " + sku.getSpu().getBrand().getBrandName());
            esData.setOnSaleTime(sku.getSpu().getOnSaleTime());
            esData.setBrandId(sku.getSpu().getBrandId());
            esData.setCatId(sku.getSpu().getCat3Id());
            esData.setPrice(sku.getPrice());
            esData.setSpuName(sku.getSpu().getSpuName());
            esData.setStock(sku.getStock());
            esData.setDescription(sku.getSpu().getDescription());
            esData.setPackages(sku.getSpu().getPackages());
            esData.setAftersale(sku.getSpu().getAftersale());

            esData.setMidlogo(sku.getSpu().getLogo());

            // 2.3 规格数据
            Map<String,Object> specMap = JSON.parseObject(sku.getSpecInfoIdTxt(), Map.class);
            esData.setSpecs(specMap);

            // 2.4 品论数
            Integer commentCount = skuCommentMapper.findNumBySpuId(sku.getSpuId());
            esData.setCommentCount(commentCount);

            // 2.5 销量数（随机生成）
            esData.setSellerCount(RandomUtils.nextInt(100, 1000));

            esDataList.add(esData);
        }

        //3 返回
        return esDataList;
    }

    @Override
    public OneSkuResult findSkuById(Integer skuId) {
        //1 封装对象
        OneSkuResult oneSkuResult = new OneSkuResult();

        //2.1 查询sku详情、spu详情
        Sku sku = baseMapper.selectById(skuId);
        Spu spu = spuMapper.findSpuById(sku.getSpuId());


        //2.2 设置基本信息
        oneSkuResult.setSkuid(sku.getId());
        oneSkuResult.setSpuid(sku.getSpuId());
        oneSkuResult.setGoodsName(sku.getSkuName());
        oneSkuResult.setPrice(sku.getPrice());
        oneSkuResult.setOnSaleDate(spu.getOnSaleTime());
        oneSkuResult.setDescription(spu.getDescription());
        oneSkuResult.setAftersale(spu.getAftersale());
        oneSkuResult.setStock(sku.getStock());

        // 3 品论
        Integer commentCount = skuCommentMapper.findNumBySpuId(sku.getSpuId());
        oneSkuResult.setCommentCount(commentCount);
        Integer commentLevel = skuCommentMapper.findAvgStarBySpuId(sku.getSpuId());
        oneSkuResult.setCommentLevel(commentLevel);

        // 4 分类
        Category cat1 = categoryMapper.selectById(spu.getCat1Id());
        oneSkuResult.setCat1Info(cat1);
        Category cat2 = categoryMapper.selectById(spu.getCat2Id());
        oneSkuResult.setCat2Info(cat2);
        Category cat3 = categoryMapper.selectById(spu.getCat3Id());
        oneSkuResult.setCat3Info(cat3);

        // 5 图片
        // 5.1 第一张图片
        Map<String,String> logo = new HashMap<>();
        logo.put("smlogo", spu.getLogo());
        logo.put("biglogo", spu.getLogo());
        logo.put("xbiglogo", spu.getLogo());
        oneSkuResult.setLogo(logo);
        // 5.2 其他列表
        List<Map<String,String>> photos = new ArrayList<>();
        List<SkuPhoto> skuPhotoList = skuPhotoMapper.findSkuPhotoBySkuId(skuId);
        for (SkuPhoto skuPhoto : skuPhotoList) {
            Map<String,String> photo = new HashMap<>();
            photo.put("smimg", skuPhoto.getUrl());
            photo.put("bigimg", skuPhoto.getUrl());
            photo.put("xbigimg", skuPhoto.getUrl());
            photos.add(photo);
        }
        oneSkuResult.setPhotos(photos);


        // 6 规格
        // 6.1 查询指定catId的规格列表
        List<Spec> specList = specMapper.findAllByCatId(spu.getCat3Id());
        oneSkuResult.setSpecList(specList);

        // 6.2 当前sku中规格详情
        Map<String, String> specInfo = new HashMap<>();
        specInfo.put("id_list", sku.getSpecInfoIdList());
        specInfo.put("id_txt", sku.getSpecInfoIdTxt());
        oneSkuResult.setSpecInfo(specInfo);

        // 6.3 sku列表
        List<Sku> skuList = baseMapper.findSkuBySpuId(sku.getSpuId());
        List<Map<String,String>> skuMapList = new ArrayList<>();
        for (Sku tempSku : skuList) {
            Map<String, String> skuMap = new HashMap<>();
            skuMap.put("skuid", tempSku.getId() + "");
            skuMap.put("id_list", tempSku.getSpecInfoIdList());
            skuMapList.add(skuMap);
        }
        oneSkuResult.setSkuList(skuMapList);


        // 返回封装对象
        return oneSkuResult;
    }

    @Override
    public void updateSkuNum(Integer skuId, Integer count) {
        // 查询
        Sku sku = baseMapper.selectById(skuId);
        // 修改数据
        sku.setStock( sku.getStock() - count );
        System.out.println("skuId:"+skuId);
//        if(skuId==2600248){
//            throw new RuntimeException("库存不足");
//        }
        // 更新
        baseMapper.updateById(sku);
    }
}
