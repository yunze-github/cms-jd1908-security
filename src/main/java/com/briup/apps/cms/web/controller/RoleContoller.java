package com.briup.apps.cms.web.controller;

import com.briup.apps.cms.bean.Role;
import com.briup.apps.cms.dao.RoleMapper;
import com.briup.apps.cms.service.IRoleService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * <h3>cms_jd1908</h3>
 * <p>角色控制器</p>
 *
 * @author : yunze
 * @date : 2019-11-17 11:27
 **/

@Validated
@RestController
@RequestMapping("role")
public class RoleContoller {

    @Autowired
    private IRoleService roleService;

    /*
    查找所有角色
     */
    @ApiOperation("查找所有角色信息")
    @GetMapping("/findAll")
    public Message finaAll() {
        List<Role> list = roleService.findAll();

        return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_QUERY_ALL,
                MessageUtil.STATUS_SUCCESS_QUERY_ALL,list);
    }

    /*
    角色添加
     */
    @ApiOperation("角色添加或者修改")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",dataType = "Long",required = false,name = "id",value = "角色ID"),
            @ApiImplicitParam(paramType = "query",dataType = "String",required = true,name = "name",value = "角色ID")
    })
    @PostMapping("/saveOrUpdate")
    public Message saveOrUpdate(Long id,@NotNull String name){
        Role role = new Role();
        role.setName(name);
        if (id!=null){
            role.setId(id);
        }
        roleService.saveOrUpdate(role);
        if (id==null){
            return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_SAVE,MessageUtil.STATUS_SUCCESS_SAVE);
        }
        return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_UPDATE,MessageUtil.STATUS_SUCCESS_UPDATE);
    }

    /*
    删除角色
     */
    @ApiOperation("角色删除")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body",required = true,name = "ids",value = "角色ID"),
    })
    @PostMapping("/drop")
    public Message drop(long[] ids) {
        System.out.println(Arrays.toString(ids));
        roleService.drop(ids);
        return MessageUtil.success(MessageUtil.MESSAGE_SUCCESS_DELETE_ALL,MessageUtil.STATUS_SUCCESS_DELETE_ALL);
    }

    /*
    权限绑定角色
     */

}
