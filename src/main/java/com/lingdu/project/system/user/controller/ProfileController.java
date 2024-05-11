package com.lingdu.project.system.user.controller;

import com.lingdu.project.billiard.domain.SelfUser;
import com.lingdu.project.billiard.service.ISelfUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.lingdu.common.constant.UserConstants;
import com.lingdu.common.utils.DateUtils;
import com.lingdu.common.utils.StringUtils;
import com.lingdu.common.utils.file.FileUploadUtils;
import com.lingdu.common.utils.file.MimeTypeUtils;
import com.lingdu.framework.aspectj.lang.annotation.Log;
import com.lingdu.framework.aspectj.lang.enums.BusinessType;
import com.lingdu.framework.config.RuoYiConfig;
import com.lingdu.framework.shiro.service.PasswordService;
import com.lingdu.framework.web.controller.BaseController;
import com.lingdu.framework.web.domain.AjaxResult;
import com.lingdu.project.system.user.domain.User;
import com.lingdu.project.system.user.service.IUserService;

/**
 * 个人信息 业务处理
 * 
 * @author 猛男波波
 */
@Controller
@RequestMapping("/system/user/profile")
public class ProfileController extends BaseController
{
    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

    private String prefix = "system/user/profile";

    @Autowired
    private ISelfUserService userService;

    @Autowired
    private PasswordService passwordService;

    /**
     * 个人信息
     */
    @GetMapping()
    public String profile(ModelMap mmap)
    {
        SelfUser user = getSelfUser();
        mmap.put("user", user);
        mmap.put("roleGroup", userService.selectSelfUserRoleGroup(user.getSelfUserId()));
        return prefix + "/profile";
    }

    @GetMapping("/checkPassword")
    @ResponseBody
    public boolean checkPassword(String password)
    {
        SelfUser user = getSelfUser();
        if (passwordService.matches(user, password))
        {
            return true;
        }
        return false;
    }

    @GetMapping("/resetPwd")
    public String resetPwd(ModelMap mmap)
    {
        SelfUser user = getSelfUser();
        mmap.put("user", userService.selectSelfUserById(user.getSelfUserId()));
        return prefix + "/resetPwd";
    }

    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwd(String oldPassword, String newPassword)
    {
        SelfUser user = getSelfUser();
        if (!passwordService.matches(user, oldPassword))
        {
            return error("修改密码失败，旧密码错误");
        }
        if (passwordService.matches(user, newPassword))
        {
            return error("新密码不能与旧密码相同");
        }
        user.setPassword(newPassword);
        user.setPwdUpdateDate(DateUtils.getNowDate());
        if (userService.resetSelfUserPwd(user) > 0)
        {
            setSelfUser(userService.selectSelfUserById(user.getSelfUserId()));
            return success();
        }
        return error("修改密码异常，请联系管理员");
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit")
    public String edit(ModelMap mmap)
    {
        SelfUser user = getSelfUser();
        mmap.put("user", userService.selectSelfUserById(user.getSelfUserId()));
        return prefix + "/edit";
    }

    /**
     * 修改头像
     */
    @GetMapping("/avatar")
    public String avatar(ModelMap mmap)
    {
        SelfUser user = getSelfUser();
        mmap.put("user", userService.selectSelfUserById(user.getSelfUserId()));
        return prefix + "/avatar";
    }

    /**
     * 修改用户
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(User user)
    {
        SelfUser currentUser = getSelfUser();
        currentUser.setUserName(user.getUserName());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhonenumber(user.getPhonenumber());
        currentUser.setSex(user.getSex());
        if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(currentUser)))
        {
            return error("修改用户'" + currentUser.getLoginName() + "'失败，手机号码已存在");
        }
        else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(currentUser)))
        {
            return error("修改用户'" + currentUser.getLoginName() + "'失败，邮箱账号已存在");
        }
        if (userService.updateSelfUserInfo(currentUser) > 0)
        {
            setSelfUser(userService.selectSelfUserById(currentUser.getSelfUserId()));
            return success();
        }
        return error();
    }

    /**
     * 保存头像
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updateAvatar")
    @ResponseBody
    public AjaxResult updateAvatar(@RequestParam("avatarfile") MultipartFile file)
    {
        SelfUser currentUser = getSelfUser();
        try
        {
            if (!file.isEmpty())
            {
                String avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file, MimeTypeUtils.IMAGE_EXTENSION);
                currentUser.setAvatar(avatar);
                if (userService.updateSelfUserInfo(currentUser) > 0)
                {
                    setSelfUser(userService.selectSelfUserById(currentUser.getSelfUserId()));
                    return success();
                }
            }
            return error();
        }
        catch (Exception e)
        {
            log.error("修改头像失败！", e);
            return error(e.getMessage());
        }
    }
}