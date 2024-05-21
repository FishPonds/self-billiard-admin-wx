package com.lingdu.project.billiard.service.impl;

import java.util.List;

import com.lingdu.project.billiard.domain.SelfTableTopAlbum;
import com.lingdu.project.billiard.mapper.SelfTableTopAlbumMapper;
import com.lingdu.project.billiard.service.ISelfTableTopAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lingdu.common.utils.text.Convert;

/**
 * 桌台相册Service业务层处理
 * 
 * @author 猛男波波
 * @date 2024-05-20
 */
@Service
public class SelfTableTopAlbumServiceImpl implements ISelfTableTopAlbumService
{
    @Autowired
    private SelfTableTopAlbumMapper selfTableTopAlbumMapper;

    /**
     * 查询桌台相册
     * 
     * @param albumId 桌台相册主键
     * @return 桌台相册
     */
    @Override
    public SelfTableTopAlbum selectSelfTableTopAlbumByAlbumId(Long albumId)
    {
        return selfTableTopAlbumMapper.selectSelfTableTopAlbumByAlbumId(albumId);
    }

    /**
     * 查询桌台相册列表
     * 
     * @param selfTableTopAlbum 桌台相册
     * @return 桌台相册
     */
    @Override
    public List<SelfTableTopAlbum> selectSelfTableTopAlbumList(SelfTableTopAlbum selfTableTopAlbum)
    {
        return selfTableTopAlbumMapper.selectSelfTableTopAlbumList(selfTableTopAlbum);
    }

    /**
     * 新增桌台相册
     * 
     * @param selfTableTopAlbum 桌台相册
     * @return 结果
     */
    @Override
    public int insertSelfTableTopAlbum(SelfTableTopAlbum selfTableTopAlbum)
    {
        return selfTableTopAlbumMapper.insertSelfTableTopAlbum(selfTableTopAlbum);
    }

    /**
     * 修改桌台相册
     * 
     * @param selfTableTopAlbum 桌台相册
     * @return 结果
     */
    @Override
    public int updateSelfTableTopAlbum(SelfTableTopAlbum selfTableTopAlbum)
    {
        return selfTableTopAlbumMapper.updateSelfTableTopAlbum(selfTableTopAlbum);
    }

    /**
     * 批量删除桌台相册
     * 
     * @param albumIds 需要删除的桌台相册主键
     * @return 结果
     */
    @Override
    public int deleteSelfTableTopAlbumByAlbumIds(String albumIds)
    {
        return selfTableTopAlbumMapper.deleteSelfTableTopAlbumByAlbumIds(Convert.toStrArray(albumIds));
    }

    /**
     * 删除桌台相册信息
     * 
     * @param albumId 桌台相册主键
     * @return 结果
     */
    @Override
    public int deleteSelfTableTopAlbumByAlbumId(Long albumId)
    {
        return selfTableTopAlbumMapper.deleteSelfTableTopAlbumByAlbumId(albumId);
    }
}