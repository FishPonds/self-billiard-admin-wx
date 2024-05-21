package com.lingdu.project.billiard.domain;

import com.lingdu.framework.aspectj.lang.annotation.Excel;
import com.lingdu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 门店相册对象 self_store_photo_album
 * 
 * @author 猛男波波
 * @date 2024-05-07
 */
public class SelfStorePhotoAlbum extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 相册ID */
    private Long albumId;

    /** 相册名称 */
    @Excel(name = "相册名称")
    private String albumName;

    /** 门店ID */
    @Excel(name = "门店ID")
    private Long storeId;

    /** 门店Key */
    @Excel(name = "门店Key")
    private String storeKey;

    public SelfStorePhotoAlbum()
    {
    }

    public SelfStorePhotoAlbum(Long storeId)
    {
        this.storeId = storeId;
    }

    public void setAlbumId(Long albumId) 
    {
        this.albumId = albumId;
    }

    public Long getAlbumId() 
    {
        return albumId;
    }
    public void setAlbumName(String albumName) 
    {
        this.albumName = albumName;
    }

    public String getAlbumName() 
    {
        return albumName;
    }
    public void setStoreId(Long storeId) 
    {
        this.storeId = storeId;
    }

    public Long getStoreId() 
    {
        return storeId;
    }
    public void setStoreKey(String storeKey) 
    {
        this.storeKey = storeKey;
    }

    public String getStoreKey() 
    {
        return storeKey;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("albumId", getAlbumId())
            .append("albumName", getAlbumName())
            .append("storeId", getStoreId())
            .append("storeKey", getStoreKey())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}