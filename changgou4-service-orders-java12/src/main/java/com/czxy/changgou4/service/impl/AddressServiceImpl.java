package com.czxy.changgou4.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czxy.changgou4.mapper.AddressMapper;
import com.czxy.changgou4.pojo.Address;
import com.czxy.changgou4.service.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Service
@Transactional
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {
    @Override
    public List<Address> findAllByUserId(Long userId) {
        return baseMapper.findAllByUserId(userId);
    }

    @Override
    public void addAddress(Address address) {
        // 1 修改指定用户地址的默认值
        baseMapper.updateDefault(0 , address.getUserId());
        // 2 添加
        address.setIsdefault(1);
        baseMapper.insert(address);
    }
}
