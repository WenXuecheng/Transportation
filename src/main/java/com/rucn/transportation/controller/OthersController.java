package com.rucn.transportation.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rucn.transportation.common.Constants;
import com.rucn.transportation.common.Result;
import com.rucn.transportation.common.RoleEnum;
import com.rucn.transportation.entity.User;
import com.rucn.transportation.exception.ServiceException;
import com.rucn.transportation.mapper.OthersMapper;
import com.rucn.transportation.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.rucn.transportation.service.IOthersService;
import com.rucn.transportation.entity.Others;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Wen
 * @since 2023-06-03
 */
@RestController
@RequestMapping("/others")
public class OthersController {

        @Resource
        private IOthersService othersService;

        @Resource
        private OthersMapper othersMapper;

        // 新增或者更新
        @PostMapping
        public boolean save(@RequestBody Others others) {
                User user = TokenUtils.getCurrentUser();
                if (user.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                return othersService.saveOrUpdate(others);
        }

        @DeleteMapping("/{id}")
        public Boolean delete(@PathVariable Integer id) {
                User user = TokenUtils.getCurrentUser();
                if (user.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                return othersService.removeById(id);
        }

        @PostMapping("/del/batch")
        public boolean deleteBatch(@RequestBody List<Integer> ids) {
                User user = TokenUtils.getCurrentUser();
                if (user.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                return othersService.removeByIds(ids);
        }

        @GetMapping
        public List<Others> findAll() {
                return othersService.list();
        }

        //取汇率的值
        @GetMapping("/{id}")
        public Result find(@PathVariable Integer id) {
                Others others = othersService.getById(id);
                return Result.success(others.getExchangeRate());
        }
        //改汇率的值
        @GetMapping("/1/{value}")
        public Result change(@PathVariable Double value) {
                User user = TokenUtils.getCurrentUser();
                if (user.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                othersMapper.setExchangeRate(value);
                return Result.success();
        }
        //获取首页展示图片和公告
        @GetMapping("/getphotoandannouncement")
        public Result getphotoandannouncement() {
                Others o = othersMapper.getPhotoAnnouncement();
                return Result.success(othersMapper.getPhotoAnnouncement());
        }
}

