package com.rucn.transportation.controller;


import cn.hutool.core.util.StrUtil;
import com.rucn.transportation.common.RoleEnum;
import com.rucn.transportation.entity.Dict;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rucn.transportation.common.Constants;
import com.rucn.transportation.common.Result;
import com.rucn.transportation.entity.User;
import com.rucn.transportation.exception.ServiceException;
import com.rucn.transportation.mapper.DictMapper;
import com.rucn.transportation.mapper.RoleMapper;
import com.rucn.transportation.mapper.RoleMenuMapper;
import com.rucn.transportation.service.IUserService;
import com.rucn.transportation.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


import com.rucn.transportation.service.IMenuService;
import com.rucn.transportation.entity.Menu;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Wen
 * @since 2023-05-24
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
        @Resource
        private IUserService userService;
        @Resource
        private IMenuService menuService;

        @Resource
        private DictMapper dictMapper;


        // 新增或者更新
        @PostMapping
        public Result save(@RequestBody Menu menu) {
                User admin = TokenUtils.getCurrentUser();
                if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                menuService.saveOrUpdate(menu);
                return Result.success();
        }

        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
                User admin = TokenUtils.getCurrentUser();
                if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                menuService.removeById(id);
                return Result.success();
        }

        @PostMapping("/del/batch")
        public Result deleteBatch(@RequestBody List<Integer> ids) {
                User admin = TokenUtils.getCurrentUser();
                if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                menuService.removeByIds(ids);
                return Result.success();
        }

        @GetMapping("/ids")
        public Result findAllIds() {
                User admin = TokenUtils.getCurrentUser();
                if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                return Result.success(menuService.list().stream().map(Menu::getId));
        }

        @GetMapping
        public Result findAll(@RequestParam(defaultValue = "") String name) {
                User admin = TokenUtils.getCurrentUser();
                if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                return Result.success(menuService.findMenus(name));
        }

        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
                User admin = TokenUtils.getCurrentUser();
                if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                return Result.success(menuService.getById(id));
        }

        @GetMapping("/page")
        public Result findPage(@RequestParam String name,
                               @RequestParam Integer pageNum,
                               @RequestParam Integer pageSize) {
                User admin = TokenUtils.getCurrentUser();
                if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
                queryWrapper.like("name", name);
                queryWrapper.orderByDesc("id");
                return Result.success(menuService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }

        @GetMapping("/icons")
        public Result getIcons() {
                User admin = TokenUtils.getCurrentUser();
                if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("type", Constants.DICT_TYPE_ICON);
                return Result.success(dictMapper.selectList(queryWrapper));
        }

        @GetMapping("/username/{username}")
        public Result findRoleMenus(@PathVariable String username) {
                User admin = TokenUtils.getCurrentUser();
                if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                if (!StrUtil.isBlank(username)) {
                        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                        queryWrapper.eq("username", username);
                        User one;
                        try {
                                one = userService.getOne(queryWrapper);
                        } catch (Exception e) {
                                throw new ServiceException(Constants.CODE_502, "该账户有多个用户或者系统错误");
                        }
                        if (StrUtil.isBlank(username)) {
                                throw new ServiceException(Constants.CODE_501, "没有该用户");
                        } else {
                                String role = one.getRole();
                                List<Menu> roleMenus = userService.getRoleMenus(role);
                                return Result.success(roleMenus);
                        }
                }
                return  Result.error();
        }

}
