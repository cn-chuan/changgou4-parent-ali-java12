package com.czxy.changgou4.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czxy.changgou4.pojo.Address;

import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
public interface AddressService extends IService<Address> {

    public List<Address> findAllByUserId(Long userId);

    /**
     * @param address 必须含有用户信息
     * @author 桐叔
     * @email liangtong@itcast.cn
     * @return
     */
    public void addAddress(Address address);
}
