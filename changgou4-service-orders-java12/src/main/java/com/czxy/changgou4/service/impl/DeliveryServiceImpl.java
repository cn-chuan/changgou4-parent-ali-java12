package com.czxy.changgou4.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czxy.changgou4.mapper.DeliveryMapper;
import com.czxy.changgou4.pojo.Delivery;
import com.czxy.changgou4.pojo.DeliveryTime;
import com.czxy.changgou4.pojo.DeliveryUser;
import com.czxy.changgou4.service.DeliveryService;
import com.czxy.changgou4.service.DeliveryTimeService;
import com.czxy.changgou4.service.DeliveryUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryServiceImpl extends ServiceImpl<DeliveryMapper, Delivery> implements DeliveryService {
    @Resource
    private DeliveryUserService deliveryUserService;
    @Resource
    private DeliveryService deliveryService;
    @Resource
    private DeliveryTimeService deliveryTimeService;
    @Override
    public ArrayList<Delivery> findAll(Long id) {
        ArrayList<Delivery> all = baseMapper.findAll();
        saveDelivery(all,id);
        return all;
    }
    public void saveDelivery(ArrayList<Delivery> all,Long id){
        for (Delivery delivery : all) {
            if(delivery.isIsdefault()){
                DeliveryUser findDeliverUser = deliveryUserService.getById(id);
                if (findDeliverUser!=null)deliveryUserService.removeById(id);
                DeliveryUser deliveryUser = new DeliveryUser(id,delivery.getId(),1);
                deliveryUserService.save(deliveryUser);
            }
            if(delivery.getList().size()!=0){
                ArrayList<DeliveryTime> list = delivery.getList();
                for (DeliveryTime deliveryTime : list) {
                    if (deliveryTime.isIsdefault()){
                        DeliveryUser findDeliverUser = deliveryUserService.getById(id);
                        if (findDeliverUser!=null)deliveryUserService.removeById(id);
                        DeliveryUser deliveryUser = new DeliveryUser(id,delivery.getId(),deliveryTime.getId());
                        deliveryUserService.save(deliveryUser);
                        break;
                    }
                }
            }

        }
    }
    @Override
    public void updateDelivery(Long id, Integer did, Integer tid) {
        ArrayList<Delivery> all = deliveryService.findAll(id);
        for (Delivery delivery : all) {
            if(delivery.isIsdefault()){
                delivery.setIsdefault(false);
            }
            if(delivery.getId()==did){
                delivery.setIsdefault(true);
            }
            if(delivery.getList().size()!=0){
                for (DeliveryTime deliveryTime : delivery.getList()) {
                    if (deliveryTime.isIsdefault()&&deliveryTime.getId()!=tid){
                        deliveryTime.setIsdefault(false);
                    }
                    if (deliveryTime.getId()==tid){
                        deliveryTime.setIsdefault(true);
                    }
                    deliveryTimeService.updateById(deliveryTime);
                }
            }
            deliveryService.updateById(delivery);
        }
        saveDelivery(all,id);


    }
}
