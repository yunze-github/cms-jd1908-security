package com.briup.apps.cms.dao.extend;

import com.briup.apps.cms.bean.security.UserSimple;

/**
 * <h3>cms_jd1908</h3>
 * <p>安全验证简单UserSimple类映射接口</p>
 *
 * @author : yunze
 * @date : 2019-11-18 18:49
 **/
public interface UserSimpleMapper {
    UserSimple selectUserSimpleByName(String username);
}
