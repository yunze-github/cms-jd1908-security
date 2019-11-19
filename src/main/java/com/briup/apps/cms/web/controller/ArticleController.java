package com.briup.apps.cms.web.controller;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.service.IArticleService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

//@Api(value = "博客接口")
@Validated
@RestController
@RequestMapping("article")
public class ArticleController {


    @Autowired
    private IArticleService iArticleService;

//多表操作


    @ApiOperation(value = "所有博客")
    @GetMapping("/findAll")
    public Message findAll() {
        return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_QUERY_ALL,
                MessageUtil.STATUS_SUCCESS_QUERY_ALL, iArticleService.findAllArticle());
    }

    @ApiOperation(value = "栏目博客")
    @PostMapping("/findWithCategoryId")
    public Message findAllByCategoryId(
            @ApiParam(value = "查询栏目下面的博客", required = true)
            @NotNull @RequestParam(value = "categoryId") Long categoryId
    ) {
        return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_QUERY_ALL,
                MessageUtil.STATUS_SUCCESS_QUERY_ALL ,iArticleService.findAllArticleInCategoryByCategoryId(categoryId));
    }

    @ApiOperation(value = "详看博客")
    @PostMapping("/findOne")
    public Message findOneByID(
            @ApiParam(value = "根据博客ID详看博客", required = true)
            @NotNull @RequestParam(value = "articleId") Long id) {
        return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_QUERY_ONE,
                MessageUtil.STATUS_SUCCESS_QUERY_ONE, iArticleService.findArtiacleInCateGoryWithCommentByID(id));
    }


//单表操作


    @ApiOperation(value = "保存或者修改博客")
    @PostMapping("/saveOrUpdate")
    public Message saveOrUpdate(
            @ApiParam(value = "修改时需要添加博客ID") Long id,
            @ApiParam(value = "添加博客标题", required = true) String title,
            @ApiParam(value = "添加博客内容", required = true) String content,
            @ApiParam(value = "添加博客资源地址", required = true) String source,
            @ApiParam(value = "添加博客作者ID", required = true) Long authorId,
            @ApiParam(value = "博客属于哪个栏目", required = true) Long categoryId
    ) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setSource(source);
        article.setAuthorId(authorId);
        article.setCategoryId(categoryId);
        iArticleService.saveOrUpdate(article);
        if (id != null) {
            article.setId(id);
            return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_SAVE,MessageUtil.STATUS_SUCCESS_SAVE);
        }
        return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_UPDATE,MessageUtil.STATUS_SUCCESS_UPDATE);
    }

    @ApiOperation(value = "删除博客")
    @GetMapping("/dropOne")
    public Message dropOne(@ApiParam(name = "id", value = "根据博客ID删除博客", required = true) @RequestParam("id") Long id) {
        iArticleService.dropOne(id);
        return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_DELETE_ONE,MessageUtil.STATUS_SUCCESS_DELETE_ONE);
    }

}
