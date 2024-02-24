package com.rucn.transportation.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rucn.transportation.common.Constants;
import com.rucn.transportation.common.Result;
import com.rucn.transportation.common.RoleEnum;
import com.rucn.transportation.entity.Order;
import com.rucn.transportation.entity.User;
import com.rucn.transportation.exception.ServiceException;
import com.rucn.transportation.mapper.OrderFileMapper;
import com.rucn.transportation.service.IUserService;
import com.rucn.transportation.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.rucn.transportation.service.IOrderFileService;
import com.rucn.transportation.entity.OrderFile;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Wen
 * @since 2023-05-29
 */
@RestController
@RequestMapping("/order-file")
        public class OrderFileController {

        @Resource
        private IOrderFileService orderFileService;
        @Resource
        private OrderFileMapper orderFileMapper;
        @Resource
        private IUserService userService;

//*******************************用户
        @GetMapping("/orderDId/{orderDId}")
        public Result findByUsername(@PathVariable Integer orderDId) {
                List<OrderFile> orderFiles;
                try {
                        orderFiles = orderFileMapper.getByOrderDId(orderDId);
                        return Result.success(orderFiles);
                } catch (Exception e) {
                        throw new ServiceException(Constants.CODE_500, "系统错误");
                }
        }
//*******************************管理员

        // 新增或者更新
        @PostMapping
        public Result save(@RequestBody OrderFile orderFile) {
                User admin = TokenUtils.getCurrentUser();
                if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                return Result.success(orderFileService.saveOrUpdate(orderFile));
        }
        //删除
        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
                User admin = TokenUtils.getCurrentUser();
                if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                return Result.success(orderFileService.removeById(id));
                }

        @PostMapping("/del/batch")
        public Result deleteBatch(@RequestBody List<Integer> ids) {
                User admin = TokenUtils.getCurrentUser();
                if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                return Result.success(orderFileService.removeByIds(ids));
                }

        @GetMapping
        public Result findAll() {
                User admin = TokenUtils.getCurrentUser();
                if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                return Result.success(orderFileService.list());
                }

        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
                User admin = TokenUtils.getCurrentUser();
                if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                return Result.success(orderFileService.getById(id));
                }

        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum,
        @RequestParam Integer pageSize) {
                User admin = TokenUtils.getCurrentUser();
                if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                QueryWrapper<OrderFile> queryWrapper = new QueryWrapper<>();
                queryWrapper.orderByDesc("id");
                return Result.success(orderFileService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }

}

