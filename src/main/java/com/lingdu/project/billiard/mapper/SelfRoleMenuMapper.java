package com.lingdu.project.billiard.mapper;


import com.lingdu.project.billiard.domain.SelfRoleMenu;

import java.util.List;

/**
 * 角色与菜单关联表 数据层
 * 
 * @author 猛男波波
 */
public interface SelfRoleMenuMapper
{
    /**
     * 查询菜单使用数量
     * 
     * @param selfMenuId 菜单ID
     * @return 结果
     */
    public int checkMenuExistRole(Long selfMenuId);

    /**
     * 通过角色ID删除角色和菜单关联
     * 
     * @param selfRoleId 角色ID
     * @return 结果
     */
    public int deleteRoleMenu(Long selfRoleId);

    /**
     * 批量删除角色菜单关联信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRoleMenu(Long[] ids);

    /**
     * 批量新增角色菜单信息
     * 
     * @param roleMenuList 角色菜单列表
     * @return 结果
     */
    public int batchRoleMenu(List<SelfRoleMenu> roleMenuList);
}