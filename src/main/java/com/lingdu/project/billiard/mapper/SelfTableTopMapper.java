package com.lingdu.project.billiard.mapper;

import java.util.List;
import com.lingdu.project.billiard.domain.SelfTableTop;

/**
 * 桌台信息Mapper接口
 * 
 * @author 猛男波波
 * @date 2024-05-18
 */
public interface SelfTableTopMapper 
{
    /**
     * 查询桌台信息
     * 
     * @param tableId 桌台信息主键
     * @return 桌台信息
     */
    public SelfTableTop selectSelfTableTopByTableId(Long tableId);

    /**
     * 查询桌台信息列表
     * 
     * @param selfTableTop 桌台信息
     * @return 桌台信息集合
     */
    public List<SelfTableTop> selectSelfTableTopList(SelfTableTop selfTableTop);

    /**
     * 新增桌台信息
     * 
     * @param selfTableTop 桌台信息
     * @return 结果
     */
    public int insertSelfTableTop(SelfTableTop selfTableTop);

    /**
     * 修改桌台信息
     * 
     * @param selfTableTop 桌台信息
     * @return 结果
     */
    public int updateSelfTableTop(SelfTableTop selfTableTop);

    /**
     * 批量删除桌台信息
     * 
     * @param tableIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSelfTableTopByTableIds(String[] tableIds);

    /**
     * 根据桌台编码及门店id查重
     *
     * @param selfTableTop 桌台信息
     * @return 桌台信息集合
     */
    public int selectCountSelfTableTopByTableCode(SelfTableTop selfTableTop);
}