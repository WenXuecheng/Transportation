package com.rucn.transportation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Wen
 * @since 2023-05-23
 */
@Getter
@Setter
@TableName("sys_user")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * id
     */
      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 用户名
     */
      private String username;

      /**
     * 密码
     */
      private String password;

      /**
     * 昵称
     */
      private String nickname;

      /**
     * 邮箱
     */
      private String email;

      /**
     * 电话
     */
      private String phone;

      /**
     * 地址
     */
      private String address;

      /**
     * 创建时间
     */
      private Date createTime;

      /**
     * 头像
     */
      private String avatarUrl;

      /**
     * 角色
     */
      private String role;
      /**
       * 邮箱确认
       */
      private Boolean isEnable;

      @JsonIgnore
      private String oPassword;


}
