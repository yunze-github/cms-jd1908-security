package com.briup.apps.cms.dao.extend;

import org.apache.ibatis.annotations.Param;

/**
 * <h3>cms_jd1908</h3>
 * <p></p>
 *
 * @author : yunze
 * @date : 2019-11-17 11:47
 **/

public interface RoleExtendMapper{
    void deleteRoleIds(@Param("roleIds") long[] roleIds);
}