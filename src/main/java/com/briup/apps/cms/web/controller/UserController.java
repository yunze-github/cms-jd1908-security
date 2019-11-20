package com.briup.apps.cms.web.controller;

import com.briup.apps.cms.bean.User;
import com.briup.apps.cms.bean.security.UserSimple;
import com.briup.apps.cms.service.IUserService;
import com.briup.apps.cms.utils.JwtTokenUtil;
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
import java.util.HashMap;
import java.util.Map;

/**
 * <h3>cms_jd1908</h3>
 * <p>用户控制器</p>
 *
 * @author : yunze
 * @date : 2019-11-14 22:18
 **/
@RestController
@Validated
@CrossOrigin
@RequestMapping("user")
public class UserController {

    /*
    用户查询
     */
    @Autowired
    private IUserService userService;

    @ApiOperation("查询用户信息!")
    @GetMapping("/info")
    public Message info(@NotNull @RequestParam String token) {
        //
        return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_QUERY_ONE,
                MessageUtil.STATUS_SUCCESS_QUERY_ONE, userService.findOne(1l));
    }

    /*
    用户登录
     */

    @ApiOperation("检查用户姓名")
    @GetMapping("/checkName")
    public Message cheackName(@NotNull @RequestParam String username) {
        userService.checkName(username);
        return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_CHECK_NAME,MessageUtil.STATUS_SUCCESS_CHECK_NAME);
    }

    @ApiOperation("检查用户登录信息")
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public Message login(@NotNull @RequestBody UserSimple userSimple) {
        System.out.println(userSimple.getUsername()+userSimple.getPassword());
        userService.login(userSimple);
        // 1. 认证用户的用户名和密码
        User user = userService.login(userSimple);
        // 2. 如果登录成功产生token,将token缓存起来，返回
        String token = JwtTokenUtil.createJWT(user.getId(), user.getUsername());
        // 3. 如果登录失败
        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_CHECK_LoginIn,MessageUtil.STATUS_SUCCESS_CHECK_LoginIn,map);
    }
    //用户退出

    @PostMapping("/logout")
    public Message logout(){
        // 1. 登录， token从缓存中移除掉
        return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_USER_Loginout,MessageUtil.STATUS_SUCCESS_CHECK_Loginout);
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
