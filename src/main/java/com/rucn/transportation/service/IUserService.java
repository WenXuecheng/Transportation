package com.rucn.transportation.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rucn.transportation.entity.Menu;
import com.rucn.transportation.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rucn.transportation.entity.dto.UserDTO;
import com.rucn.transportation.entity.dto.UserPasswordDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wen
 * @since 2023-05-23
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);
    User register(UserDTO userDTO);
    void updatePassword(UserPasswordDTO userPasswordDTO);

    List<Menu> getRoleMenus(String roleFlag);

    boolean editUse(User user);

    User findByUserName(String username);

    boolean emialVerification(String email);

    Boolean saveByUser(UserDTO userDTO);
}
