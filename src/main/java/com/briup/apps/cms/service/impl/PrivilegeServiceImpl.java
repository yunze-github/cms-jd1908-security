package com.briup.apps.cms.service.impl;

import com.briup.apps.cms.bean.Privilege;
import com.briup.apps.cms.dao.extend.PrivilegeExtendMapper;
import com.briup.apps.cms.exception.CustomerException;
import com.briup.apps.cms.service.IPrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>cms_jd1908</h3>
 * <p>权限Service层接口实现类</p>
 *
 * @author : yunze
 * @date : 2019-11-18 09:50
 **/
@Service
public class PrivilegeServiceImpl implements IPrivilegeService {

    @Autowired
    private PrivilegeExtendMapper privilegeExtendMapper;

    @Override
    public List<Privilege> findByParentId(long userId) throws CustomerException {
        List<Privilege> list = privilegeExtendMapper.selectByUserId(userId);
        return list;
    }
}
