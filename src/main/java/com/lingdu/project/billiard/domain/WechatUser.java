package com.lingdu.project.billiard.domain;

import com.lingdu.framework.aspectj.lang.annotation.Excel;
import com.lingdu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 微信用户对象 wechat_user
 * 
 * @author 猛男波波
 * @date 2024-05-01
 */
public class WechatUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long userId;

    /** 微信OpenID */
    @Excel(name = "微信OpenID")
    private String openId;

    /** 微信session_key */
    @Excel(name = "微信session_key")
    public String sessionKey;

    /** 微信UnionID */
    @Excel(name = "微信UnionID")
    private String unionId;

    /** 微信昵称 */
    @Excel(name = "微信昵称")
    private String nickName;

    /** 微信头像 */
    @Excel(name = "微信头像")
    private String avatar;

    /** 性别，0表示男性，1表示女性 */
    @Excel(name = "性别",readConverterExp = "0=表示男性,1=表示女性")
    private Long genderMale;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phoneNumber;

    /** 最后登录ip */
    @Excel(name = "最后登录ip")
    private String loginIp;

    /** 最后登录时间 */
    @Excel(name = "最后登录时间")
    private String loginTime;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setOpenId(String openId) 
    {
        this.openId = openId;
    }

    public String getOpenId() 
    {
        return openId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public void setUnionId(String unionId)
    {
        this.unionId = unionId;
    }

    public String getUnionId() 
    {
        return unionId;
    }
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setGenderMale(Long genderMale) 
    {
        this.genderMale = genderMale;
    }

    public Long getGenderMale() 
    {
        return genderMale;
    }
    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("openId", getOpenId())
            .append("sessionKey", getSessionKey())
            .append("unionId", getUnionId())
            .append("nickName", getNickName())
            .append("avatar", getAvatar())
            .append("genderMale", getGenderMale())
            .append("phoneNumber", getPhoneNumber())
            .append("createTime", getCreateTime())
            .append("loginIp", getLoginIp())
            .append("loginTime", getLoginTime())
            .toString();
    }
}