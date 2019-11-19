package com.briup.apps.cms.web.controller;

import com.briup.apps.cms.bean.Log;
import com.briup.apps.cms.service.ILogService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * <h3>cms_jd1908</h3>
 * <p>用户操作日志记录控制器</p>
 *
 * @author : yunze
 * @date : 2019-11-16 08:34
 **/
@Validated
@RestController
@RequestMapping("log")
public class LogController {

    @Autowired
    private ILogService logService;

    /*
    日志查询
     */
    @ApiOperation("用户操作日志记录查询")
    @GetMapping("/findOne")
    public Message findLogs(Long userId) {
        return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_QUERY_ALL,
                MessageUtil.STATUS_SUCCESS_QUERY_ALL,logService.findLogsByUserId(userId));
    }

    /*
    添加或者修改
     */

    @ApiOperation("用户日志记录添加或者修改")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",dataType = "Long",name = "id",value = "日志ID"),
            @ApiImplicitParam(paramType = "query",dataType = "String",required = true,name = "content",value = "日志内容"),
            @ApiImplicitParam(paramType = "query",dataType = "Long",required = true,name = "actionTime",value = "日志记录时间"),
            @ApiImplicitParam(paramType = "query",dataType = "Long",required = true,name = "userId",value = "日志所属用户ID")
    })

    @PostMapping("/saveOrUpadate")
    public Message saveOrUpdate(Long id,@NotNull String content,@NotNull Long actionTime,@NotNull Long userId){
        Log log = new Log();
        log.setContent(content);
        log.setActionTime(actionTime);
        log.setUserId(userId);
        if (id==null){
            logService.saveOrUpdate(log);
            return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_SAVE,MessageUtil.STATUS_SUCCESS_SAVE);
        }else {
            log.setId(id);
            logService.saveOrUpdate(log);
            return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_UPDATE,MessageUtil.STATUS_SUCCESS_UPDATE);
        }
    }

    /*
    删除一条或者多条日志记录
     */
    @ApiOperation("删除日志记录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body",dataType = "Array",required = true,name = "ids",value = "日志ID数组")
    })
    @PostMapping("/drop")
    public Message drop(@NotNull @RequestBody Long[] ids) {
        logService.dropLogs(ids);
        return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_DELETE_ALL,MessageUtil.STATUS_SUCCESS_DELETE_ALL);
    }
}
