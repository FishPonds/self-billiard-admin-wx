package com.lingdu.project.billiard.service;

import com.lingdu.project.billiard.domain.SelfTableTopAlbum;

import java.util.List;

/**
 * 桌台相册Service接口
 * 
 * @author 猛男波波
 * @date 2024-05-20
 */
public interface ISelfTableTopAlbumService 
{
    /**
     * 查询桌台相册
     * 
     * @param albumId 桌台相册主键
     * @return 桌台相册
     */
    public SelfTableTopAlbum selectSelfTableTopAlbumByAlbumId(Long albumId);

    /**
     * 查询桌台相册列表
     * 
     * @param selfTableTopAlbum 桌台相册
     * @return 桌台相册集合
     */
    public List<SelfTableTopAlbum> selectSelfTableTopAlbumList(SelfTableTopAlbum selfTableTopAlbum);

    /**
     * 新增桌台相册
     * 
     * @param selfTableTopAlbum 桌台相册
     * @return 结果
     */
    public int insertSelfTableTopAlbum(SelfTableTopAlbum selfTableTopAlbum);

    /**
     * 修改桌台相册
     * 
     * @param selfTableTopAlbum 桌台相册
     * @return 结果
     */
    public int updateSelfTableTopAlbum(SelfTableTopAlbum selfTableTopAlbum);

    /**
     * 批量删除桌台相册
     * 
     * @param albumIds 需要删除的桌台相册主键集合
     * @return 结果
     */
    public int deleteSelfTableTopAlbumByAlbumIds(String albumIds);

    /**
     * 删除桌台相册信息
     * 
     * @param albumId 桌台相册主键
     * @return 结果
     */
    public int deleteSelfTableTopAlbumByAlbumId(Long albumId);
}