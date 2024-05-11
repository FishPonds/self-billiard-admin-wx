package com.lingdu.common.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.lingdu.project.billiard.domain.SelfMenu;
import com.lingdu.project.system.menu.domain.Menu;

/**
 * 权限数据处理
 * 
 * @author 猛男波波
 */
public class TreeUtils
{
    /**
     * 根据父节点的ID获取所有子节点
     * 
     * @param list 分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public static List<SelfMenu> getChildPerms(List<SelfMenu> list, int parentId)
    {
        List<SelfMenu> returnList = new ArrayList<SelfMenu>();
        for (Iterator<SelfMenu> iterator = list.iterator(); iterator.hasNext();)
        {
            SelfMenu t = (SelfMenu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId)
            {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     * 
     * @param list
     * @param t
     */
    private static void recursionFn(List<SelfMenu> list, SelfMenu t)
    {
        // 得到子节点列表
        List<SelfMenu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SelfMenu tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private static List<SelfMenu> getChildList(List<SelfMenu> list, SelfMenu t)
    {

        List<SelfMenu> tlist = new ArrayList<SelfMenu>();
        Iterator<SelfMenu> it = list.iterator();
        while (it.hasNext())
        {
            SelfMenu n = (SelfMenu) it.next();
            if (n.getParentId().longValue() == t.getSelfMenuId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    List<SelfMenu> returnList = new ArrayList<SelfMenu>();

    /**
     * 根据父节点的ID获取所有子节点
     * 
     * @param list 分类表
     * @param typeId 传入的父节点ID
     * @param prefix 子节点前缀
     */
    public List<SelfMenu> getChildPerms(List<SelfMenu> list, int typeId, String prefix)
    {
        if (list == null)
        {
            return null;
        }
        for (Iterator<SelfMenu> iterator = list.iterator(); iterator.hasNext();)
        {
            SelfMenu node = (SelfMenu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (node.getParentId() == typeId)
            {
                recursionFn(list, node, prefix);
            }
            // 二、遍历所有的父节点下的所有子节点
            /*
             * if (node.getParentId()==0) { recursionFn(list, node); }
             */
        }
        return returnList;
    }

    private void recursionFn(List<SelfMenu> list, SelfMenu node, String p)
    {
        // 得到子节点列表
        List<SelfMenu> childList = getChildList(list, node);
        if (hasChild(list, node))
        {
            // 判断是否有子节点
            returnList.add(node);
            Iterator<SelfMenu> it = childList.iterator();
            while (it.hasNext())
            {
                SelfMenu n = (SelfMenu) it.next();
                n.setSelfMenuName(p + n.getSelfMenuName());
                recursionFn(list, n, p + p);
            }
        }
        else
        {
            returnList.add(node);
        }
    }

    /**
     * 判断是否有子节点
     */
    private static boolean hasChild(List<SelfMenu> list, SelfMenu t)
    {
        return getChildList(list, t).size() > 0;
    }
}