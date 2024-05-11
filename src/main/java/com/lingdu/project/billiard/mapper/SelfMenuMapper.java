package com.lingdu.project.billiard.mapper;

import com.lingdu.project.billiard.domain.SelfMenu;
import com.lingdu.project.system.menu.domain.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 台球系统菜单权限Mapper接口
 *
 * @author 猛男波波
 * @date 2024-04-28
 */
public interface SelfMenuMapper {
    /**
     * 查询台球系统菜单权限
     *
     * @param selfMenuId 台球系统菜单权限主键
     * @return 台球系统菜单权限
     */
    public SelfMenu selectSelfMenuBySelfMenuId(Long selfMenuId);

    /**
     * 查询台球系统菜单权限列表
     *
     * @param selfMenu 台球系统菜单权限
     * @return 台球系统菜单权限集合
     */
    public List<SelfMenu> selectSelfMenuList(SelfMenu selfMenu);

    /**
     * 根据商户id查权限
     *
     * @param selfUserId 台球用户id
     * @return 台球系统菜单权限集合
     */
    public List<String> selectPermsBySelfUserId(Long selfUserId);

    /**
     * 根据用户ID查询菜单
     *
     * @param selfUserId 用户ID
     * @return 菜单列表
     */
    public List<SelfMenu> selectMenusBySelfUserId(Long selfUserId);

    /**
     * 查询系统菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    public List<SelfMenu> selectSelfMenuListBySelfUserId(SelfMenu menu);

    /**
     * 根据用户ID查询菜单
     *
     * @param selfUserId 用户ID
     * @return 菜单列表
     */
    public List<SelfMenu> selectMenuAllBySelfUserId(Long selfUserId);

    /**
     * 根据角色ID查询菜单
     *
     * @param selfRoleId 角色ID
     * @return 菜单列表
     */
    public List<String> selectSelfMenuTree(Long selfRoleId);
}