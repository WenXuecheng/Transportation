package com.rucn.transportation.service.impl;

import com.rucn.transportation.common.Constants;
import com.rucn.transportation.entity.Order;
import com.rucn.transportation.entity.User;
import com.rucn.transportation.exception.ServiceException;
import com.rucn.transportation.mapper.OrderMapper;
import com.rucn.transportation.service.EmailService;
import com.rucn.transportation.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wen
 * @since 2023-05-29
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Resource
    EmailServiceImpl emailService;
    //生成订单后发送相关信息
    @Override
    public void sendMailOfOrderDetails(User user, Order order) {
        emailService.sendMailOrderDetails(user.getNickname(), order.getEmail(), order);
    }

    @Override
    public void sendOrderInfo(Order order) {
        emailService.sendMailOrderInfo(order);
    }

}
