package com.rucn.transportation.service.impl;

import com.rucn.transportation.entity.Article;
import com.rucn.transportation.mapper.ArticleMapper;
import com.rucn.transportation.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wen
 * @since 2023-08-08
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
