package com.briup.apps.cms.bean.extend;

import com.briup.apps.cms.bean.Log;

/**
 * <h3>cms_jd1908</h3>
 * <p>日志关联对象</p>
 *
 * @author : yunze
 * @date : 2019-11-15 14:00
 **/
public class LogExtend extends Log {
    private UserExtend userExtend;

    public UserExtend getUserExtend() {
        return userExtend;
    }

    public void setUserExtend(UserExtend userExtend) {
        this.userExtend = userExtend;
    }
}
