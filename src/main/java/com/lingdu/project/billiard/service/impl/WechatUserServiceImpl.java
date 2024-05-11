package com.lingdu.project.billiard.service.impl;

import com.lingdu.common.utils.DateUtils;
import com.lingdu.project.billiard.domain.WechatUser;
import com.lingdu.project.billiard.mapper.WechatUserMapper;
import com.lingdu.project.billiard.service.IWechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 微信用户Service业务层处理
 * 
 * @author 猛男波波
 * @date 2024-05-01
 */
@Service
public class WechatUserServiceImpl implements IWechatUserService
{
    @Autowired
    private WechatUserMapper wechatUserMapper;

    /**
     * 查询微信用户
     * 
     * @param userId 微信用户主键
     * @return 微信用户
     */
    @Override
    public WechatUser selectWechatUserByUserId(Long userId)
    {
        return wechatUserMapper.selectWechatUserByUserId(userId);
    }

    /**
     * 查询微信用户列表
     * 
     * @param wechatUser 微信用户
     * @return 微信用户
     */
    @Override
    public List<WechatUser> selectWechatUserList(WechatUser wechatUser)
    {
        return wechatUserMapper.selectWechatUserList(wechatUser);
    }

    /**
     * 新增微信用户
     * 
     * @param wechatUser 微信用户
     * @return 结果
     */
    @Override
    public int insertWechatUser(WechatUser wechatUser)
    {
        wechatUser.setCreateTime(DateUtils.getNowDate());
        return wechatUserMapper.insertWechatUser(wechatUser);
    }

    /**
     * 修改微信用户
     * 
     * @param wechatUser 微信用户
     * @return 结果
     */
    @Override
    public int updateWechatUser(WechatUser wechatUser)
    {
        return wechatUserMapper.updateWechatUser(wechatUser);
    }

    /**
     * 批量删除微信用户
     * 
     * @param userIds 需要删除的微信用户主键
     * @return 结果
     */
    @Override
    public int deleteWechatUserByUserIds(Long[] userIds)
    {
        return wechatUserMapper.deleteWechatUserByUserIds(userIds);
    }

    /**
     * 删除微信用户信息
     * 
     * @param userId 微信用户主键
     * @return 结果
     */
    @Override
    public int deleteWechatUserByUserId(Long userId)
    {
        return wechatUserMapper.deleteWechatUserByUserId(userId);
    }

    /**
     * 通过openid查询是否存在
     *
     * @param openid 微信用户openid
     * @return 微信用户
     */
    @Override
    public WechatUser selectWechatUserOpenID(String openid) {
        return wechatUserMapper.selectWechatUserOpenID(openid);
    }

}