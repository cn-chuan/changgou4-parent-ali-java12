package com.czxy.changgou4.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czxy.changgou4.pojo.Delivery;

import java.util.ArrayList;

public interface DeliveryService extends IService<Delivery> {
    ArrayList<Delivery> findAll(Long id);
}
