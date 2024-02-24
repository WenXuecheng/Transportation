package com.rucn.transportation.mapper;

import com.rucn.transportation.entity.OrderFile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wen
 * @since 2023-05-29
 */
public interface OrderFileMapper extends BaseMapper<OrderFile> {
    @Select("select * from sys_order_file where orderD_id = #{orderDId}")
    List<OrderFile> getByOrderDId(Integer orderDId);

    @Delete("delete from sys_order_file where order_id = #{orderId}")
    boolean removeByOrderId(Integer orderId);

}
