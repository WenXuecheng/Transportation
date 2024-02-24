package com.rucn.transportation.mapper;

import com.rucn.transportation.entity.OrderDelivery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rucn.transportation.entity.OrderFile;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wen
 * @since 2023-07-31
 */
public interface OrderDeliveryMapper extends BaseMapper<OrderDelivery> {
    @Delete("delete from sys_order_delivery where order_id = #{orderId}")
    boolean removeByOrderId(Integer orderId);

    @Select("SELECT * FROM sys_order_delivery WHERE order_id = #{orderId}")
    List<OrderDelivery> findByorderId(Integer orderId);
}
