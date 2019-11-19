package com.briup.apps.cms.dao.extend;

import com.briup.apps.cms.bean.User;
import com.briup.apps.cms.bean.extend.RoleExtend;
import com.briup.apps.cms.bean.extend.UserExtend;

import java.util.List;

/**
 * <h3>cms_jd1908</h3>
 * <p>用户数据操作映射接口</p>
 *
 * @author : yunze
 * @date : 2019-11-14 22:26
 **/
public interface UserExtendMapper {
    List<UserExtend> selectUserWithRoles(Long id);

    UserExtend selectUserByusername(String username);
}
