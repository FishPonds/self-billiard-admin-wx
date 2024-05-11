package com.lingdu.project.billiard.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 文件创建人：波波
 * 日期：2024/4/29
 * 时间：14:38
 * 类名：SelfMerchantsRole 台球用户和角色关联表
 * @author 猛男波波
 */
public class SelfUserRole {

    /** 商户ID */
    private Long selfUserId;

    /** 角色ID */
    private Long selfRoleId;

    public Long getSelfUserId() {
        return selfUserId;
    }

    public void setSelfUserId(Long selfUserId) {
        this.selfUserId = selfUserId;
    }

    public Long getSelfRoleId() {
        return selfRoleId;
    }

    public void setSelfRoleId(Long selfRoleId) {
        this.selfRoleId = selfRoleId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("selfUserId", getSelfUserId())
                .append("selfRoleId", getSelfRoleId())
                .toString();
    }
}