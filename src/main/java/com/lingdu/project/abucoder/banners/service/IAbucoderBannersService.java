package com.lingdu.project.abucoder.banners.service;

import java.util.List;
import com.lingdu.project.abucoder.banners.domain.AbucoderBanners;

/**
 * 轮播图Service接口
 * 
 * @author 阿卜 QQ932696181
 * @date 2022-05-27
 */
public interface IAbucoderBannersService 
{
    /**
     * 查询轮播图
     * 
     * @param id 轮播图主键
     * @return 轮播图
     */
    public AbucoderBanners selectAbucoderBannersById(Long id);

    /**
     * 查询轮播图列表
     * 
     * @param abucoderBanners 轮播图
     * @return 轮播图集合
     */
    public List<AbucoderBanners> selectAbucoderBannersList(AbucoderBanners abucoderBanners);

    /**
     * 新增轮播图
     * 
     * @param abucoderBanners 轮播图
     * @return 结果
     */
    public int insertAbucoderBanners(AbucoderBanners abucoderBanners);

    /**
     * 修改轮播图
     * 
     * @param abucoderBanners 轮播图
     * @return 结果
     */
    public int updateAbucoderBanners(AbucoderBanners abucoderBanners);

    /**
     * 批量删除轮播图
     * 
     * @param ids 需要删除的轮播图主键集合
     * @return 结果
     */
    public int deleteAbucoderBannersByIds(String ids);

    /**
     * 删除轮播图信息
     * 
     * @param id 轮播图主键
     * @return 结果
     */
    public int deleteAbucoderBannersById(Long id);
}