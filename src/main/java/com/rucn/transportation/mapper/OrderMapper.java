package com.rucn.transportation.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rucn.transportation.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wen
 * @since 2023-05-29
 */
public interface OrderMapper extends BaseMapper<Order> {
    @Select("select * from sys_order where username = #{username}")
    List<Order> selectByUsername(@Param("username")String username);

    @Select("select * from sys_order where id = #{orderId}")
    Order getByOrderId(Integer orderId);
    @Select("select count(id) from sys_order")
    Integer countOrder();
    @Select("select count(order_status) from sys_order where order_status=6")
    Integer countOrderTurnover();

    @Update("update sys_order set delivery_num=#{deliveryNum} where id = #{id}")
    boolean changeOrderNum(Integer deliveryNum,Integer id);
}
