package com.briup.apps.cms.web.controller;

import com.briup.apps.cms.service.IPrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h3>cms_jd1908</h3>
 * <p>用户权限管控制器</p>
 *
 * @author : yunze
 * @date : 2019-11-18 08:04
 **/
@Validated
@RestController
@RequestMapping("privilege")
public class PrivilegeController {

    @Autowired
    private IPrivilegeService privilegeService;

    /*
    权限添加
     */

    /*
    权限删除
     */


}
