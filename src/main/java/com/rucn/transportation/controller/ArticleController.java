package com.rucn.transportation.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rucn.transportation.common.Constants;
import com.rucn.transportation.common.Result;
import com.rucn.transportation.common.RoleEnum;
import com.rucn.transportation.entity.User;
import com.rucn.transportation.exception.ServiceException;
import com.rucn.transportation.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.rucn.transportation.service.IArticleService;
import com.rucn.transportation.entity.Article;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Wen
 * @since 2023-08-08
 */
@RestController
@RequestMapping("/article")
        public class ArticleController {
    
        @Resource
        private IArticleService articleService;

        // 新增或者更新
        @PostMapping
        public Result save(@RequestBody Article article) {
                User admin = TokenUtils.getCurrentUser();
                if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                return Result.success(articleService.saveOrUpdate(article));
                }

        @DeleteMapping("/{id}")
        public Result delete(@PathVariable Integer id) {
                User admin = TokenUtils.getCurrentUser();
                if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                return Result.success(articleService.removeById(id));
                }

        @GetMapping
        public Result findAll() {
                User admin = TokenUtils.getCurrentUser();
                if (admin.getRole().equals(RoleEnum.ROLE_USER.toString())){
                        throw new ServiceException(Constants.CODE_500, "权限认证失败");
                }
                return Result.success(articleService.list());
                }
        @GetMapping("/{id}")
        public Result findOne(@PathVariable Integer id) {
                return Result.success(articleService.getById(id));
        }
}


