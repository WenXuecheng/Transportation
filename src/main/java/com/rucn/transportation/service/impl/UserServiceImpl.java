package com.rucn.transportation.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rucn.transportation.common.Result;
import com.rucn.transportation.common.RoleEnum;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rucn.transportation.common.Constants;
import com.rucn.transportation.entity.Menu;
import com.rucn.transportation.entity.User;
import com.rucn.transportation.entity.dto.UserDTO;
import com.rucn.transportation.entity.dto.UserPasswordDTO;
import com.rucn.transportation.exception.ServiceException;
import com.rucn.transportation.mapper.RoleMapper;
import com.rucn.transportation.mapper.RoleMenuMapper;
import com.rucn.transportation.mapper.UserMapper;
import com.rucn.transportation.service.EmailService;
import com.rucn.transportation.service.IMenuService;
import com.rucn.transportation.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rucn.transportation.utils.TokenUtils;
import com.rucn.transportation.utils.Tools;
import com.rucn.transportation.utils.VerificationCodeUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wen
 * @since 2023-05-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private static final Log LOG = Log.get();

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private IMenuService menuService;

    @Resource
    EmailService emailService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public UserDTO login(UserDTO userDTO) {
        // 用户密码 md5加密
        userDTO.setPassword(SecureUtil.md5(userDTO.getPassword()));
        User one = getUserInfo(userDTO);
        if (one == null) {
            throw new ServiceException(Constants.CODE_600, "Incorrect username or password");
        }
        if ( !one.getPassword().equals(userDTO.getPassword()))
        {
            throw new ServiceException(Constants.CODE_600, "Incorrect username or password");
        }
        BeanUtil.copyProperties(one, userDTO, true);
        // 设置token
        String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
        userDTO.setToken(token);

        String role = one.getRole(); // ROLE_ADMIN
        // 设置用户的菜单列表
        List<Menu> roleMenus = getRoleMenus(role);
        userDTO.setIsEnable(one.getIsEnable());
        userDTO.setMenus(roleMenus);
        userDTO.setPassword("");
        return userDTO;
    }

    @Override
    public User register(UserDTO userDTO) {
        userDTO.setRole(null);
        userDTO.setAvatarUrl(null);
        //检查数据的格式电话号码应为数字，昵称为中文、英文、俄文、数字，其他为数字、英文字母和@.*/-+
        if (!userDTO.getNickname().matches("[\\u4E00-\\u9FA5A-Za-z0-9Ѐ-ӿ]+") || !Tools.isValidString(userDTO.getNickname(), 1, 10))
        {
            throw new ServiceException(Constants.CODE_600, "Please enter the correct nickname");
        }
        if (!userDTO.getPhone().matches("[0-9]+") || !Tools.isValidString(userDTO.getPhone(), 5, 20))
        {
            throw new ServiceException(Constants.CODE_600, "Please enter the correct phone number");
        }
        if (!userDTO.getUsername().matches("[0-9A-Za-z@.*/\\-+]+") || !Tools.isValidString(userDTO.getUsername(), 5, 20))
        {
            throw new ServiceException(Constants.CODE_600, "Please enter as required");
        }
        if (!userDTO.getPassword().matches("[0-9A-Za-z@.*/\\-+]+") || !Tools.isValidString(userDTO.getPassword(), 5, 20))
        {
            throw new ServiceException(Constants.CODE_600, "Please enter as required");
        }
        if (!userDTO.getEmail().matches("[0-9A-Za-z@.*/\\-+]+") || !Tools.isValidString(userDTO.getEmail(), 5, 20))
        {
            throw new ServiceException(Constants.CODE_600, "Please enter as required");
        }

        // 用户密码 md5加密
        userDTO.setPassword(SecureUtil.md5(userDTO.getPassword()));
        User one = getUserInfo(userDTO);
        try {
            QueryWrapper<User> queryWrapper_email = new QueryWrapper<>();
            queryWrapper_email.eq("email", userDTO.getEmail());
            User oneEamil = getOne(queryWrapper_email);// 从数据库查询用户邮箱是否已经存在
            if (oneEamil == null){

            }else {
                throw new ServiceException(Constants.CODE_602, "");
            }
        } catch (Exception e){
            throw new ServiceException(Constants.CODE_602, "Mailboxes are already being used");
        }
        if (one == null) {
            one = new User();
            BeanUtil.copyProperties(userDTO, one, true);
            // 默认一个普通用户的角色
            one.setRole(RoleEnum.ROLE_USER.toString());
            one.setNickname(one.getUsername());
            one.setIsEnable(false);
            one.setId(null); // 把 copy完之后的用户对象存储到数据库
            // 检测验证码
            String code = stringRedisTemplate.opsForValue().get("email_code_"+userDTO.getEmail());
            if (StrUtil.isBlank(code)) {
                // 存缓存
                throw new ServiceException(Constants.CODE_600, "Email authentication failed");
            }
            if (code.equals(userDTO.getCode())) {
                one.setIsEnable(true);
                save(one);
                return one;
            }
            stringRedisTemplate.delete("email_code_"+userDTO.getEmail());
            throw new ServiceException(Constants.CODE_600, "Failure to verify email");
        } else {
            throw new ServiceException(Constants.CODE_601, "User already exists");
        }
    }
    //验证邮箱
    @Override
    public boolean emialVerification(String email) {
        if (!email.matches("[0-9A-Za-z@.*/\\-+]+") || !Tools.isValidString(email, 5, 20))
        {
            throw new ServiceException(Constants.CODE_600, "请按要求输入");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        User one;
        try {
            one = this.getOne(queryWrapper);
        }catch (Exception e) {
            throw new ServiceException(Constants.CODE_600, "The email has been used many times");
        }
        if (one == null) {
            stringRedisTemplate.delete("email_code_"+email);
            String code = stringRedisTemplate.opsForValue().get("email_code_"+email);
            if (StrUtil.isBlank(code)) {
                // 生成验证码
                VerificationCodeUtil verificationCodeUtil = new VerificationCodeUtil();
                code = String.valueOf(verificationCodeUtil.getVerificationCode(6));
                // 存缓存
                stringRedisTemplate.opsForValue().set("email_code_"+email, JSONUtil.toJsonStr(code),5, TimeUnit.MINUTES);
            } else {
                throw new ServiceException(Constants.CODE_600, "send email error");
            }
            try {
                emailService.sendMailCode(email, code);
            } catch (Exception e) {
                stringRedisTemplate.delete("email_code_"+email);
                throw new ServiceException(Constants.CODE_600, "send email error");
            }
            return true;
        }else {
            throw new ServiceException(Constants.CODE_602, "The email has been used");
        }
    }

    @Override
    public Boolean saveByUser(UserDTO userDTO) {
        //版判断输入的数据是否合格
        if (!userDTO.getEmail().matches("[0-9A-Za-z@.*/\\-+]+") || !Tools.isValidString(userDTO.getEmail(), 5, 20))
        {
            throw new ServiceException(Constants.CODE_500, "Please enter as required");
        }

        if (!userDTO.getNickname().matches("[\\u4E00-\\u9FA5A-Za-z0-9Ѐ-ӿ]+") || !Tools.isValidString(userDTO.getNickname(), 1, 20))
        {
            throw new ServiceException(Constants.CODE_500, "Please enter the correct nickname");
        }
        if (!userDTO.getPhone().matches("[0-9]+") || !Tools.isValidString(userDTO.getPhone(), 5, 20))
        {
            throw new ServiceException(Constants.CODE_500, "Please enter the correct phone number");
        }

        //角色必须是指定的
        if (!StrUtil.isBlank(userDTO.getRole())){
            if (userDTO.getRole().equals(RoleEnum.ROLE_USER.toString()) || userDTO.getRole().equals(RoleEnum.ROLE_SU.toString()) || userDTO.getRole().equals(RoleEnum.ROLE_ADMIN.toString())){

            }else
            {
                throw new ServiceException(Constants.CODE_500, "Unknown user role");
            }
        }else {
            throw new ServiceException(Constants.CODE_500, "Unknown user role");
        }


        //邮箱必须唯一
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", userDTO.getEmail());
        try {
            User one = this.getOne(queryWrapper);
            if (one == null) {
                boolean update = userMapper.updateNicknameEmailPhoneAdrress(userDTO.getId().toString(),userDTO.getUsername(),userDTO.getNickname(),userDTO.getEmail(),userDTO.getPhone(),userDTO.getAddress());
                return true;
            }else {
                if (one.getUsername().equals(userDTO.getUsername())){
                    boolean update = userMapper.updateNicknameEmailPhoneAdrress(userDTO.getId().toString(),userDTO.getUsername(),userDTO.getNickname(),userDTO.getEmail(),userDTO.getPhone(),userDTO.getAddress());
                    return true;
                }else {
                    throw new ServiceException(Constants.CODE_502, "保存失败");
                }
            }
        }catch (Exception e) {
            throw new ServiceException(Constants.CODE_502, "保存失败");
        }
    }

    @Override
    public void updatePassword(UserPasswordDTO userPasswordDTO) {
        int update = userMapper.updatePassword(userPasswordDTO);
        if (update < 1) {
            throw new ServiceException(Constants.CODE_600, "Password error");
        }
    }

    private User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        User one;
        try {
            one = getOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
            //LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "This username has been used multiple times or can not connect to databases");
        }
        return one;
    }



    /**
     * 获取当前角色的菜单列表
     * @param roleFlag
     * @return
     */
    public List<Menu> getRoleMenus(String roleFlag) {
        Integer roleId = roleMapper.selectByFlag(roleFlag);
        // 当前角色的所有菜单id集合
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);

        // 查出系统所有的菜单(树形)
        List<Menu> menus = menuService.findMenus("");
        // new一个最后筛选完成之后的list
        List<Menu> roleMenus = new ArrayList<>();
        // 筛选当前用户角色的菜单
        for (Menu menu : menus) {
            if (menuIds.contains(menu.getId())) {
                roleMenus.add(menu);
            }
            List<Menu> children = menu.getChildren();
            // removeIf()  移除 children 里面不在 menuIds集合中的 元素
            children.removeIf(child -> !menuIds.contains(child.getId()));
        }
        return roleMenus;
    }

    @Override
    public boolean editUse(User user) {

        //版判断输入的数据是否合格
        if (!user.getEmail().matches("[0-9A-Za-z@.*/\\-+]+") || !Tools.isValidString(user.getEmail(), 5, 20))
        {
            throw new ServiceException(Constants.CODE_500, "Please enter as required");
        }

        if (!user.getNickname().matches("[\\u4E00-\\u9FA5A-Za-z0-9Ѐ-ӿ]+") || !Tools.isValidString(user.getNickname(), 1, 10))
        {
            throw new ServiceException(Constants.CODE_500, "Please enter the correct nickname");
        }
        if (!user.getPhone().matches("[0-9]+") || !Tools.isValidString(user.getPhone(), 5, 20))
        {
            throw new ServiceException(Constants.CODE_500, "Please enter the correct phone number");
        }

        //角色必须是指定的
        if (!StrUtil.isBlank(user.getRole())){
            if (user.getRole().equals(RoleEnum.ROLE_USER.toString()) || user.getRole().equals(RoleEnum.ROLE_SU.toString()) || user.getRole().equals(RoleEnum.ROLE_ADMIN.toString())){

            }else
            {
                throw new ServiceException(Constants.CODE_500, "Unknown user role");
            }
        }else {
            throw new ServiceException(Constants.CODE_500, "Unknown user role");
        }


        //邮箱必须唯一
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", user.getEmail());
        try {
            User one = this.getOne(queryWrapper);
            if (one == null) {
                this.saveOrUpdate(user);
            }else {
                if (one.getUsername().equals(user.getUsername())){
                    this.saveOrUpdate(user);
                }else {
                    throw new ServiceException(Constants.CODE_502, "保存失败");
                }
            }
        }catch (Exception e) {
            throw new ServiceException(Constants.CODE_502, "保存失败");
        }
        return false;
    }

    @Override
    public User findByUserName(String username) {

        if (StrUtil.isBlank(username)) {
            throw new ServiceException(Constants.CODE_501, "No such user");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User one;
        try {
            one = this.getOne(queryWrapper);
        }catch (Exception e) {
            throw new ServiceException(Constants.CODE_502, "The account has multiple users or a system error");
        }
        if (one == null) {
            throw new ServiceException(Constants.CODE_501, "No such user");
        }else {
            return one;
        }
    }


}
