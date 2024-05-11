package com.lingdu.project.billiard.service;


import com.lingdu.framework.web.domain.Ztree;
import com.lingdu.project.billiard.domain.SelfMenu;
import com.lingdu.project.billiard.domain.SelfRole;
import com.lingdu.project.billiard.domain.SelfUser;

import java.util.List;
import java.util.Set;

/**
 * 台球系统菜单权限Service接口
 *
 * @author 猛男波波
 * @date 2024-04-28
 */
public interface ISelfMenuService {
    /**
     * 根据台球用户id查权限
     *
     * @param selfUserId 台球用户id
     * @return 台球系统菜单权限集合
     */
    public Set<String> selectPermsBySelfUserId(Long selfUserId);

    /**
     * 根据台球用户id查询菜单
     *
     * @param user 用户信息
     * @return 菜单列表
     */
    public List<SelfMenu> selectMenusBySelfUser(SelfUser user);

    /**
     * 查询系统菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    public List<SelfMenu> selectSelfMenuList(SelfMenu menu);

    /**
     * 查询菜单集合
     *
     * @return 所有菜单信息
     */
    public List<SelfMenu> selectSelfMenuAll();

    /**
     * 根据角色ID查询菜单
     *
     * @param role 角色对象
     * @return 菜单列表
     */
    public List<Ztree> roleMenuTreeData(SelfRole role);

    /**
     * 查询所有菜单信息
     *
     * @return 菜单列表
     */
    public List<Ztree> menuTreeData();
}