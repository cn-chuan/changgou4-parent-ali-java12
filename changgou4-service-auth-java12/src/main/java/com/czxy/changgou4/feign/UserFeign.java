package com.czxy.changgou4.feign;

import com.czxy.changgou4.domain.AuthUser;
import com.czxy.changgou4.vo.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@FeignClient(value = "web-service", path = "/user")
public interface UserFeign {

    @PostMapping("/findByUsername")
    public BaseResult<AuthUser> findByUsername(@RequestBody AuthUser user);
}
