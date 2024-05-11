package com.lingdu.project.billiard.service;


import com.lingdu.project.billiard.domain.SelfUser;
import com.lingdu.project.billiard.domain.SelfUserRole;
import com.lingdu.project.system.user.domain.User;

import java.util.List;

/**
 * 用户 业务层
 * 
 * @author 猛男波波
 */
public interface ISelfUserService
{

    /**
     * 根据条件分页查询已分配用户角色列表
     * 
     * @param selfUser 用户信息
     * @return 用户信息集合信息
     */
    public List<SelfUser> selectAllocatedList(SelfUser selfUser);

    /**
     * 根据条件分页查询未分配用户角色列表
     * 
     * @param selfUser 用户信息
     * @return 用户信息集合信息
     */
    public List<SelfUser> selectUnallocatedList(SelfUser selfUser);

    /**
     * 通过用户名查询用户
     * 
     * @param selfUserName 用户名
     * @return 用户对象信息
     */
    public SelfUser selectSelfUserByLoginName(String selfUserName);

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
     * @param selfUserId 用户ID
     * @return 用户对象信息
     */
    public SelfUser selectSelfUserById(Long selfUserId);

    /**
     * 通过用户ID查询用户和角色关联
     * 
     * @param selfUserId 用户ID
     * @return 用户和角色关联列表
     */
    public List<SelfUserRole> selectSelfUserRoleBySelfUserId(Long selfUserId);

    /**
     * 注册用户信息
     * 
     * @param selfUser 用户信息
     * @return 结果
     */
    public boolean registerSelfUser(SelfUser selfUser);

    /**
     * 修改用户详细信息
     * 
     * @param selfUser 用户信息
     * @return 结果
     */
    public int updateSelfUserInfo(SelfUser selfUser);

    /**
     * 修改用户密码信息
     * 
     * @param selfUser 用户信息
     * @return 结果
     */
    public int resetSelfUserPwd(SelfUser selfUser);

    /**
     * 校验用户名称是否唯一
     * 
     * @param loginName 登录名称
     * @return 结果
     */
    public String checkLoginNameUnique(String loginName);

    /**
     * 校验手机号码是否唯一
     *
     * @param selfUser 用户信息
     * @return 结果
     */
    public String checkPhoneUnique(SelfUser selfUser);

    /**
     * 校验email是否唯一
     *
     * @param selfUser 用户信息
     * @return 结果
     */
    public String checkEmailUnique(SelfUser selfUser);

    /**
     * 保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    public int insertSelfUser(SelfUser user);

    /**
     * 导入用户数据
     * 
     * @param selfUserList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 结果
     */
    public String importSelfUser(List<SelfUser> selfUserList, Boolean isUpdateSupport);

    /**
     * 用户状态修改
     * 
     * @param selfUser 用户信息
     * @return 结果
     */
    public int changeStatus(SelfUser selfUser);

    /**
     * 根据用户ID查询用户所属角色组
     *
     * @param selfUserId 用户ID
     * @return 结果
     */
    public String selectSelfUserRoleGroup(Long selfUserId);

}