package com.lingdu.framework.shiro.service;

import com.lingdu.project.billiard.domain.SelfUser;
import com.lingdu.project.billiard.service.ISelfUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.lingdu.common.constant.Constants;
import com.lingdu.common.constant.ShiroConstants;
import com.lingdu.common.constant.UserConstants;
import com.lingdu.common.exception.user.CaptchaException;
import com.lingdu.common.exception.user.UserBlockedException;
import com.lingdu.common.exception.user.UserDeleteException;
import com.lingdu.common.exception.user.UserNotExistsException;
import com.lingdu.common.exception.user.UserPasswordNotMatchException;
import com.lingdu.common.utils.DateUtils;
import com.lingdu.common.utils.MessageUtils;
import com.lingdu.common.utils.ServletUtils;
import com.lingdu.common.utils.StringUtils;
import com.lingdu.common.utils.security.ShiroUtils;
import com.lingdu.framework.manager.AsyncManager;
import com.lingdu.framework.manager.factory.AsyncFactory;
import com.lingdu.project.system.user.domain.UserStatus;
import com.lingdu.project.system.user.service.IUserService;

/**
 * 登录校验方法
 * 
 * @author 猛男波波
 */
@Component
public class LoginService
{
    @Autowired
    private PasswordService passwordService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ISelfUserService selfUserService;

    /**
     * 登录
     */
    public SelfUser login(String username, String password)
    {
        // 验证码校验
        if (ShiroConstants.CAPTCHA_ERROR.equals(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA)))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null")));
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }

        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new UserPasswordNotMatchException();
        }

        // 查询用户信息
        SelfUser user = selfUserService.selectSelfUserByLoginName(username);

        /** 
        if (user == null && maybeMobilePhoneNumber(username))
        {
            user = userService.selectUserByPhoneNumber(username);
        }

        if (user == null && maybeEmail(username))
        {
            user = userService.selectUserByEmail(username);
        }
        */

        if (user == null)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.not.exists")));
            throw new UserNotExistsException();
        }
        
        if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.delete")));
            throw new UserDeleteException();
        }
        
        if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.blocked", user.getRemark())));
            throw new UserBlockedException();
        }

        passwordService.validate(user, password);

        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        recordLoginInfo(user.getSelfUserId());
        return user;
    }

    /**
    private boolean maybeEmail(String username)
    {
        if (!username.matches(UserConstants.EMAIL_PATTERN))
        {
            return false;
        }
        return true;
    }

    private boolean maybeMobilePhoneNumber(String username)
    {
        if (!username.matches(UserConstants.MOBILE_PHONE_NUMBER_PATTERN))
        {
            return false;
        }
        return true;
    }
    */

    /**
     * 记录登录信息
     *
     * @param selfUserId 商户ID
     */
    public void recordLoginInfo(Long selfUserId)
    {
        SelfUser selfUser = new SelfUser();
        selfUser.setSelfUserId(selfUserId);
        selfUser.setLoginIp(ShiroUtils.getIp());
        selfUser.setLoginDate(DateUtils.getNowDate());
        selfUserService.updateSelfUserInfo(selfUser);
    }
}