package com.lingdu.common.exception.user;

/**
 * 角色锁定异常类
 * 
 * @author 猛男波波
 */
public class RoleBlockedException extends UserException
{
    private static final long serialVersionUID = 1L;

    public RoleBlockedException()
    {
        super("role.blocked", null);
    }

}