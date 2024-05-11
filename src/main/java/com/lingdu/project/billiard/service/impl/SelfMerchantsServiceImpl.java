package com.lingdu.project.billiard.service.impl;

import com.lingdu.common.exception.ServiceException;
import com.lingdu.common.utils.StringUtils;
import com.lingdu.project.billiard.domain.SelfMerchants;
import com.lingdu.project.billiard.domain.SelfUserRole;
import com.lingdu.project.billiard.mapper.SelfMerchantsMapper;
import com.lingdu.project.billiard.mapper.SelfUserRoleMapper;
import com.lingdu.project.billiard.service.ISelfMerchantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商户账号信息Service业务层处理
 *
 * @author 猛男波波
 * @date 2024-04-26
 */
@Service
public class SelfMerchantsServiceImpl implements ISelfMerchantsService {
    @Autowired
    private SelfMerchantsMapper selfMerchantsMapper;

    /**
     * 查询商户账号信息
     *
     * @param customMerchantId 商户账号信息主键
     * @return 商户账号信息
     */
    @Override
    public SelfMerchants selectMerchantsByCustomMerchantId(Long customMerchantId) {
        return selfMerchantsMapper.selectMerchantsByCustomMerchantId(customMerchantId);
    }

    /**
     * 查询父商户账号信息列表
     *
     * @param selfMerchants 商户账号信息
     */
    public List<SelfMerchants> selectParentMerchantsList(SelfMerchants selfMerchants) {
        return selfMerchantsMapper.selectParentMerchantsList(selfMerchants);
    }

    /**
     * 查询商户账号信息列表
     *
     * @param selfMerchants 商户账号信息
     * @return 商户账号信息
     */
    @Override
    public List<SelfMerchants> selectMerchantsList(SelfMerchants selfMerchants) {
        return selfMerchantsMapper.selectMerchantsList(selfMerchants);
    }

    /**
     * 通过selfUserId查询所有商户账号信息列表
     */
    @Override
    public SelfMerchants selectMerchantsListBySelfUserId(Long selfUserId) {
        //通过customMerchantIds查询子商户账号信息列表
        return selfMerchantsMapper.selectMerchantsListBySelfUserId(selfUserId);
    }

    /**
     * 新增商户账号信息
     *
     * @param selfMerchants 商户账号信息
     * @return 结果
     */
    @Override
    public int insertMerchants(SelfMerchants selfMerchants) {
        //校验商户key是否唯一，如果唯一则新增，否则抛出商户key已存在异常
        if (selfMerchants.getCustomMerchantId() == null) {
            if (selfMerchantsMapper.checkCustomMerchantKeyUnique(selfMerchants.getMerchantKey()) > 0) {
                throw new ServiceException("商户key已存在");
            }
        }
        return selfMerchantsMapper.insertMerchants(selfMerchants);
    }

    /**
     * 修改商户账号信息
     *
     * @param selfMerchants 商户账号信息
     * @return 结果
     */
    @Override
    public int updateMerchants(SelfMerchants selfMerchants) {
        return selfMerchantsMapper.updateMerchants(selfMerchants);
    }

    /**
     * 批量删除商户账号信息
     *
     * @param customMerchantIds 需要删除的商户账号信息主键
     * @return 结果
     */
    @Override
    public int deleteMerchantsByCustomMerchantIds(Long[] customMerchantIds) {
        return selfMerchantsMapper.deleteMerchantsByCustomMerchantIds(customMerchantIds);
    }

    /**
     * 删除商户账号信息信息
     *
     * @param customMerchantId 商户账号信息主键
     * @return 结果
     */
    @Override
    public int deleteMerchantsByCustomMerchantId(Long customMerchantId) {
        return selfMerchantsMapper.deleteMerchantsByCustomMerchantId(customMerchantId);
    }

    /**
     * 校验商户key是否唯一
     *
     * @param merchantKey 商户key
     * @return 结果
     */
    @Override
    public int checkCustomMerchantKeyUnique(String merchantKey) {
        return selfMerchantsMapper.checkCustomMerchantKeyUnique(merchantKey);
    }

}