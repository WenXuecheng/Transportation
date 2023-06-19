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

        // 新增或者更新
        @PostMapping
        public Result save(@RequestBody OrderFile orderFile) {
                User user = TokenUtils.getCurrentUser();
                if ( user.getRole().equals("ROLE_SU") || user.getRole().equals("ROLE_ADMIN")) {
                        orderFile.setAdminUp(Boolean.TRUE);
                }
                orderFile.setUserName(user.getUsername());
                return Result.success(orderFileService.saveOrUpdate(orderFile));
        }

        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
                return Result.success(orderFileService.removeById(id));
                }

        @PostMapping("/del/batch")
        public Result deleteBatch(@RequestBody List<Integer> ids) {
                return Result.success(orderFileService.removeByIds(ids));
                }

        @GetMapping
        public Result findAll() {
                return Result.success(orderFileService.list());
                }

        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
                return Result.success(orderFileService.getById(id));
                }

        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum,
        @RequestParam Integer pageSize) {
                QueryWrapper<OrderFile> queryWrapper = new QueryWrapper<>();
                queryWrapper.orderByDesc("id");
                return Result.success(orderFileService.page(new Page<>(pageNum, pageSize), queryWrapper));
        }
        @GetMapping("/ordername/{ordername}")
        public Result findByUsername(@PathVariable String ordername) {
                if (StrUtil.isBlank(ordername)) {
                        throw new ServiceException(Constants.CODE_501, "没有该订单号");
                }
                List<OrderFile> orderFiles;
                try {
                        orderFiles = orderFileMapper.getByOrdername(ordername);
                        return Result.success(orderFiles);
                } catch (Exception e) {
                        throw new ServiceException(Constants.CODE_500, "系统错误");
                }
        }

}

