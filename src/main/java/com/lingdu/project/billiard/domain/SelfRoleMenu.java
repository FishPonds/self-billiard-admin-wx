package com.lingdu.project.billiard.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 角色和菜单关联 self_role_menu
 * 
 * @author 猛男波波
 */
public class SelfRoleMenu
{
    /** 角色ID */
    private Long selfRoleId;
    
    /** 菜单ID */
    private Long selfMenuId;

    public Long getSelfRoleId() {
        return selfRoleId;
    }

    public void setSelfRoleId(Long selfRoleId) {
        this.selfRoleId = selfRoleId;
    }

    public Long getSelfMenuId() {
        return selfMenuId;
    }

    public void setSelfMenuId(Long selfMenuId) {
        this.selfMenuId = selfMenuId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("selfRoleId", getSelfRoleId())
            .append("selfMenuId", getSelfMenuId())
            .toString();
    }
}