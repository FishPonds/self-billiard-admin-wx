package com.lingdu.framework.shiro.service;

import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;

import com.lingdu.project.billiard.domain.SelfUser;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.lingdu.common.constant.Constants;
import com.lingdu.common.constant.ShiroConstants;
import com.lingdu.common.exception.user.UserPasswordNotMatchException;
import com.lingdu.common.exception.user.UserPasswordRetryLimitExceedException;
import com.lingdu.common.utils.MessageUtils;
import com.lingdu.framework.manager.AsyncManager;
import com.lingdu.framework.manager.factory.AsyncFactory;
import com.lingdu.project.system.user.domain.User;

/**
 * 登录密码方法
 * 
 * @author 猛男波波
 */
@Component
public class PasswordService
{
    @Autowired
    private CacheManager cacheManager;

    private Cache<String, AtomicInteger> loginRecordCache;

    @Value(value = "${user.password.maxRetryCount}")
    private String maxRetryCount;

    @PostConstruct
    public void init()
    {
        loginRecordCache = cacheManager.getCache(ShiroConstants.LOGINRECORDCACHE);
    }

    public void validate(SelfUser user, String password)
    {
        String loginName = user.getLoginName();

        AtomicInteger retryCount = loginRecordCache.get(loginName);

        if (retryCount == null)
        {
            retryCount = new AtomicInteger(0);
            loginRecordCache.put(loginName, retryCount);
        }
        if (retryCount.incrementAndGet() > Integer.valueOf(maxRetryCount).intValue())
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.retry.limit.exceed", maxRetryCount)));
            throw new UserPasswordRetryLimitExceedException(Integer.valueOf(maxRetryCount).intValue());
        }

        if (!matches(user, password))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGIN_FAIL, MessageUtils.message("user.password.retry.limit.count", retryCount)));
            loginRecordCache.put(loginName, retryCount);
            throw new UserPasswordNotMatchException();
        }
        else
        {
            clearLoginRecordCache(loginName);
        }
    }

    public boolean matches(SelfUser user, String newPassword)
    {
        return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
    }

    public void clearLoginRecordCache(String loginName)
    {
        loginRecordCache.remove(loginName);
    }

    public String encryptPassword(String loginName, String password, String salt)
    {
        return new Md5Hash(loginName + password + salt).toHex();
    }

    public static void main(String[] args)
    {
        System.out.println(new PasswordService().encryptPassword("零度测试", "admin123", "111111"));
        System.out.println(new PasswordService().encryptPassword("ry", "admin123", "222222"));
    }
}