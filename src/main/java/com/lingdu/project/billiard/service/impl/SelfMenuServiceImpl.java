package com.lingdu.project.billiard.service.impl;

import com.lingdu.common.utils.StringUtils;
import com.lingdu.common.utils.TreeUtils;
import com.lingdu.common.utils.security.ShiroUtils;
import com.lingdu.framework.web.domain.Ztree;
import com.lingdu.project.billiard.domain.SelfMenu;
import com.lingdu.project.billiard.domain.SelfRole;
import com.lingdu.project.billiard.domain.SelfUser;
import com.lingdu.project.billiard.mapper.SelfMenuMapper;
import com.lingdu.project.billiard.mapper.SelfRoleMapper;
import com.lingdu.project.billiard.mapper.SelfRoleMenuMapper;
import com.lingdu.project.billiard.service.ISelfMenuService;
import com.lingdu.project.system.menu.domain.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 台球系统菜单权限Service业务层处理
 *
 * @author 猛男波波
 * @date 2024-04-28
 */
@Service
public class SelfMenuServiceImpl implements ISelfMenuService {

    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    @Autowired
    private SelfMenuMapper selfMenuMapper;
    @Autowired
    private SelfRoleMapper selfRoleMapper;
    @Autowired
    private SelfRoleMenuMapper selfRoleMenuMapper;

    /**
     * 根据商户id查权限
     *
     * @param selfUserId 台球用户id
     * @return 台球系统菜单权限集合
     */
    @Override
    public Set<String> selectPermsBySelfUserId(Long selfUserId) {
        List<String> perms = selfMenuMapper.selectPermsBySelfUserId(selfUserId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据用户查询菜单
     *
     * @param user 用户信息
     * @return 菜单列表
     */
    @Override
    public List<SelfMenu> selectMenusBySelfUser(SelfUser user) {
        List<SelfMenu> menus = new LinkedList<SelfMenu>();
        menus = selfMenuMapper.selectMenusBySelfUserId(user.getSelfUserId());
        return TreeUtils.getChildPerms(menus, 0);
    }

    /**
     * 查询系统菜单列表
     *
     * @param menu 菜单信息
     * @return 菜单列表
     */
    @Override
    public List<SelfMenu> selectSelfMenuList(SelfMenu menu) {
        List<SelfMenu> menuList = null;
        SelfUser user = ShiroUtils.getSelfUser();
        menu.getParams().put("userId", user.getSelfUserId());
        menuList = selfMenuMapper.selectSelfMenuListBySelfUserId(menu);
        return menuList;
    }

    /**
     * 查询菜单集合
     *
     * @return 所有菜单信息
     */
    @Override
    public List<SelfMenu> selectSelfMenuAll() {
        List<SelfMenu> menuList = null;
        SelfUser user = ShiroUtils.getSelfUser();
        menuList = selfMenuMapper.selectMenuAllBySelfUserId(user.getSelfUserId());
        return menuList;
    }

    /**
     * 根据角色ID查询菜单
     *
     * @param role 角色对象
     * @return 菜单列表
     */
    @Override
    public List<Ztree> roleMenuTreeData(SelfRole role) {
        Long roleId = role.getSelfRoleId();
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<SelfMenu> menuList = selectSelfMenuAll();
        if (StringUtils.isNotNull(roleId))
        {
            List<String> roleMenuList = selfMenuMapper.selectSelfMenuTree(roleId);
            ztrees = initZtree(menuList, roleMenuList, true);
        }
        else
        {
            ztrees = initZtree(menuList, null, true);
        }
        return ztrees;
    }

    /**
     * 查询所有菜单
     *
     * @return 菜单列表
     */
    @Override
    public List<Ztree> menuTreeData()
    {
        List<SelfMenu> menuList = selectSelfMenuAll();
        List<Ztree> ztrees = initZtree(menuList);
        return ztrees;
    }


    /**
     * 对象转菜单树
     *
     * @param menuList 菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SelfMenu> menuList)
    {
        return initZtree(menuList, null, false);
    }

    /**
     * 对象转菜单树
     *
     * @param menuList 菜单列表
     * @param roleMenuList 角色已存在菜单列表
     * @param permsFlag 是否需要显示权限标识
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SelfMenu> menuList, List<String> roleMenuList, boolean permsFlag)
    {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleMenuList);
        for (SelfMenu menu : menuList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(menu.getSelfMenuId());
            ztree.setpId(menu.getParentId());
            ztree.setName(transMenuName(menu, permsFlag));
            ztree.setTitle(menu.getSelfMenuName());
            if (isCheck)
            {
                ztree.setChecked(roleMenuList.contains(menu.getSelfMenuId() + menu.getPerms()));
            }
            ztrees.add(ztree);
        }
        return ztrees;
    }

    public String transMenuName(SelfMenu menu, boolean permsFlag)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(menu.getSelfMenuName());
        if (permsFlag)
        {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + menu.getPerms() + "</font>");
        }
        return sb.toString();
    }
}