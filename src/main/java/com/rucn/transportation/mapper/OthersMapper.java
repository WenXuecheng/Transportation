package com.rucn.transportation.mapper;

import com.rucn.transportation.entity.Others;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wen
 * @since 2023-06-03
 */
public interface OthersMapper extends BaseMapper<Others> {
    @Update("update sys_others set exchange_rate = #{value} where id = 1")
    void setExchangeRate(Double value);
    @Select("select photo1, photo2, photo3, announcement1 from sys_others where id = 1")
    Others getPhotoAnnouncement();
    @Update("update sys_others set usrmb_rate = #{value} where id = 1")
    void setUsRmbRate(Double value);
}
