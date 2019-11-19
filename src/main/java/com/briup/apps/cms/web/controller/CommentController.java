package com.briup.apps.cms.web.controller;

import com.briup.apps.cms.bean.Comment;
import com.briup.apps.cms.service.ICommentService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * <h3>cms_jd1908</h3>
 * <p>评论控制器</p>
 *
 * @author : yunze
 * @date : 2019-11-16 10:24
 **/

@Validated
@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;


    /*
    添加评论
     */
    @ApiOperation("添加评论")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",dataType = "String",required = true,name = "content"),
            @ApiImplicitParam(paramType = "query",dataType = "Long",required = true,name = "userId"),
            @ApiImplicitParam(paramType = "query",dataType = "Long",required = true,name = "articleId"),
            @ApiImplicitParam(paramType = "query",dataType = "Long",name = "parentId")
    })
    @PostMapping("/save")
    public Message save(@NotNull String content,@NotNull Long userId,@NotNull Long articleId,Long parentId) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCommentTime(System.currentTimeMillis());
        comment.setUserId(userId);
        comment.setArticleId(articleId);
        if (parentId!=null){
            comment.setParentId(parentId);
        }
        commentService.save(comment);
        return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_SAVE,MessageUtil.STATUS_SUCCESS_SAVE);
    }

    /*
    删除一条或者多条评论
     */
    @ApiOperation("删除评论")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body",required = true,name = "ids",value = "删除评论")
    })
    @GetMapping("/drop")
    public Message drop(@NotNull @RequestBody Long[] ids) {
        commentService.dropComments(ids);
        return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_DELETE_ALL,MessageUtil.STATUS_SUCCESS_DELETE_ALL);
    }
}
