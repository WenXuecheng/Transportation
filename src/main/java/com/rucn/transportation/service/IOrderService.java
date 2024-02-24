package com.rucn.transportation.service;

import com.rucn.transportation.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rucn.transportation.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wen
 * @since 2023-05-29
 */
public interface IOrderService extends IService<Order> {

    void sendMailOfOrderDetails(User user, Order order);

    void sendOrderInfo(Order order);

}
