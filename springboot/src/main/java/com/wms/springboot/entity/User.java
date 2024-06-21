/*
 *功能：
 *日期：2024/6/10 18:23
 *作者：
 */
package com.wms.springboot.entity;

import lombok.*;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String avatar;

    private String token;
}
