package com.czxy.changgou4.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@TableName("tb_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /*
        CREATE TABLE `tb_user` (
           `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
           `created_at` timestamp NULL DEFAULT NULL,
           `updated_at` timestamp NULL DEFAULT NULL,
           `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Email',
           `mobile` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '手机号码',
           `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '昵称',
           `password` char(60) COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
           `face` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '头像',
           `expriece` int(10) unsigned DEFAULT '0' COMMENT '经验值',
           PRIMARY KEY (`id`),
           UNIQUE KEY `users_mobile_unique` (`mobile`),
           UNIQUE KEY `users_name_unique` (`name`),
           UNIQUE KEY `users_email_unique` (`email`)
         ) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci
     */
    @TableId(value="id",type = IdType.AUTO)
    private Long id;

    @TableField(value="username")
    private String username;

    @TableField(value="password")
    private String password;

    @TableField(value="face")
    private String face;

    @TableField(value="expriece")
    private Integer expriece;

    @TableField(value="email")
    private String email;

    @TableField(value="mobile")
    private String mobile;

    @TableField(value="created_at", fill = FieldFill.INSERT)
    private Date createdAt;

    @TableField(value="updated_at", fill = FieldFill.INSERT_UPDATE)
    private Date updatedAt;

    @TableField(exist = false)              //验证码
    private String code;
    @TableField(exist = false)              //确认密码
    private String password_confirm;

}
