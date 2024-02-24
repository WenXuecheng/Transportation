package com.rucn.transportation.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rucn.transportation.common.Constants;
import com.rucn.transportation.common.Result;
import com.rucn.transportation.common.RoleEnum;
import com.rucn.transportation.entity.Role;
import com.rucn.transportation.entity.User;
import com.rucn.transportation.exception.ServiceException;
import com.rucn.transportation.service.IRoleService;
import com.rucn.transportation.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 青哥哥
 * @since 2022-02-10
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private IRoleService roleService;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Role role) {
        User admin = TokenUtils.getCurrentUser();
        if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
            throw new ServiceException(Constants.CODE_500, "权限认证失败");
        }
        return Result.success(roleService.saveOrUpdate(role));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        User admin = TokenUtils.getCurrentUser();
        if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
            throw new ServiceException(Constants.CODE_500, "权限认证失败");
        }
        roleService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        User admin = TokenUtils.getCurrentUser();
        if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
            throw new ServiceException(Constants.CODE_500, "权限认证失败");
        }
        roleService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        User admin = TokenUtils.getCurrentUser();
        if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
            throw new ServiceException(Constants.CODE_500, "权限认证失败");
        }
        return Result.success(roleService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        User admin = TokenUtils.getCurrentUser();
        if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
            throw new ServiceException(Constants.CODE_500, "权限认证失败");
        }
        return Result.success(roleService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam String name,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize) {
        User admin = TokenUtils.getCurrentUser();
        if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
            throw new ServiceException(Constants.CODE_500, "权限认证失败");
        }
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        queryWrapper.orderByDesc("id");
        return Result.success(roleService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    /**
     * 绑定角色和菜单的关系
     * @param roleId 角色id
     * @param menuIds 菜单id数组
     * @return
     */
    @PostMapping("/roleMenu/{roleId}")
    public Result roleMenu(@PathVariable Integer roleId, @RequestBody List<Integer> menuIds) {
        User admin = TokenUtils.getCurrentUser();
        if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
            throw new ServiceException(Constants.CODE_500, "权限认证失败");
        }
        roleService.setRoleMenu(roleId, menuIds);
        return Result.success();
    }

    @GetMapping("/roleMenu/{roleId}")
    public Result getRoleMenu(@PathVariable Integer roleId) {
        User admin = TokenUtils.getCurrentUser();
        if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
            throw new ServiceException(Constants.CODE_500, "权限认证失败");
        }
        return Result.success( roleService.getRoleMenu(roleId));
    }

}