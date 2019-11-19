package com.briup.apps.cms.web.controller;

import com.briup.apps.cms.bean.User;
import com.briup.apps.cms.service.IUserService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <h3>cms_jd1908</h3>
 * <p>用户控制器</p>
 *
 * @author : yunze
 * @date : 2019-11-14 22:18
 **/
@RestController
@Validated
@RequestMapping("user")
public class UserController {

    /*
    用户查询
     */
    @Autowired
    private IUserService userService;

    @ApiOperation("查询所有用户信息!")
    @GetMapping("/findAll")
    public Message findAll() {
        return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_QUERY_ALL,
                MessageUtil.STATUS_SUCCESS_QUERY_ALL, userService.findAll());
    }

    /*
    用户登录
     */
    @ApiOperation("检查用户姓名")
    @PostMapping("/checkName")
    public Message cheackName(String username) {
        userService.checkName(username);
        return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_CHECK_NAME,MessageUtil.STATUS_SUCCESS_CHECK_NAME);
    }

    @ApiOperation("检查用户登录信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "username", value = "用戶注册信息!"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "password", value = "用戶注册信息!")
    })
    @PostMapping("/login")
    public Message login(String username, String password) {
        userService.checkLogin(username, password);
        return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_CHECK_PASS,MessageUtil.STATUS_SUCCESS_CHECK_PASS);
    }

    /*
    用户信息注册/修改
     */

    @ApiOperation("用户注册!")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "username", value = "用戶名称"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "password", value = "用戶密码"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "telephone", value = "用戶电话号码"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "realname", value = "用戶真实姓名"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "gender", value = "用戶性别"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "birth", value = "用戶出生日期"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "userFace", value = "用戶头像资源"),
    })
    @PostMapping("/register")
    public Message save(@NotNull String username, @NotNull String password, @NotNull String telephone, @NotNull String realname, @NotNull String gender, @NotNull Long birth, @NotNull String userFace) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setTelephone(telephone);
        user.setRealname(realname);
        user.setGender(gender);
        user.setBirth(birth);
        user.setUserFace(userFace);
        userService.save(user);
        return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_USER_REGISTER,MessageUtil.STATUS_SUCCESS_USER_REGISTER);
    }

    @ApiOperation("用户信息修改!")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "id", value = "用戶ID"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "username", value = "用戶名称"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "password", value = "用戶密码"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "telephone", value = "用戶电话号码"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "realname", value = "用戶真实姓名"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "gender", value = "用戶性别"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "birth", value = "用戶出生日期"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "userFace", value = "用戶头像资源"),
    })
    @PostMapping("/alter")
    public Message update(@NotNull Long id,@NotNull String username, @NotNull String password, @NotNull String telephone, @NotNull String realname, @NotNull String gender, @NotNull Long birth, @NotNull String userFace) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setTelephone(telephone);
        user.setRealname(realname);
        user.setGender(gender);
        user.setBirth(birth);
        user.setUserFace(userFace);
        userService.update(user);
        return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_USER_ALTER,MessageUtil.STATUS_SUCCESS_USER_ALTER);
    }

    /*
    用户注销
     */
    @ApiOperation("用户注销")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",dataType = "Long",required = true,name = "id",value = "用户ID"),
    })
    @GetMapping("/drop")
    public Message drop(@NotNull Long id) {
        userService.drop(id);
        return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_DELETE_ALL,MessageUtil.STATUS_SUCCESS_DELETE_ALL);
    }

    /*
    用户角色以及用户权限
     */
    @ApiOperation("用户角色查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",dataType = "Long",required = true,name = "id",value = "用户ID")
    })
    @GetMapping("/userRoles")
    public Message findRoles(@NotNull Long id) {

        return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_QUERY_ONE,
                MessageUtil.STATUS_SUCCESS_QUERY_ONE,userService.findRoles(id));
    }

    /*
    用户绑定角色
     */
}
