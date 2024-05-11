package com.lingdu.project.billiard.domain;

import com.lingdu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * 台球系统菜单权限对象 self_menu
 * 
 * @author 猛男波波
 * @date 2024-04-28
 */
public class SelfMenu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 菜单ID */
    private Long selfMenuId;

    /** 菜单名称 */
    private String selfMenuName;

    /** 父菜单名称 */
    private String parentName;

    /** 父菜单ID */
    private Long parentId;

    /** 显示顺序 */
    private Integer orderNum;

    /** 菜单URL */
    private String url;

    /** 打开方式（menuItem页签 menuBlank新窗口） */
    private String target;

    /** 类型（M目录 C菜单 F按钮） */
    private String menuType;

    /** 菜单状态（0显示 1隐藏） */
    private String visible;

    /** 是否刷新（0刷新 1不刷新） */
    private String isRefresh;

    /** 权限字符串 */
    private String perms;

    /** 菜单图标 */
    private String icon;

    /** 菜单状态（0正常 1停用） */
    private String status;

    /** 子菜单 */
    private List<SelfMenu> children = new ArrayList<SelfMenu>();

    public Long getSelfMenuId()
    {
        return selfMenuId;
    }

    public void setSelfMenuId(Long selfMenuId)
    {
        this.selfMenuId = selfMenuId;
    }

    @NotBlank(message = "菜单名称不能为空")
    @Size(min = 0, max = 50, message = "菜单名称长度不能超过50个字符")
    public String getSelfMenuName()
    {
        return selfMenuName;
    }

    public void setSelfMenuName(String selfMenuName)
    {
        this.selfMenuName = selfMenuName;
    }

    public String getParentName()
    {
        return parentName;
    }

    public void setParentName(String parentName)
    {
        this.parentName = parentName;
    }

    public Long getParentId()
    {
        return parentId;
    }

    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    @NotNull(message = "显示顺序不能为空")
    public Integer getOrderNum()
    {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum)
    {
        this.orderNum = orderNum;
    }

    public String getIsRefresh() {
        return isRefresh;
    }

    public void setIsRefresh(String isRefresh) {
        this.isRefresh = isRefresh;
    }

    @NotBlank(message = "菜单类型不能为空")
    public String getMenuType()
    {
        return menuType;
    }

    public void setMenuType(String menuType)
    {
        this.menuType = menuType;
    }

    public String getVisible()
    {
        return visible;
    }

    public void setVisible(String visible)
    {
        this.visible = visible;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @Size(min = 0, max = 100, message = "权限标识长度不能超过100个字符")
    public String getPerms()
    {
        return perms;
    }

    public void setPerms(String perms)
    {
        this.perms = perms;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public List<SelfMenu> getChildren()
    {
        return children;
    }

    public void setChildren(List<SelfMenu> children)
    {
        this.children = children;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("selfMenuId", getSelfMenuId())
                .append("selfMenuName", getSelfMenuName())
                .append("parentId", getParentId())
                .append("parentName", getParentName())
                .append("orderNum", getOrderNum())
                .append("isRefresh", getIsRefresh())
                .append("menuType", getMenuType())
                .append("visible", getVisible())
                .append("status ", getStatus())
                .append("perms", getPerms())
                .append("icon", getIcon())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("url", getUrl())
                .append("target", getTarget())
                .toString();
    }
}