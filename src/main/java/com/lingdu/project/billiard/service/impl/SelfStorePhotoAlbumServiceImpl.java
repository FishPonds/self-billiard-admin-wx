package com.lingdu.project.billiard.service.impl;

import com.lingdu.common.utils.DateUtils;
import com.lingdu.project.billiard.domain.SelfStorePhotoAlbum;
import com.lingdu.project.billiard.mapper.SelfStorePhotoAlbumMapper;
import com.lingdu.project.billiard.service.ISelfStorePhotoAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 门店相册Service业务层处理
 * 
 * @author 猛男波波
 * @date 2024-05-07
 */
@Service
public class SelfStorePhotoAlbumServiceImpl implements ISelfStorePhotoAlbumService
{
    @Autowired
    private SelfStorePhotoAlbumMapper selfStorePhotoAlbumMapper;

    /**
     * 查询门店相册
     * 
     * @param albumId 门店相册主键
     * @return 门店相册
     */
    @Override
    public SelfStorePhotoAlbum selectSelfStorePhotoAlbumByAlbumId(Long albumId)
    {
        return selfStorePhotoAlbumMapper.selectSelfStorePhotoAlbumByAlbumId(albumId);
    }

    /**
     * 查询门店相册列表
     * 
     * @param selfStorePhotoAlbum 门店相册
     * @return 门店相册
     */
    @Override
    public List<SelfStorePhotoAlbum> selectSelfStorePhotoAlbumList(SelfStorePhotoAlbum selfStorePhotoAlbum)
    {
        return selfStorePhotoAlbumMapper.selectSelfStorePhotoAlbumList(selfStorePhotoAlbum);
    }

    /**
     * 新增门店相册
     * 
     * @param selfStorePhotoAlbum 门店相册
     * @return 结果
     */
    @Override
    public int insertSelfStorePhotoAlbum(SelfStorePhotoAlbum selfStorePhotoAlbum)
    {
        selfStorePhotoAlbum.setCreateTime(DateUtils.getNowDate());
        return selfStorePhotoAlbumMapper.insertSelfStorePhotoAlbum(selfStorePhotoAlbum);
    }

    /**
     * 修改门店相册
     * 
     * @param selfStorePhotoAlbum 门店相册
     * @return 结果
     */
    @Override
    public int updateSelfStorePhotoAlbum(SelfStorePhotoAlbum selfStorePhotoAlbum)
    {
        selfStorePhotoAlbum.setUpdateTime(DateUtils.getNowDate());
        return selfStorePhotoAlbumMapper.updateSelfStorePhotoAlbum(selfStorePhotoAlbum);
    }

    /**
     * 批量删除门店相册
     * 
     * @param albumIds 需要删除的门店相册主键
     * @return 结果
     */
    @Override
    public int deleteSelfStorePhotoAlbumByAlbumIds(Long[] albumIds)
    {
        return selfStorePhotoAlbumMapper.deleteSelfStorePhotoAlbumByAlbumIds(albumIds);
    }

    /**
     * 删除门店相册信息
     * 
     * @param albumId 门店相册主键
     * @return 结果
     */
    @Override
    public int deleteSelfStorePhotoAlbumByAlbumId(Long albumId)
    {
        return selfStorePhotoAlbumMapper.deleteSelfStorePhotoAlbumByAlbumId(albumId);
    }
}