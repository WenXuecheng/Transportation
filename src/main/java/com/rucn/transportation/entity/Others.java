package com.rucn.transportation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Wen
 * @since 2023-06-03
 */

@TableName("sys_others")
@Data
public class Others implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private Double exchangeRate;
    private Double usrmbRate;

    private String photo1;
    private String photo2;
    private String photo3;

    private String announcement1;

}
