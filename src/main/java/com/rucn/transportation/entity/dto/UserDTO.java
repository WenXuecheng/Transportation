package com.rucn.transportation.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rucn.transportation.entity.Menu;
import lombok.Data;

import java.util.List;

/**
 * 接受前端登录请求的参数
 */
@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private String avatarUrl;
    private String token;
    private String role;
    private List<Menu> menus;
    private Boolean isEnable;
    private String address;
    private String code;
}
