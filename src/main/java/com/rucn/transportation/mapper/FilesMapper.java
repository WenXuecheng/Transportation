package com.rucn.transportation.mapper;

import com.rucn.transportation.entity.Files;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Wen
 * @since 2023-05-24
 */
public interface FilesMapper extends BaseMapper<Files> {
    @Delete("delete from sys_file where url = #{url}")
    void deleteByUrl(String url);
}
