package com.rucn.transportation.mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rucn.transportation.entity.User;
import com.rucn.transportation.entity.dto.UserPasswordDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
public interface UserMapper extends BaseMapper<User> {

    @Update("update sys_user set password = #{newPassword}, o_password = #{oPassword} where username = #{username} and password = #{password} and id = #{Id}")
    int updatePassword(Integer Id, String username,String password, String newPassword,String oPassword);

    Page<User> findPage(Page<User> page, @Param("username") String username, @Param("email") String email, @Param("address") String address);
    @Select("select count(id) from sys_user")
    Integer countOrder();
    @Update("update sys_user set nickname = #{nickname}, email = #{email}, phone = #{phone}, address = #{address} where username = #{username} and id = #{id}")
    boolean updateNicknameEmailPhoneAdrress(String id, String username, String nickname, String email, String phone, String address);
}