package com.lingdu.project.billiard.service;


import com.lingdu.project.billiard.domain.SelfStoreInformation;

import java.util.List;

/**
 * 自助台球系统门店信息Service接口
 * 
 * @author 猛男波波
 * @date 2024-05-03
 */
public interface ISelfStoreInformationService 
{
    /**
     * 查询自助台球系统门店信息
     * 
     * @param storeId 自助台球系统门店信息主键
     * @return 自助台球系统门店信息
     */
    public SelfStoreInformation selectSelfStoreInformationByStoreId(Long storeId);

    /**
     * 查询自助台球系统门店信息列表
     * 
     * @param selfStoreInformation 自助台球系统门店信息
     * @return 自助台球系统门店信息集合
     */
    public List<SelfStoreInformation> selectSelfStoreInformationList(SelfStoreInformation selfStoreInformation);

    /**
     * 新增自助台球系统门店信息
     * 
     * @param selfStoreInformation 自助台球系统门店信息
     * @return 结果
     */
    public int insertSelfStoreInformation(SelfStoreInformation selfStoreInformation);

    /**
     * 修改自助台球系统门店信息
     * 
     * @param selfStoreInformation 自助台球系统门店信息
     * @return 结果
     */
    public int updateSelfStoreInformation(SelfStoreInformation selfStoreInformation);

    /**
     * 批量删除自助台球系统门店信息
     * 
     * @param storeIds 需要删除的自助台球系统门店信息主键集合
     * @return 结果
     */
    public int deleteSelfStoreInformationByStoreIds(Long[] storeIds);

    /**
     * 删除自助台球系统门店信息信息
     * 
     * @param storeId 自助台球系统门店信息主键
     * @return 结果
     */
    public int deleteSelfStoreInformationByStoreId(Long storeId);
}