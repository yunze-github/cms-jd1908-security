package com.briup.apps.cms.service.impl;

import com.briup.apps.cms.bean.Role;
import com.briup.apps.cms.bean.RoleExample;
import com.briup.apps.cms.dao.RoleMapper;
import com.briup.apps.cms.dao.extend.RoleExtendMapper;
import com.briup.apps.cms.exception.CustomerException;
import com.briup.apps.cms.service.IRoleService;
import com.briup.apps.cms.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>cms_jd1908</h3>
 * <p>角色Service层接口实现类</p>
 *
 * @author : yunze
 * @date : 2019-11-17 11:42
 **/

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleExtendMapper roleExtendMapper;

    //查找所有角色
    @Override
    public List<Role> findAll() throws CustomerException {
        RoleExample roleExample = new RoleExample();
        List<Role> list = roleMapper.selectByExample(roleExample);
        if (list.size()==0){
            throw new CustomerException("角色信息不存在!");
        }
        return list;
    }

    //添加或者修改角色
    @Override
    public void saveOrUpdate(Role role) throws CustomerException {
        RoleExample roleExample = new RoleExample();
        if (role.getId()!=null){
            roleExample.createCriteria().andIdEqualTo(role.getId());
            List<Role> idList = roleMapper.selectByExample(roleExample);
            if (idList.size()==0) {
                throw new CustomerException("角色信息不存在!");
            }
            roleMapper.updateByExample(role,roleExample);
        }
        roleExample.createCriteria().andNameEqualTo(role.getName());
        List<Role> nameList = roleMapper.selectByExample(roleExample);
        if (nameList.size()!=0) {
            throw new CustomerException("角色名称重复!");
        }
        roleMapper.insert(role);
    }

    //删除角色
    @Override
    public void drop(long[] ids) throws CustomerException {
        if (ids==null||ids.length==0){
            throw new CustomerException("删除角色ID为空!");
        }
        roleExtendMapper.deleteRoleIds(ids);
    }
}
