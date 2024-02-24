package com.rucn.transportation.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rucn.transportation.common.Constants;
import com.rucn.transportation.common.Result;
import com.rucn.transportation.common.RoleEnum;
import com.rucn.transportation.entity.Order;
import com.rucn.transportation.entity.User;
import com.rucn.transportation.exception.ServiceException;
import com.rucn.transportation.mapper.OrderDeliveryMapper;
import com.rucn.transportation.service.IOrderService;
import com.rucn.transportation.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

import com.rucn.transportation.service.IOrderDeliveryService;
import com.rucn.transportation.entity.OrderDelivery;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Wen
 * @since 2023-07-31
 */
@RestController
@RequestMapping("/order-delivery")
        public class OrderDeliveryController {
    
        @Resource
        private IOrderDeliveryService orderDeliveryService;
        @Resource
        private OrderDeliveryMapper  orderDeliveryMapper;

        @Resource
        private IOrderService orderService;
        // 新增一条记录
        @PostMapping("/add")
        public Result save(@RequestBody OrderDelivery orderDelivery) {
                orderDeliveryService.save(orderDelivery);
                return Result.success(true);
        }
        //查找
        @GetMapping("/findByorder/{orderId}")
        public Result findByorder(@PathVariable Integer orderId) {
                return Result.success(orderDeliveryMapper.findByorderId(orderId));
        }




        // 更改
        @PostMapping("/update")
        public Result Update(@RequestBody OrderDelivery orderDelivery) {
                User admin = TokenUtils.getCurrentUser();
                if (admin.getRole().equals(RoleEnum.ROLE_SU.toString())){
                        orderDeliveryService.saveOrUpdate(orderDelivery);
                        return Result.success(true);
                }
                if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        Integer id = orderDelivery.getOrderId();
                        Order order = orderService.getById(id);
                        String a = order.getIsConfirmed().toString();
                        if (order.getIsConfirmed().toString().equals("true"))
                        {
                                throw new ServiceException(Constants.CODE_501, "订单已确认");
                        }
                        orderDeliveryService.saveOrUpdate(orderDelivery);
                        return Result.success(true);
                }
                throw new ServiceException(Constants.CODE_500, "权限认证失败");
        }
        //删除
        @DeleteMapping("/delete/{orderId}")
        public Result delete(@PathVariable Integer orderId) {
                User admin = TokenUtils.getCurrentUser();
                if (admin.getRole().equals(RoleEnum.ROLE_SU.toString())){
                        return Result.success(orderDeliveryMapper.removeByOrderId(orderId));
                }
                if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        Order order = orderService.getById(orderId);
                        if (order.getIsConfirmed().toString().equals("true"))
                        {
                                throw new ServiceException(Constants.CODE_501, "订单已确认");
                        }
                        return Result.success(orderDeliveryMapper.removeByOrderId(orderId));
                }
                throw new ServiceException(Constants.CODE_500, "权限认证失败");
        }

}

