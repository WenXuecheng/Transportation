package com.rucn.transportation.service;

import com.rucn.transportation.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wen
 * @since 2023-05-24
 */
public interface IMenuService extends IService<Menu> {
    List<Menu> findMenus(String name);
}
