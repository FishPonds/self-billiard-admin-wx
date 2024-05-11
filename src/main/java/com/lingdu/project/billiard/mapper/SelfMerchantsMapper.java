package com.lingdu.project.billiard.mapper;

import com.lingdu.project.billiard.domain.SelfMerchants;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商户账号信息Mapper接口
 * 
 * @author 猛男波波
 * @date 2024-04-26
 */
public interface SelfMerchantsMapper
{
    /**
     * 查询商户账号信息
     * 
     * @param customMerchantId 商户账号信息主键
     * @return 商户账号信息
     */
    public SelfMerchants selectMerchantsByCustomMerchantId(Long customMerchantId);

    /**
     * 查询商户账号信息列表
     * 
     * @param selfMerchants 商户账号信息
     * @return 商户账号信息集合
     */
    public List<SelfMerchants> selectMerchantsList(SelfMerchants selfMerchants);

    /**
     * 通过customMerchantIds查询子商户账号信息列表
     *
     * @param selfUserId 需要查询的数据主键
     * @return 商户账号信息集合
     */
    public SelfMerchants selectMerchantsListBySelfUserId(@Param("selfUserId") Long selfUserId);

    /**
     * 查询父商户账号信息列表
     *
     * @param selfMerchants 商户账号信息
     * @return 父商户账号信息集合
     */
    public List<SelfMerchants> selectParentMerchantsList(SelfMerchants selfMerchants);

    /**
     * 新增商户账号信息
     * 
     * @param selfMerchants 商户账号信息
     * @return 结果
     */
    public int insertMerchants(SelfMerchants selfMerchants);

    /**
     * 修改商户账号信息
     * 
     * @param selfMerchants 商户账号信息
     * @return 结果
     */
    public int updateMerchants(SelfMerchants selfMerchants);

    /**
     * 删除商户账号信息
     * 
     * @param customMerchantId 商户账号信息主键
     * @return 结果
     */
    public int deleteMerchantsByCustomMerchantId(Long customMerchantId);

    /**
     * 批量删除商户账号信息
     * 
     * @param customMerchantIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMerchantsByCustomMerchantIds(Long[] customMerchantIds);

    /**
     * 校验商户key是否唯一
     *
     * @param merchantKey 商户key
     * @return 结果
     */
    public int checkCustomMerchantKeyUnique(String merchantKey);


    public int updateMerchantsProFile(SelfMerchants selfMerchants);

}