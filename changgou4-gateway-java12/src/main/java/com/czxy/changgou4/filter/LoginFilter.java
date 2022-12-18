package com.czxy.changgou4.filter;

import com.czxy.changgou4.config.FilterProperties;
import com.czxy.changgou4.config.JwtProperties;
import com.czxy.changgou4.pojo.User;
import com.czxy.changgou4.utils.JwtUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 */
@Component
@EnableConfigurationProperties(FilterProperties.class)
public class LoginFilter implements GlobalFilter, Ordered {

    @Resource
    private FilterProperties filterProperties;
    @Resource
    private JwtProperties jwtProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
            //1 获得当前路径
            String path = exchange.getRequest().getURI().getPath();
            System.out.println(path);

            //2 处理白名单
            List<String> allowPaths = filterProperties.getAllowPaths();
            for (String allowPath : allowPaths) {
                // 当前路径只要包含允许的路径，则放行
                if(path.contains(allowPath)) {
                    return chain.filter(exchange);
                }
            }

            //3 获得token
            String token = exchange.getRequest().getHeaders().getFirst("Authorization");

            //4 解析token（校验token）
            User user = JwtUtils.getObjectFromToken(token, jwtProperties.getPublicKey() , User.class);

            // 5.1 成功放行
            return chain.filter(exchange);
        } catch (Exception e) {
            // 5.2 失败：直接返回，提供401没有权限，需要重新登录
            e.printStackTrace();
            ServerHttpResponse response = exchange.getResponse();
            // 响应状态 401 没有权限
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            // 响应数据的编码
            response.getHeaders().add("Content-Type","application/json;charset=UTF-8");
            // 响应“没有权限”提示
            DataBuffer wrap = response.bufferFactory().wrap("没有权限".getBytes(StandardCharsets.UTF_8));
            return exchange.getResponse().writeWith(Flux.just(wrap));
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
