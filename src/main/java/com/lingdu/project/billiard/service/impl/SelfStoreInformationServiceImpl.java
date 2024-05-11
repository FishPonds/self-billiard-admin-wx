package com.lingdu.project.billiard.service.impl;

import com.lingdu.common.exception.ServiceException;
import com.lingdu.project.billiard.domain.SelfStoreInformation;
import com.lingdu.project.billiard.domain.SelfStorePhotoAlbum;
import com.lingdu.project.billiard.mapper.SelfStoreInformationMapper;
import com.lingdu.project.billiard.mapper.SelfStorePhotoAlbumMapper;
import com.lingdu.project.billiard.service.ISelfStoreInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 自助台球系统门店信息Service业务层处理
 * 
 * @author 猛男波波
 * @date 2024-05-03
 */
@Service
public class SelfStoreInformationServiceImpl implements ISelfStoreInformationService
{
    @Autowired
    private SelfStoreInformationMapper selfStoreInformationMapper;
    @Autowired
    private SelfStorePhotoAlbumMapper selfStorePhotoAlbumMapper;

    /**
     * 查询自助台球系统门店信息
     * 
     * @param storeId 自助台球系统门店信息主键
     * @return 自助台球系统门店信息
     */
    @Override
    public SelfStoreInformation selectSelfStoreInformationByStoreId(Long storeId)
    {
        return selfStoreInformationMapper.selectSelfStoreInformationByStoreId(storeId);
    }

    /**
     * 查询自助台球系统门店信息列表
     * 
     * @param selfStoreInformation 自助台球系统门店信息
     * @return 自助台球系统门店信息
     */
    @Override
    public List<SelfStoreInformation> selectSelfStoreInformationList(SelfStoreInformation selfStoreInformation) {
        List<SelfStoreInformation> selfStoreInformations = selfStoreInformationMapper.selectSelfStoreInformationList(selfStoreInformation);

//        if (selfStoreInformations == null || selfStoreInformations.isEmpty()) {
//            throw new ServiceException("未查询到门店信息");
//        }

        // 收集所有门店ID
        List<Long> storeIds = selfStoreInformations.stream()
                .map(SelfStoreInformation::getStoreId)
                .collect(Collectors.toList());

        // 根据门店ID一次性查询所有门店相册信息
        Map<Long, List<SelfStorePhotoAlbum>> photoAlbumMap = new HashMap<>();
        if (!storeIds.isEmpty()) {
            List<SelfStorePhotoAlbum> allPhotoAlbums = selfStorePhotoAlbumMapper.selectSelfStorePhotoAlbumListByStoreId(storeIds);
            for (SelfStorePhotoAlbum photoAlbum : allPhotoAlbums) {
                photoAlbumMap.computeIfAbsent(photoAlbum.getStoreId(), k -> new ArrayList<>()).add(photoAlbum);
            }
        }

        // 将门店相册信息设置到对应的门店信息对象中
        for (SelfStoreInformation storeInfo : selfStoreInformations) {
            List<SelfStorePhotoAlbum> selfStorePhotoAlbums = photoAlbumMap.getOrDefault(storeInfo.getStoreId(), Collections.emptyList());
//            if (selfStorePhotoAlbums.isEmpty()) {
//                throw new ServiceException("未查询到门店相册信息");
//            }
            storeInfo.setSelfStorePhotoAlbums(selfStorePhotoAlbums);
        }

        return selfStoreInformations;
    }

    /**
     * 新增自助台球系统门店信息
     * 
     * @param selfStoreInformation 自助台球系统门店信息
     * @return 结果
     */
    @Override
    public int insertSelfStoreInformation(SelfStoreInformation selfStoreInformation)
    {
        return selfStoreInformationMapper.insertSelfStoreInformation(selfStoreInformation);
    }

    /**
     * 修改自助台球系统门店信息
     * 
     * @param selfStoreInformation 自助台球系统门店信息
     * @return 结果
     */
    @Override
    public int updateSelfStoreInformation(SelfStoreInformation selfStoreInformation)
    {
        return selfStoreInformationMapper.updateSelfStoreInformation(selfStoreInformation);
    }

    /**
     * 批量删除自助台球系统门店信息
     * 
     * @param storeIds 需要删除的自助台球系统门店信息主键
     * @return 结果
     */
    @Override
    public int deleteSelfStoreInformationByStoreIds(Long[] storeIds)
    {
        return selfStoreInformationMapper.deleteSelfStoreInformationByStoreIds(storeIds);
    }

    /**
     * 删除自助台球系统门店信息信息
     * 
     * @param storeId 自助台球系统门店信息主键
     * @return 结果
     */
    @Override
    public int deleteSelfStoreInformationByStoreId(Long storeId)
    {
        return selfStoreInformationMapper.deleteSelfStoreInformationByStoreId(storeId);
    }
}