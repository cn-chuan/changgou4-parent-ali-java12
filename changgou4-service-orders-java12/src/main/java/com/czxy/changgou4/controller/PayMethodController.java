package com.czxy.changgou4.controller;

import com.czxy.changgou4.pojo.Pay;
import com.czxy.changgou4.service.OrderService;
import com.czxy.changgou4.service.PayMethodService;
import com.czxy.changgou4.vo.BaseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/payMethod")
public class PayMethodController {
    @Resource
    private PayMethodService payMethodService;
    @Resource
    private OrderService orderService;
    @GetMapping("/{id}")
    public BaseResult<List<Pay>> getPayFid(@PathVariable("id")Integer id){
        List<Pay> list=payMethodService.getByFid(id);
        return BaseResult.ok("查询成功",list);
    }
    @GetMapping("/getById/{id}")
    public BaseResult<Pay> getPayid(@PathVariable("id")Integer id){
        Pay pay = payMethodService.getById(id);
        return BaseResult.ok("查询成功",pay);
    }
}
