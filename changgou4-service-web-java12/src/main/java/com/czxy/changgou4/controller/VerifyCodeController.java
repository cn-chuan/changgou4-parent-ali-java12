package com.czxy.changgou4.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Controller
@RequestMapping("/verifycode")
public class VerifyCodeController {
//    @Resource
//    private HttpServletResponse response;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping
    public void verifycode(String username, HttpServletResponse response) throws IOException {
        //1 准备数据
        // 1.1 字体只显示大写，去掉了1,0,i,o几个容易混淆的字符
        String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";

        // 1.2 高宽
        int IMG_WIDTH = 72;
        int IMG_HEIGTH = 27;


        Random random = new Random();
        // 1.3 创建图片
        BufferedImage image = new BufferedImage(IMG_WIDTH, IMG_HEIGTH, BufferedImage.TYPE_INT_RGB);
        // 1.4 画板
        Graphics g = image.getGraphics();
        // 1.5 填充背景 （白色背景，黑色的1像素边框）
        g.setColor(Color.WHITE);
        g.fillRect(1,1,IMG_WIDTH-2,IMG_HEIGTH-2);

        /** 5.1 提供缓存对象*/
        StringBuilder sb = new StringBuilder();

        //2 绘制随机字符串
        // 2.1 设置字体
        g.setFont(new Font("楷体", Font.BOLD,25));
        // 2.2 写字
        for(int i = 1 ; i <= 4 ; i ++){
            // 2.3 随机颜色
            g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
            // 2.4 写入一个字符
            int len = random.nextInt(VERIFY_CODES.length());
            String str = VERIFY_CODES.substring(len,len+1);
            g.drawString(str, IMG_WIDTH / 6 * i , 22 );

            /** 5.2 保存每一个字符*/
            sb.append(str);
        }
        /** 5.3 将获得4长度的随机字符串，保存redis中 */
        String key = "login" + username;
        stringRedisTemplate.opsForValue().set(key, sb.toString(), 5, TimeUnit.MINUTES);

        //3 干扰元素
        for (int i = 0; i < 30; i++) {
            // 3.1 随机颜色
            g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
            // 3.2 随机线段
            int x = random.nextInt(IMG_WIDTH - 1);
            int y = random.nextInt(IMG_HEIGTH - 1);
            int x1 = random.nextInt(12) + 1;
            int y1 = random.nextInt(6) + 1;
            g.drawLine(x, y, x - x1, y - y1);
        }

        //4 以图片的方式响应到浏览器
        ImageIO.write(image,"jpeg", response.getOutputStream());
    }
}
