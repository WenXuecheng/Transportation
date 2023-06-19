package com.rucn.transportation.entity;

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
 * @since 2023-05-24
 */
@Getter
@Setter
@TableName("sys_dict")
@Data

public class Dict {

    private static final long serialVersionUID = 1L;

      /**
     * 名称
     */
      private String name;

      /**
     * 内容
     */
      private String value;

      /**
     * 类型
     */
      private String type;


}
