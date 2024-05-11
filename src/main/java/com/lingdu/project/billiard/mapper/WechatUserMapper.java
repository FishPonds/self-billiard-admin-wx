package com.lingdu.project.billiard.mapper;


import com.lingdu.project.billiard.domain.WechatUser;

import java.util.List;

/**
 * 微信用户Mapper接口
 * 
 * @author 猛男波波
 * @date 2024-05-01
 */
public interface WechatUserMapper 
{
    /**
     * 查询微信用户
     * 
     * @param userId 微信用户主键
     * @return 微信用户
     */
    public WechatUser selectWechatUserByUserId(Long userId);

    /**
     * 查询微信用户列表
     * 
     * @param wechatUser 微信用户
     * @return 微信用户集合
     */
    public List<WechatUser> selectWechatUserList(WechatUser wechatUser);

    /**
     * 新增微信用户
     * 
     * @param wechatUser 微信用户
     * @return 结果
     */
    public int insertWechatUser(WechatUser wechatUser);

    /**
     * 修改微信用户
     * 
     * @param wechatUser 微信用户
     * @return 结果
     */
    public int updateWechatUser(WechatUser wechatUser);

    /**
     * 删除微信用户
     * 
     * @param userId 微信用户主键
     * @return 结果
     */
    public int deleteWechatUserByUserId(Long userId);

    /**
     * 批量删除微信用户
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWechatUserByUserIds(Long[] userIds);

    /**
     * 通过openid来查询是否存在
     *
     * @param openid 微信用户openid
     * @return 微信用户
     */
    public WechatUser selectWechatUserOpenID(String openid);
}