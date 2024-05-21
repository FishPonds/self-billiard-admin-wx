package com.lingdu.project.billiard.service.impl;

import com.lingdu.project.billiard.domain.*;
import com.lingdu.project.billiard.mapper.SelfStoreInformationMapper;
import com.lingdu.project.billiard.mapper.SelfStorePhotoAlbumMapper;
import com.lingdu.project.billiard.mapper.SelfTableTopAlbumMapper;
import com.lingdu.project.billiard.mapper.SelfTableTopMapper;
import com.lingdu.project.billiard.service.ISelfStoreInformationService;
import com.lingdu.project.billiard.service.ISelfTableTopAlbumService;
import com.lingdu.project.billiard.service.ISelfTableTopService;
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
public class SelfStoreInformationServiceImpl implements ISelfStoreInformationService {
    @Autowired
    private SelfStoreInformationMapper selfStoreInformationMapper;
    @Autowired
    private SelfStorePhotoAlbumMapper selfStorePhotoAlbumMapper;
    @Autowired
    private SelfTableTopMapper selfTableTopMapper;
    @Autowired
    private SelfTableTopAlbumMapper selfTableTopAlbumMapper;

    /**
     * 查询自助台球系统门店信息
     *
     * @param storeId 自助台球系统门店信息主键
     * @return 自助台球系统门店信息
     */
    @Override
    public SelfStoreVO selectSelfStoreInformationByStoreId(Long storeId) {

        // 查询门店信息
        SelfStoreInformation selfStoreInformation = selfStoreInformationMapper.selectSelfStoreInformationByStoreId(storeId);
        // 查询门店相册信息,返回的是一个列表
        List<SelfStorePhotoAlbum> selfStorePhotoAlbums = selfStorePhotoAlbumMapper.selectSelfStorePhotoAlbumList(new SelfStorePhotoAlbum(storeId));
        // 将门店相册信息设置到门店相册URL数组中
        if (selfStorePhotoAlbums != null && !selfStorePhotoAlbums.isEmpty()) {
            String[] photoUrls = selfStorePhotoAlbums.stream()
                    .map(SelfStorePhotoAlbum::getAlbumName)
                    .toArray(String[]::new);
            selfStoreInformation.setPhotoUrls(photoUrls);
        } else {
            selfStoreInformation.setPhotoUrls(new String[0]);
        }
        //根据门店id查询桌台信息
        List<SelfTableTop> selfTableTops = selfTableTopMapper.selectSelfTableTopList(new SelfTableTop(storeId));
        //门店桌台根据桌台类型进行排序
        selfTableTops.sort(Comparator.comparing(SelfTableTop::getTableType));
        //根据桌台id查询桌台图片
        selfTableTops.forEach(selfTableTop -> {
            List<SelfTableTopAlbum> selfTableTopAlbums = selfTableTopAlbumMapper.selectSelfTableTopAlbumList(new SelfTableTopAlbum(selfTableTop.getTableId()));
            if (selfTableTopAlbums != null && !selfTableTopAlbums.isEmpty()) {
                String[] tablePhotoUrls = selfTableTopAlbums.stream()
                        .map(SelfTableTopAlbum::getImageUrl)
                        .toArray(String[]::new);
                selfTableTop.setImagesUrl(tablePhotoUrls);
            } else {
                selfTableTop.setImagesUrl(new String[0]);
            }
        });
        return new SelfStoreVO(selfStoreInformation,selfTableTops);
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
    public int insertSelfStoreInformation(SelfStoreInformation selfStoreInformation) {
        return selfStoreInformationMapper.insertSelfStoreInformation(selfStoreInformation);
    }

    /**
     * 修改自助台球系统门店信息
     *
     * @param selfStoreInformation 自助台球系统门店信息
     * @return 结果
     */
    @Override
    public int updateSelfStoreInformation(SelfStoreInformation selfStoreInformation) {
        return selfStoreInformationMapper.updateSelfStoreInformation(selfStoreInformation);
    }

    /**
     * 批量删除自助台球系统门店信息
     *
     * @param storeIds 需要删除的自助台球系统门店信息主键
     * @return 结果
     */
    @Override
    public int deleteSelfStoreInformationByStoreIds(Long[] storeIds) {
        return selfStoreInformationMapper.deleteSelfStoreInformationByStoreIds(storeIds);
    }

    /**
     * 删除自助台球系统门店信息信息
     *
     * @param storeId 自助台球系统门店信息主键
     * @return 结果
     */
    @Override
    public int deleteSelfStoreInformationByStoreId(Long storeId) {
        return selfStoreInformationMapper.deleteSelfStoreInformationByStoreId(storeId);
    }
}