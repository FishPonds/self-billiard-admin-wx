package com.lingdu.project.billiard.service.impl;

import com.lingdu.common.utils.StringUtils;
import com.lingdu.framework.aspectj.lang.annotation.DataScope;
import com.lingdu.project.billiard.domain.SelfRole;
import com.lingdu.project.billiard.mapper.SelfRoleMapper;
import com.lingdu.project.billiard.service.ISelfRoleService;
import com.lingdu.project.system.role.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 台球系统角色信息Service业务层处理
 *
 * @author 猛男波波
 * @date 2024-04-28
 */
@Service
public class SelfRoleServiceImpl implements ISelfRoleService {
    @Autowired
    private SelfRoleMapper selfRoleMapper;

    /**
     * 查询台球系统角色信息
     *
     * @param selfRoleId 台球系统角色信息主键
     * @return 台球系统角色信息
     */
    @Override
    public SelfRole selectSelfRoleBySelfRoleId(Long selfRoleId) {
        return selfRoleMapper.selectSelfRoleBySelfRoleId(selfRoleId);
    }

    /**
     * 查询台球系统角色信息列表
     *
     * @param selfRole 台球系统角色信息
     * @return 台球系统角色信息
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<SelfRole> selectSelfRoleList(SelfRole selfRole) {
        return selfRoleMapper.selectSelfRoleList(selfRole);
    }

    /**
     * 根据商户id查询角色列表
     *
     * @param selfUserId 台球用户id
     * @return 角色列表
     */
    @Override
    public Set<String> selectRolesBySelfUserId(Long selfUserId) {
        List<SelfRole> perms = selfRoleMapper.selectRolesBySelfUserId(selfUserId);
        Set<String> permsSet = new HashSet<>();
        for (SelfRole perm : perms)
        {
            if (StringUtils.isNotNull(perm))
            {
                permsSet.addAll(Arrays.asList(perm.getSelfRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }
}