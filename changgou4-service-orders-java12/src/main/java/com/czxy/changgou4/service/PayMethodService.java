package com.czxy.changgou4.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czxy.changgou4.pojo.Pay;

import java.util.ArrayList;
import java.util.List;

public interface PayMethodService extends IService<Pay> {
    List<Pay> getByFid(Integer id);
}
