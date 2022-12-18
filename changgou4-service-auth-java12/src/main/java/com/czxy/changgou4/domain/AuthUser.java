package com.czxy.changgou4.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 桐叔
 * @email liangtong@itcast.cn
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser {

    private Long id;

    private String username;

    private String password;

    private String face;

    private Integer expriece;

    private String email;

    private String mobile;

    private Date createdAt;

    private Date updatedAt;

    private String code;

    private String password_confirm;

}
