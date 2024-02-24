package com.rucn.transportation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Wen
 * @since 2023-05-29
 */
@Getter
@Setter
  @TableName("sys_order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String orderName;

    private String sendRegion;

    private String receiveRegion;

    private String receiveAdress;

    private String dateOut;

    private Integer deliveryNum;

    private String others;

    private String name;

    private String phone;

    private String email;

    private String details;

    private String remark;

    private String username;

    private Integer isDelete;

    private Boolean isConfirmed;

    private LocalDateTime createTime;

    private Double weight;

    private  Integer orderStatus;

    private  Double usd;

    private  Double rubs;
}
