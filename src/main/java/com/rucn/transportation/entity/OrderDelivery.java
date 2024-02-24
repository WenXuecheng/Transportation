package com.rucn.transportation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Wen
 * @since 2023-07-31
 */
@Getter
@Setter
  @TableName("sys_order_delivery")
public class OrderDelivery implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String trackingNumber;

    private String remark;

    private String type;

    private Integer orderId;

    private Boolean isUpload;
}
