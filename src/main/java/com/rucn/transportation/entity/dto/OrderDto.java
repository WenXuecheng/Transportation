package com.rucn.transportation.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class OrderDto implements Serializable {



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

    private String pay;

    private String username;

    private Integer isDelete;

    private Boolean isConfirmed;

    private LocalDateTime createTime;

    private Double weight;

    private  Integer orderStatus;

    private  Double rmb;

    private  Double rubs;
}
