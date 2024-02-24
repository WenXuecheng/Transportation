package com.rucn.transportation.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rucn.transportation.common.Constants;
import com.rucn.transportation.common.Result;
import com.rucn.transportation.common.RoleEnum;
import com.rucn.transportation.entity.dto.UserDTO;
import com.rucn.transportation.entity.dto.UserPasswordDTO;
import com.rucn.transportation.exception.ServiceException;
import com.rucn.transportation.mapper.UserMapper;
import com.rucn.transportation.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.rucn.transportation.service.IUserService;
import com.rucn.transportation.entity.User;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Wen
 * @since 2023-05-23
 */
@RestController
@RequestMapping("/user")
        public class UserController {
    
        @Resource
        private IUserService userService;

        @Resource
        private UserMapper userMapper;


//*********************************************公共权限
//注册时邮箱验证
        @GetMapping("/forgetpassword/{email}")
        public Result forgetPassword(@PathVariable String email) {
                return Result.success(userService.sendPassword(email));
        }

//*********************************************用户权限
        //注册时邮箱验证
        @GetMapping("/emailverification/{email}")
        public Result emailCode(@PathVariable String email) {
                return Result.success(userService.emialVerification(email));
        }

        // 用户更新用户更新用户操作
        @PostMapping("/saveByUser")
        public Result saveByUser(@RequestBody UserDTO userDTO) {
                User user = TokenUtils.getCurrentUser();
                if (!user.getId().equals(userDTO.getId())){
                        throw new ServiceException(Constants.CODE_500, "Id被篡改");
                }
                if (!user.getUsername().equals(userDTO.getUsername())){
                        throw new ServiceException(Constants.CODE_500, "用户名不一致");
                }
                if(user.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        return Result.success(userService.saveByUser(userDTO));
                }
                throw new ServiceException(Constants.CODE_500, "权限认证失败");
        }
        /**
         * 用户修改密码
         * @param userPasswordDTO
         * @return
         */
        @PostMapping("/password")
        public Result password(@RequestBody UserPasswordDTO userPasswordDTO) {
                User user = TokenUtils.getCurrentUser();
                if (!user.getId().equals(userPasswordDTO.getId())){
                        throw new ServiceException(Constants.CODE_500, "Id被篡改");
                }
                if (!user.getUsername().equals(userPasswordDTO.getUsername())){
                        throw new ServiceException(Constants.CODE_500, "用户名不一致");
                }
                userPasswordDTO.setOPassword(userPasswordDTO.getNewPassword());
                userPasswordDTO.setPassword(SecureUtil.md5(userPasswordDTO.getPassword()));
                userPasswordDTO.setNewPassword(SecureUtil.md5(userPasswordDTO.getNewPassword()));
                userService.updatePassword(userPasswordDTO);
                return Result.success();
        }


        @PostMapping("/login")
        public Result login(@RequestBody UserDTO userDTO) {
                String username = userDTO.getUsername();
                String password = userDTO.getPassword();
                if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
                        return Result.success(false);
                }
                return Result.success(userService.login(userDTO));
        }
        //用户查询自己的信息
        @GetMapping("/username/{username}")
        public Result findByUserName(@PathVariable String username) {
                User user = TokenUtils.getCurrentUser();
                if (!user.getUsername().equals(username)){
                        throw new ServiceException(Constants.CODE_500, "用户名不一致");
                }
                return Result.success(userService.findByUserName(username));
        }

        //新增
        @PostMapping("/register")
        public Result register(@RequestBody UserDTO userDTO) {
                userService.register(userDTO);
                return Result.success();
        }

//*********************************************管理员权限
        // 更新管理员操作
        @PostMapping
        public Result save(@RequestBody User user) {
                User admin = TokenUtils.getCurrentUser();
                if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                return Result.success(userService.editUse(user));
        }

        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
                User admin = TokenUtils.getCurrentUser();
                if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                return Result.success(userService.removeById(id));
                }

        @PostMapping("/del/batch")
        public Result deleteBatch(@RequestBody List<Integer> ids) {
                User admin = TokenUtils.getCurrentUser();
                if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                return Result.success(userService.removeByIds(ids));
                }
        //查找用户总数
        @GetMapping("/usernum")
        public Result usernum() {
                User user = TokenUtils.getCurrentUser();
                if (user.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                return Result.success(userMapper.countOrder());
        }

        @GetMapping
        public Result findAll() {
                User user = TokenUtils.getCurrentUser();
                if (user.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                return Result.success(userService.list());
                }

        @GetMapping("/role/{role}")
        public Result findUsersByRole(@PathVariable String role) {
                User user = TokenUtils.getCurrentUser();
                if (user.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("role", role);
                List<User> list = userService.list(queryWrapper);
                return Result.success(list);
        }

        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
                User user = TokenUtils.getCurrentUser();
                if (user.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                return Result.success(userService.getById(id));
                }

        @GetMapping("/page")
        public Result findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String username,
                               @RequestParam(defaultValue = "") String email,
                               @RequestParam(defaultValue = "") String address) {
                User user = TokenUtils.getCurrentUser();
                if (user.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                queryWrapper.orderByDesc("id");
                if (!"".equals(username)) {
                        queryWrapper.like("username", username);
                }
                if (!"".equals(email)) {
                        queryWrapper.like("email", email);
                }
                if (!"".equals(address)) {
                        queryWrapper.like("address", address);
                }
                return Result.success(userService.page(new Page<>(pageNum, pageSize),queryWrapper));
        }

        /**
         * 导出接口
         */
        @GetMapping("/export")
        public void export(HttpServletResponse response) throws Exception {
                // 从数据库查询出所有的数据
                List<User> list = userService.list();
                // 通过工具类创建writer 写出到磁盘路径
                // 在内存操作，写出到浏览器
                ExcelWriter writer = ExcelUtil.getWriter(true);
                //自定义标题别名
                writer.addHeaderAlias("id", "id");
                writer.addHeaderAlias("username", "用户名");
                writer.addHeaderAlias("password", "密码");
                writer.addHeaderAlias("nickname", "昵称");
                writer.addHeaderAlias("email", "邮箱");
                writer.addHeaderAlias("phone", "电话");
                writer.addHeaderAlias("address", "地址");
                writer.addHeaderAlias("createTime", "创建时间");
                writer.addHeaderAlias("role", "角色");
                writer.addHeaderAlias("avatarUrl", "头像");
                writer.addHeaderAlias("isEnable", "是否启用");

                // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
                writer.write(list, true);

                // 设置浏览器响应的格式
                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
                String fileName = URLEncoder.encode("用户信息", "UTF-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

                ServletOutputStream out = response.getOutputStream();
                writer.flush(out, true);
                out.close();
                writer.close();
        }
}

