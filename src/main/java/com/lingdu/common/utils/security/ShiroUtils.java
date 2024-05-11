package com.lingdu.common.utils.security;

import com.lingdu.project.billiard.domain.SelfMerchants;
import com.lingdu.project.billiard.domain.SelfUser;
import com.lingdu.project.billiard.domain.SelfUserProfile;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import com.lingdu.common.utils.StringUtils;
import com.lingdu.common.utils.bean.BeanUtils;
import com.lingdu.project.system.user.domain.User;

/**
 * shiro 工具类
 * 
 * @author 猛男波波
 */
public class ShiroUtils
{
    public static Subject getSubject()
    {
        return SecurityUtils.getSubject();
    }

    public static Session getSession()
    {
        return SecurityUtils.getSubject().getSession();
    }

    public static void logout()
    {
        getSubject().logout();
    }


    public static SelfUser getSelfUser()
    {
        SelfUser selfUser = null;
        Object obj = getSubject().getPrincipal();
        if (StringUtils.isNotNull(obj))
        {
            if(obj instanceof SelfUserProfile){
                SelfUserProfile selfUserProfile = (SelfUserProfile) obj;
                selfUser = new SelfUser();
                BeanUtils.copyBeanProp(selfUser, selfUserProfile.getSelfUser());
            }else{
                selfUser = new SelfUser();
                BeanUtils.copyBeanProp(selfUser, obj);
            }
        }
        return selfUser;
    }

    public static SelfMerchants getSelfMerchants()
    {
        SelfMerchants selfMerchants = null;
        Object obj = getSubject().getPrincipal();
        if (StringUtils.isNotNull(obj))
        {
            if(obj instanceof SelfUserProfile){
                SelfUserProfile selfUserProfile = (SelfUserProfile) obj;
                selfMerchants = selfUserProfile.getSelfMerchants();
            }
        }
        return selfMerchants;
    }

    public static void setSelfUser(SelfUserProfile selfUserProfile)
    {
        Subject subject = getSubject();
        PrincipalCollection principalCollection = subject.getPrincipals();
        String realmName = principalCollection.getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(selfUserProfile, realmName);
        // 重新加载Principal
        subject.runAs(newPrincipalCollection);
    }



    public static Long getSelfUserId()
    {
        return getSelfUser().getSelfUserId().longValue();
    }

    public static String getLoginName()
    {
        return getSelfUser().getLoginName();
    }

    public static String getIp()
    {
        return getSubject().getSession().getHost();
    }

    public static String getSessionId()
    {
        return String.valueOf(getSubject().getSession().getId());
    }
}