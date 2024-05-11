package com.lingdu.project.abucoder.items.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.lingdu.framework.aspectj.lang.annotation.Excel;
import com.lingdu.framework.web.domain.BaseEntity;

/**
 * 推荐项目对象 abucoder_items
 * 
 * @author 阿卜 QQ932696181
 * @date 2022-05-27
 */
public class AbucoderItems extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String itemName;

    /** 项目介绍 */
    @Excel(name = "项目介绍")
    private String itemDesc;

    /** 项目内容 */
    private String itemContent;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public String getItemName()
    {
        return itemName;
    }
    public void setItemDesc(String itemDesc)
    {
        this.itemDesc = itemDesc;
    }

    public String getItemDesc()
    {
        return itemDesc;
    }
    public void setItemContent(String itemContent)
    {
        this.itemContent = itemContent;
    }

    public String getItemContent()
    {
        return itemContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("itemName", getItemName())
            .append("itemDesc", getItemDesc())
            .append("itemContent", getItemContent())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}