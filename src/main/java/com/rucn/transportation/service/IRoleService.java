package com.rucn.transportation.service;

import com.rucn.transportation.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wen
 * @since 2023-05-24
 */
public interface IRoleService extends IService<Role> {
    void setRoleMenu(Integer roleId, List<Integer> menuIds);
    List<Integer> getRoleMenu(Integer roleId);
}
