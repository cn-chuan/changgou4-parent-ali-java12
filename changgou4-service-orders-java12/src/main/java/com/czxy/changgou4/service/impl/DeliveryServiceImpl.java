package com.czxy.changgou4.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czxy.changgou4.mapper.DeliveryMapper;
import com.czxy.changgou4.pojo.Delivery;
import com.czxy.changgou4.pojo.DeliveryTime;
import com.czxy.changgou4.pojo.DeliveryUser;
import com.czxy.changgou4.service.DeliveryService;
import com.czxy.changgou4.service.DeliveryUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class DeliveryServiceImpl extends ServiceImpl<DeliveryMapper, Delivery> implements DeliveryService {
    @Resource
    private DeliveryUserService deliveryUserService;
    @Override
    public ArrayList<Delivery> findAll(Long id) {
        ArrayList<Delivery> all = baseMapper.findAll();
        for (Delivery delivery : all) {
            ArrayList<DeliveryTime> list = delivery.getList();
            for (DeliveryTime deliveryTime : list) {
                if (deliveryTime.isIsdefault()){
                    DeliveryUser findDeliverUser = deliveryUserService.getById(id);
                    if (findDeliverUser!=null)deliveryUserService.removeById(id);
                    DeliveryUser deliveryUser = new DeliveryUser(id,delivery.getId(),deliveryTime.getId());
                    deliveryUserService.save(deliveryUser);
                }
            }
        }
        return all;
    }
}
