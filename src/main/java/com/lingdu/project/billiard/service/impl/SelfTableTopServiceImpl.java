package com.lingdu.project.billiard.service.impl;

import java.util.List;

import com.lingdu.common.utils.text.Convert;
import com.lingdu.project.billiard.domain.SelfTableTopAlbum;
import com.lingdu.project.billiard.mapper.SelfTableTopAlbumMapper;
import com.lingdu.project.billiard.service.ISelfTableTopAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lingdu.project.billiard.mapper.SelfTableTopMapper;
import com.lingdu.project.billiard.domain.SelfTableTop;
import com.lingdu.project.billiard.service.ISelfTableTopService;

/**
 * 桌台信息Service业务层处理
 * 
 * @author 猛男波波
 * @date 2024-05-18
 */
@Service
public class SelfTableTopServiceImpl implements ISelfTableTopService 
{
    @Autowired
    private SelfTableTopMapper selfTableTopMapper;

    @Autowired
    private SelfTableTopAlbumMapper selfTableTopAlbumMapper;

    /**
     * 查询桌台信息
     * 
     * @param tableId 桌台信息主键
     * @return 桌台信息
     */
    @Override
    public SelfTableTop selectSelfTableTopByTableId(Long tableId)
    {
        return selfTableTopMapper.selectSelfTableTopByTableId(tableId);
    }

    /**
     * 查询桌台信息列表
     * 
     * @param selfTableTop 桌台信息
     * @return 桌台信息
     */
    @Override
    public List<SelfTableTop> selectSelfTableTopList(SelfTableTop selfTableTop)
    {
        return selfTableTopMapper.selectSelfTableTopList(selfTableTop);
    }

    /**
     * 新增桌台信息
     * 
     * @param selfTableTop 桌台信息
     * @return 结果
     */
    @Override
    public int insertSelfTableTop(SelfTableTop selfTableTop)
    {
        //先新增桌台，然后返回桌台主键，再新增桌台相册，根据selfTableTop.getImages，先把图片路径根据逗号分割成数组，然后循环插入
        int rows = selfTableTopMapper.insertSelfTableTop(selfTableTop);
        if (rows > 0) {
            Long tableId = selfTableTop.getTableId();
            String[] images = selfTableTop.getImages().split(",");
            for (String image : images) {
                SelfTableTopAlbum selfTableTopAlbum = new SelfTableTopAlbum();
                selfTableTopAlbum.setTableId(tableId);
                selfTableTopAlbum.setImageUrl(image);
                selfTableTopAlbumMapper.insertSelfTableTopAlbum(selfTableTopAlbum);
            }
        }
        return rows;
    }

    /**
     * 修改桌台信息
     * 
     * @param selfTableTop 桌台信息
     * @return 结果
     */
    @Override
    public int updateSelfTableTop(SelfTableTop selfTableTop)
    {
        //先修改桌台，然后删除桌台相册，再新增桌台相册
        selfTableTopAlbumMapper.deleteSelfTableTopAlbumByTableId(new String[]{selfTableTop.getTableId().toString()});
        String[] images = selfTableTop.getImages().split(",");
        for (String image : images) {
            SelfTableTopAlbum selfTableTopAlbum = new SelfTableTopAlbum();
            selfTableTopAlbum.setTableId(selfTableTop.getTableId());
            selfTableTopAlbum.setImageUrl(image);
            selfTableTopAlbumMapper.insertSelfTableTopAlbum(selfTableTopAlbum);
        }
        return selfTableTopMapper.updateSelfTableTop(selfTableTop);
    }

    /**
     * 批量删除桌台信息
     *
     * @param tableIds 需要删除的桌台信息主键
     * @return 结果
     */
    @Override
    public int deleteSelfTableTopByTableIds(String tableIds)
    {
        //删除桌台相册
        selfTableTopAlbumMapper.deleteSelfTableTopAlbumByTableId(Convert.toStrArray(tableIds));
        return selfTableTopMapper.deleteSelfTableTopByTableIds(Convert.toStrArray(tableIds));
    }

    /**
     * 根据桌台编码及门店id查重
     *
     * @param selfTableTop 桌台信息
     * @return 桌台信息集合
     */
    @Override
    public int selectCountSelfTableTopByTableCode(SelfTableTop selfTableTop) {
        return selfTableTopMapper.selectCountSelfTableTopByTableCode(selfTableTop);
    }
}