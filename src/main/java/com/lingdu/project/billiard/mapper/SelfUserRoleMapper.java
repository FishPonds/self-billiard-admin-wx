package com.lingdu.project.billiard.mapper;

import com.lingdu.project.billiard.domain.SelfUserRole;
import com.lingdu.project.system.user.domain.UserRole;

import java.util.List;

/**
 * 角色和菜单关联 数据层
 *
 * @author 猛男波波
 */
public interface SelfUserRoleMapper {

    /**
     * 通过台球用户ID查询用户和角色关联
     *
     * @param selfUserId 台球用户ID
     * @return 用户和角色关联列表
     */
    public List<SelfUserRole> selectSelfUserRoleBySelfUserId(Long selfUserId);

    /**
     * 批量新增用户角色信息
     *
     * @param userRoleList 用户角色列表
     * @return 结果
     */
    public int batchUserRole(List<UserRole> userRoleList);

}