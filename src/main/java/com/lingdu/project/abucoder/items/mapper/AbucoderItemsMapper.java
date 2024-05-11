package com.lingdu.project.abucoder.items.mapper;

import java.util.List;
import com.lingdu.project.abucoder.items.domain.AbucoderItems;

/**
 * 推荐项目Mapper接口
 * 
 * @author 阿卜 QQ932696181
 * @date 2022-05-27
 */
public interface AbucoderItemsMapper 
{
    /**
     * 查询推荐项目
     * 
     * @param id 推荐项目主键
     * @return 推荐项目
     */
    public AbucoderItems selectAbucoderItemsById(Long id);

    /**
     * 查询推荐项目列表
     * 
     * @param abucoderItems 推荐项目
     * @return 推荐项目集合
     */
    public List<AbucoderItems> selectAbucoderItemsList(AbucoderItems abucoderItems);

    /**
     * 新增推荐项目
     * 
     * @param abucoderItems 推荐项目
     * @return 结果
     */
    public int insertAbucoderItems(AbucoderItems abucoderItems);

    /**
     * 修改推荐项目
     * 
     * @param abucoderItems 推荐项目
     * @return 结果
     */
    public int updateAbucoderItems(AbucoderItems abucoderItems);

    /**
     * 删除推荐项目
     * 
     * @param id 推荐项目主键
     * @return 结果
     */
    public int deleteAbucoderItemsById(Long id);

    /**
     * 批量删除推荐项目
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAbucoderItemsByIds(String[] ids);
}