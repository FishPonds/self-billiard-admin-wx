package com.lingdu.project.billiard.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lingdu.framework.aspectj.lang.annotation.Excel;
import com.lingdu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 桌台相册对象 self_table_top_album
 * 
 * @author 猛男波波
 * @date 2024-05-20
 */
public class SelfTableTopAlbum extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    public SelfTableTopAlbum() {
    }

    public SelfTableTopAlbum(Long tableId) {
        this.tableId = tableId;
    }

    /** 相册id */
    private Long albumId;

    /** 桌台id */
    @Excel(name = "桌台id")
    private Long tableId;

    /** 图片URL */
    @Excel(name = "图片URL")
    private String imageUrl;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    public void setAlbumId(Long albumId)
    {
        this.albumId = albumId;
    }

    public Long getAlbumId()
    {
        return albumId;
    }
    public void setTableId(Long tableId)
    {
        this.tableId = tableId;
    }

    public Long getTableId()
    {
        return tableId;
    }
    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }
    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("albumId", getAlbumId())
            .append("tableId", getTableId())
            .append("imageUrl", getImageUrl())
            .append("description", getDescription())
            .append("createdAt", getCreatedAt())
            .toString();
    }
}