package com.briup.apps.cms.bean.extend;

import com.briup.apps.cms.bean.Privilege;

import java.util.List;

public class PrivilegeExtend extends Privilege {

    private List<RoleExtend> roleExtends;
    private List<PrivilegeExtend> privilegeExtends;

    public List<RoleExtend> getRoleExtends() {
        return roleExtends;
    }

    public void setRoleExtends(List<RoleExtend> roleExtends) {
        this.roleExtends = roleExtends;
    }

    public List<PrivilegeExtend> getPrivilegeExtends() {
        return privilegeExtends;
    }

    public void setPrivilegeExtends(List<PrivilegeExtend> privilegeExtends) {
        this.privilegeExtends = privilegeExtends;
    }
}
