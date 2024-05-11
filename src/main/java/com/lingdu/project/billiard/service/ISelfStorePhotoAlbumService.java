package com.lingdu.project.billiard.service;


import com.lingdu.project.billiard.domain.SelfStorePhotoAlbum;

import java.util.List;

/**
 * 门店相册Service接口
 * 
 * @author 猛男波波
 * @date 2024-05-07
 */
public interface ISelfStorePhotoAlbumService 
{
    /**
     * 查询门店相册
     * 
     * @param albumId 门店相册主键
     * @return 门店相册
     */
    public SelfStorePhotoAlbum selectSelfStorePhotoAlbumByAlbumId(Long albumId);

    /**
     * 查询门店相册列表
     * 
     * @param selfStorePhotoAlbum 门店相册
     * @return 门店相册集合
     */
    public List<SelfStorePhotoAlbum> selectSelfStorePhotoAlbumList(SelfStorePhotoAlbum selfStorePhotoAlbum);

    /**
     * 新增门店相册
     * 
     * @param selfStorePhotoAlbum 门店相册
     * @return 结果
     */
    public int insertSelfStorePhotoAlbum(SelfStorePhotoAlbum selfStorePhotoAlbum);

    /**
     * 修改门店相册
     * 
     * @param selfStorePhotoAlbum 门店相册
     * @return 结果
     */
    public int updateSelfStorePhotoAlbum(SelfStorePhotoAlbum selfStorePhotoAlbum);

    /**
     * 批量删除门店相册
     * 
     * @param albumIds 需要删除的门店相册主键集合
     * @return 结果
     */
    public int deleteSelfStorePhotoAlbumByAlbumIds(Long[] albumIds);

    /**
     * 删除门店相册信息
     * 
     * @param albumId 门店相册主键
     * @return 结果
     */
    public int deleteSelfStorePhotoAlbumByAlbumId(Long albumId);
}