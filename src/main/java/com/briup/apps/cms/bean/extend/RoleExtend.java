package com.briup.apps.cms.bean.extend;

import com.briup.apps.cms.bean.Role;

import java.util.List;

public class RoleExtend extends Role {

    private List<PrivilegeExtend> privilegeExtends;
    private List<UserExtend> userExtends;

    public List<PrivilegeExtend> getPrivilegeExtends() {
        return privilegeExtends;
    }

    public void setPrivilegeExtends(List<PrivilegeExtend> privilegeExtends) {
        this.privilegeExtends = privilegeExtends;
    }

    public List<UserExtend> getUserExtends() {
        return userExtends;
    }

    public void setUserExtends(List<UserExtend> userExtends) {
        this.userExtends = userExtends;
    }
}
