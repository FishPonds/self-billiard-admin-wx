package com.lingdu.project.billiard.mapper;


import com.lingdu.project.billiard.domain.SelfRole;

import java.util.List;
import java.util.Set;

/**
 * 台球系统角色信息Mapper接口
 * 
 * @author 猛男波波
 * @date 2024-04-28
 */
public interface SelfRoleMapper 
{
    /**
     * 查询台球系统角色信息
     * 
     * @param selfRoleId 台球系统角色信息主键
     * @return 台球系统角色信息
     */
    public SelfRole selectSelfRoleBySelfRoleId(Long selfRoleId);

    /**
     * 查询台球系统角色信息列表
     * 
     * @param selfRole 台球系统角色信息
     * @return 台球系统角色信息集合
     */
    public List<SelfRole> selectSelfRoleList(SelfRole selfRole);

    /**
     * 根据商户id查询角色列表
     *
     * @param selfUserId 台球用户id
     * @return 角色列表
     */
    public List<SelfRole> selectRolesBySelfUserId(Long selfUserId);
}