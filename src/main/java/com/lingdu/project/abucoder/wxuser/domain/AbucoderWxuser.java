package com.lingdu.project.abucoder.wxuser.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.lingdu.framework.aspectj.lang.annotation.Excel;
import com.lingdu.framework.web.domain.BaseEntity;

/**
 * 微信用户对象 abucoder_wxuser
 * 
 * @author 猛男波波
 * @date 2022-05-27
 */
public class AbucoderWxuser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 微信名称 */
    @Excel(name = "微信名称")
    private String nickname;

    /** 头像 */
    @Excel(name = "头像")
    private String avatar;

    /** 微信唯一标识符 */
    @Excel(name = "微信唯一标识符")
    private String openid;

    /** 性别 */
    @Excel(name = "性别")
    private Integer gender;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public String getNickname()
    {
        return nickname;
    }
    public void setAvatar(String avatar)
    {
        this.avatar = avatar;
    }

    public String getAvatar()
    {
        return avatar;
    }
    public void setOpenid(String openid)
    {
        this.openid = openid;
    }

    public String getOpenid()
    {
        return openid;
    }
    public void setGender(Integer gender)
    {
        this.gender = gender;
    }

    public Integer getGender()
    {
        return gender;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("nickname", getNickname())
            .append("avatar", getAvatar())
            .append("openid", getOpenid())
            .append("gender", getGender())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}