package com.rucn.transportation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author WeDI
 * @since 2023-05-29
 */
@Getter
@Setter
@TableName("sys_order_file")
public class OrderFile implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;
    @TableField(value = "orderD_id")
    private Integer orderDId;
    private String url;
    private Integer orderId;

}
