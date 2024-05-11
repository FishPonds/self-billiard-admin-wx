package com.lingdu.project.billiard.mapper;


import com.lingdu.project.billiard.domain.SelfUser;

import java.util.List;

/**
 * 用户表 数据层
 * 
 * @author 猛男波波
 */
public interface SelfUserMapper
{

    /**
     * 根据条件分页查询已配用户角色列表
     * 
     * @param SelfUser 用户信息
     * @return 用户信息集合信息
     */
    public List<SelfUser> selectAllocatedList(SelfUser SelfUser);

    /**
     * 根据条件分页查询未分配用户角色列表
     * 
     * @param SelfUser 用户信息
     * @return 用户信息集合信息
     */
    public List<SelfUser> selectUnallocatedList(SelfUser SelfUser);

    /**
     * 通过用户名查询用户
     * 
     * @param SelfUserName 用户名
     * @return 用户对象信息
     */
    public SelfUser selectSelfUserByLoginName(String SelfUserName);

    /**
     * 通过手机号码查询用户
     * 
     * @param phoneNumber 手机号码
     * @return 用户对象信息
     */
    public SelfUser selectSelfUserByPhoneNumber(String phoneNumber);

    /**
     * 通过邮箱查询用户
     * 
     * @param email 邮箱
     * @return 用户对象信息
     */
    public SelfUser selectSelfUserByEmail(String email);

    /**
     * 通过用户ID查询用户
     * 
     * @param SelfUserId 用户ID
     * @return 用户对象信息
     */
    public SelfUser selectSelfUserById(Long SelfUserId);

    /**
     * 修改用户信息
     * 
     * @param SelfUser 用户信息
     * @return 结果
     */
    public int updateSelfUser(SelfUser SelfUser);

    /**
     * 新增用户信息
     * 
     * @param SelfUser 用户信息
     * @return 结果
     */
    public int insertSelfUser(SelfUser SelfUser);

    /**
     * 校验用户名称是否唯一
     * 
     * @param loginName 登录名称
     * @return 结果
     */
    public int checkLoginNameUnique(String loginName);

    /**
     * 校验手机号码是否唯一
     *
     * @param phonenumber 手机号码
     * @return 结果
     */
    public SelfUser checkPhoneUnique(String phonenumber);

    /**
     * 校验email是否唯一
     *
     * @param email 用户邮箱
     * @return 结果
     */
    public SelfUser checkEmailUnique(String email);
}