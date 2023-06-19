package com.rucn.transportation.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;import lombok.Data;

/**
 * <p>
 * 角色菜单关系表
 * </p>
 *
 * @author Wen
 * @since 2023-05-24
 */
@Getter
@Setter
@TableName("sys_role_menu")
@Data
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 角色id
     */
        private Integer roleId;

      /**
     * 菜单id
     */
        private Integer menuId;


}
