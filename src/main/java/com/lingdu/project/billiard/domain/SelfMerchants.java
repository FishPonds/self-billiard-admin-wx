package com.lingdu.project.billiard.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lingdu.framework.aspectj.lang.annotation.Excel;
import com.lingdu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 商户账号信息对象 self_merchants
 *
 * @author 猛男波波
 * @date 2024-04-29
 */
public class SelfMerchants extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商户ID（自增ID） */
    private Long customMerchantId;

    /** 商户Key（由代码随机生成的七位数） */
    @Excel(name = "商户Key")
    private String merchantKey;

    /** 台球用户ID */
    @Excel(name = "台球用户ID")
    private Long selfUserId;

    /** 商户名称 */
    @Excel(name = "商户名称")
    private String merchantName;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 帐号状态（0正常 1停用） */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 最后登录IP */
    @Excel(name = "最后登录IP")
    private String loginIp;

    /** 最后登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date loginDate;

    /** 联系人姓名 */
    @Excel(name = "联系人姓名")
    private String contactName;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String contactPhone;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;

    /** 角色对象 */
    private List<SelfRole> selfRoles;

    /** 角色组 */
    private Long[] selfRoleIds;

    /** 角色ID */
    private Long selfRoleId;

    public SelfMerchants() {
    }

    public void setCustomMerchantId(Long customMerchantId)
    {
        this.customMerchantId = customMerchantId;
    }

    public Long getCustomMerchantId()
    {
        return customMerchantId;
    }
    public void setMerchantKey(String merchantKey)
    {
        this.merchantKey = merchantKey;
    }

    public String getMerchantKey()
    {
        return merchantKey;
    }

    public Long getSelfUserId() {
        return selfUserId;
    }

    public void setSelfUserId(Long selfUserId) {
        this.selfUserId = selfUserId;
    }

    public void setMerchantName(String merchantName)
    {
        this.merchantName = merchantName;
    }

    public String getMerchantName()
    {
        return merchantName;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setLoginIp(String loginIp)
    {
        this.loginIp = loginIp;
    }

    public String getLoginIp()
    {
        return loginIp;
    }
    public void setLoginDate(Date loginDate)
    {
        this.loginDate = loginDate;
    }

    public Date getLoginDate()
    {
        return loginDate;
    }
    public void setContactName(String contactName)
    {
        this.contactName = contactName;
    }

    public String getContactName()
    {
        return contactName;
    }
    public void setContactPhone(String contactPhone)
    {
        this.contactPhone = contactPhone;
    }

    public String getContactPhone()
    {
        return contactPhone;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }
    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }
    public void setUpdatedAt(Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt()
    {
        return updatedAt;
    }

    public List<SelfRole> getSelfRoles() {
        return selfRoles;
    }

    public void setSelfRoles(List<SelfRole> selfRoles) {
        this.selfRoles = selfRoles;
    }

    public Long[] getSelfRoleIds() {
        return selfRoleIds;
    }

    public void setSelfRoleIds(Long[] selfRoleIds) {
        this.selfRoleIds = selfRoleIds;
    }

    public Long getSelfRoleId() {
        return selfRoleId;
    }

    public void setSelfRoleId(Long selfRoleId) {
        this.selfRoleId = selfRoleId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("customMerchantId", getCustomMerchantId())
                .append("merchantKey", getMerchantKey())
                .append("selfUserId", getSelfUserId())
                .append("merchantName", getMerchantName())
                .append("email", getEmail())
                .append("password", getPassword())
                .append("delFlag", getDelFlag())
                .append("status", getStatus())
                .append("loginIp", getLoginIp())
                .append("loginDate", getLoginDate())
                .append("contactName", getContactName())
                .append("contactPhone", getContactPhone())
                .append("address", getAddress())
                .append("createBy", getCreateBy())
                .append("createdAt", getCreatedAt())
                .append("updateBy", getUpdateBy())
                .append("updatedAt", getUpdatedAt())
                .append("remark", getRemark())
                .toString();
    }
}