package com.lingdu.project.abucoder.items.service.impl;

import java.util.List;
import com.lingdu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lingdu.project.abucoder.items.mapper.AbucoderItemsMapper;
import com.lingdu.project.abucoder.items.domain.AbucoderItems;
import com.lingdu.project.abucoder.items.service.IAbucoderItemsService;
import com.lingdu.common.utils.text.Convert;

/**
 * 推荐项目Service业务层处理
 * 
 * @author 阿卜 QQ932696181
 * @date 2022-05-27
 */
@Service
public class AbucoderItemsServiceImpl implements IAbucoderItemsService 
{
    @Autowired
    private AbucoderItemsMapper abucoderItemsMapper;

    /**
     * 查询推荐项目
     * 
     * @param id 推荐项目主键
     * @return 推荐项目
     */
    @Override
    public AbucoderItems selectAbucoderItemsById(Long id)
    {
        return abucoderItemsMapper.selectAbucoderItemsById(id);
    }

    /**
     * 查询推荐项目列表
     * 
     * @param abucoderItems 推荐项目
     * @return 推荐项目
     */
    @Override
    public List<AbucoderItems> selectAbucoderItemsList(AbucoderItems abucoderItems)
    {
        return abucoderItemsMapper.selectAbucoderItemsList(abucoderItems);
    }

    /**
     * 新增推荐项目
     * 
     * @param abucoderItems 推荐项目
     * @return 结果
     */
    @Override
    public int insertAbucoderItems(AbucoderItems abucoderItems)
    {
        abucoderItems.setCreateTime(DateUtils.getNowDate());
        return abucoderItemsMapper.insertAbucoderItems(abucoderItems);
    }

    /**
     * 修改推荐项目
     * 
     * @param abucoderItems 推荐项目
     * @return 结果
     */
    @Override
    public int updateAbucoderItems(AbucoderItems abucoderItems)
    {
        abucoderItems.setUpdateTime(DateUtils.getNowDate());
        return abucoderItemsMapper.updateAbucoderItems(abucoderItems);
    }

    /**
     * 批量删除推荐项目
     * 
     * @param ids 需要删除的推荐项目主键
     * @return 结果
     */
    @Override
    public int deleteAbucoderItemsByIds(String ids)
    {
        return abucoderItemsMapper.deleteAbucoderItemsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除推荐项目信息
     * 
     * @param id 推荐项目主键
     * @return 结果
     */
    @Override
    public int deleteAbucoderItemsById(Long id)
    {
        return abucoderItemsMapper.deleteAbucoderItemsById(id);
    }
}