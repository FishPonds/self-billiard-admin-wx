package com.lingdu.project.system.user.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.lingdu.project.billiard.domain.SelfRole;
import com.lingdu.project.billiard.domain.SelfUser;
import com.lingdu.project.billiard.service.ISelfRoleService;
import com.lingdu.project.billiard.service.ISelfUserService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.lingdu.common.constant.UserConstants;
import com.lingdu.common.utils.StringUtils;
import com.lingdu.common.utils.poi.ExcelUtil;
import com.lingdu.common.utils.security.AuthorizationUtils;
import com.lingdu.common.utils.security.ShiroUtils;
import com.lingdu.common.utils.text.Convert;
import com.lingdu.framework.aspectj.lang.annotation.Log;
import com.lingdu.framework.aspectj.lang.enums.BusinessType;
import com.lingdu.framework.web.controller.BaseController;
import com.lingdu.framework.web.domain.AjaxResult;
import com.lingdu.framework.web.page.TableDataInfo;
import com.lingdu.project.system.post.service.IPostService;
import com.lingdu.project.system.role.domain.Role;
import com.lingdu.project.system.role.service.IRoleService;
import com.lingdu.project.system.user.domain.User;
import com.lingdu.project.system.user.service.IUserService;

/**
 * 用户信息
 * 
 * @author 猛男波波
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController
{
//    private String prefix = "system/user";
//
//    @Autowired
//    private ISelfUserService userService;
//
//    @Autowired
//    private ISelfRoleService roleService;
//
//
//    @RequiresPermissions("system:user:view")
//    @GetMapping()
//    public String user()
//    {
//        return prefix + "/user";
//    }
//
//    @RequiresPermissions("system:user:list")
//    @PostMapping("/list")
//    @ResponseBody
//    public TableDataInfo list(SelfUser user)
//    {
//        startPage();
//        List<SelfUser> list = userService.selectSelfUserList(user);
//        return getDataTable(list);
//    }
//
//    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
//    @RequiresPermissions("system:user:export")
//    @PostMapping("/export")
//    @ResponseBody
//    public AjaxResult export(SelfUser user)
//    {
//        List<SelfUser> list = userService.selectUserList(user);
//        ExcelUtil<SelfUser> util = new ExcelUtil<SelfUser>(SelfUser.class);
//        return util.exportExcel(list, "用户数据");
//    }
//
//    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
//    @RequiresPermissions("system:user:import")
//    @PostMapping("/importData")
//    @ResponseBody
//    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
//    {
//        ExcelUtil<SelfUser> util = new ExcelUtil<SelfUser>(User.class);
//        List<SelfUser> userList = util.importExcel(file.getInputStream());
//        String message = userService.importUser(userList, updateSupport);
//        return AjaxResult.success(message);
//    }
//
//    @RequiresPermissions("system:user:view")
//    @GetMapping("/importTemplate")
//    @ResponseBody
//    public AjaxResult importTemplate()
//    {
//        ExcelUtil<SelfUser> util = new ExcelUtil<SelfUser>(User.class);
//        return util.importTemplateExcel("用户数据");
//    }
//
//    /**
//     * 新增用户
//     */
//    @GetMapping("/add")
//    public String add(ModelMap mmap)
//    {
//        mmap.put("roles", roleService.selectRoleAll().stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
//        return prefix + "/add";
//    }
//
//    /**
//     * 新增保存用户
//     */
//    @RequiresPermissions("system:user:add")
//    @Log(title = "用户管理", businessType = BusinessType.INSERT)
//    @PostMapping("/add")
//    @ResponseBody
//    public AjaxResult addSave(@Validated SelfUser user)
//    {
//        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(user.getLoginName())))
//        {
//            return error("新增用户'" + user.getLoginName() + "'失败，登录账号已存在");
//        }
//        else if (StringUtils.isNotEmpty(user.getPhonenumber())
//                && UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
//        {
//            return error("新增用户'" + user.getLoginName() + "'失败，手机号码已存在");
//        }
//        else if (StringUtils.isNotEmpty(user.getEmail())
//                && UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
//        {
//            return error("新增用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
//        }
//        return toAjax(userService.insertUser(user));
//    }
//
//    /**
//     * 修改用户
//     */
//    @RequiresPermissions("system:user:edit")
//    @GetMapping("/edit/{userId}")
//    public String edit(@PathVariable("userId") Long userId, ModelMap mmap)
//    {
//        userService.checkUserDataScope(userId);
//        List<SelfRole> roles = roleService.selectRolesByUserId(userId);
//        mmap.put("user", userService.selectUserById(userId));
//        mmap.put("roles", User.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
//        return prefix + "/edit";
//    }
//
//    /**
//     * 修改保存用户
//     */
//    @RequiresPermissions("system:user:edit")
//    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
//    @PostMapping("/edit")
//    @ResponseBody
//    public AjaxResult editSave(@Validated SelfUser user)
//    {
//        userService.checkUserAllowed(user);
//        userService.checkUserDataScope(user.getSelfUserId());
//        if (StringUtils.isNotEmpty(user.getPhonenumber())
//                && UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
//        {
//            return error("修改用户'" + user.getLoginName() + "'失败，手机号码已存在");
//        }
//        else if (StringUtils.isNotEmpty(user.getEmail())
//                && UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
//        {
//            return error("修改用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
//        }
//        AuthorizationUtils.clearAllCachedAuthorizationInfo();
//        return toAjax(userService.updateUser(user));
//    }
//
//    @RequiresPermissions("system:user:resetPwd")
//    @GetMapping("/resetPwd/{userId}")
//    public String resetPwd(@PathVariable("userId") Long userId, ModelMap mmap)
//    {
//        mmap.put("user", userService.selectUserById(userId));
//        return prefix + "/resetPwd";
//    }
//
//    @RequiresPermissions("system:user:resetPwd")
//    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
//    @PostMapping("/resetPwd")
//    @ResponseBody
//    public AjaxResult resetPwdSave(SelfUser user)
//    {
//        userService.checkUserAllowed(user);
//        userService.checkUserDataScope(user.getSelfUserId());
//        if (userService.resetUserPwd(user) > 0)
//        {
//            if (ShiroUtils.getUserId().longValue() == user.getUserId().longValue())
//            {
//                setSelfUser(userService.selectUserById(user.getUserId()));
//            }
//            return success();
//        }
//        return error();
//    }
//
//    /**
//     * 进入授权角色页
//     */
//    @RequiresPermissions("system:user:edit")
//    @GetMapping("/authRole/{userId}")
//    public String authRole(@PathVariable("userId") Long userId, ModelMap mmap)
//    {
//        User user = userService.selectUserById(userId);
//        // 获取用户所属的角色列表
//        List<Role> roles = roleService.selectRolesByUserId(userId);
//        mmap.put("user", user);
//        mmap.put("roles", User.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
//        return prefix + "/authRole";
//    }
//
//    /**
//     * 用户授权角色
//     */
//    @RequiresPermissions("system:user:edit")
//    @Log(title = "用户管理", businessType = BusinessType.GRANT)
//    @PostMapping("/authRole/insertAuthRole")
//    @ResponseBody
//    public AjaxResult insertAuthRole(Long userId, Long[] roleIds)
//    {
//        userService.checkUserDataScope(userId);
//        userService.insertUserAuth(userId, roleIds);
//        AuthorizationUtils.clearAllCachedAuthorizationInfo();
//        return success();
//    }
//
//    @RequiresPermissions("system:user:remove")
//    @Log(title = "用户管理", businessType = BusinessType.DELETE)
//    @PostMapping("/remove")
//    @ResponseBody
//    public AjaxResult remove(String ids)
//    {
//        if (ArrayUtils.contains(Convert.toLongArray(ids), getUserId()))
//        {
//            return error("当前用户不能删除");
//        }
//        return toAjax(userService.deleteUserByIds(ids));
//    }
//
//    /**
//     * 校验用户名
//     */
//    @PostMapping("/checkLoginNameUnique")
//    @ResponseBody
//    public String checkLoginNameUnique(User user)
//    {
//        return userService.checkLoginNameUnique(user.getLoginName());
//    }
//
//    /**
//     * 校验手机号码
//     */
//    @PostMapping("/checkPhoneUnique")
//    @ResponseBody
//    public String checkPhoneUnique(User user)
//    {
//        return userService.checkPhoneUnique(user);
//    }
//
//    /**
//     * 校验email邮箱
//     */
//    @PostMapping("/checkEmailUnique")
//    @ResponseBody
//    public String checkEmailUnique(User user)
//    {
//        return userService.checkEmailUnique(user);
//    }
//
//    /**
//     * 用户状态修改
//     */
//    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
//    @RequiresPermissions("system:user:edit")
//    @PostMapping("/changeStatus")
//    @ResponseBody
//    public AjaxResult changeStatus(User user)
//    {
//        userService.checkUserAllowed(user);
//        userService.checkUserDataScope(user.getUserId());
//        return toAjax(userService.changeStatus(user));
//    }
}