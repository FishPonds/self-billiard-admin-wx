package com.lingdu.appcontroller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lingdu.common.utils.AESForWeixinGetPhoneNumber;
import com.lingdu.common.utils.DateUtils;
import com.lingdu.common.utils.file.FileUploadUtils;
import com.lingdu.common.utils.file.FileUtils;
import com.lingdu.common.utils.http.HttpUtils;
import com.lingdu.framework.config.RuoYiConfig;
import com.lingdu.framework.config.ServerConfig;
import com.lingdu.framework.web.controller.BaseController;
import com.lingdu.framework.web.domain.AjaxResult;
import com.lingdu.project.billiard.domain.WechatUser;
import com.lingdu.project.billiard.service.IWechatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件创建人：波波
 * 日期：2024/5/1
 * 时间：15:24
 * 类名：WxloginController
 */

@RestController
@RequestMapping("/app/wxapi")
public class WxloginController extends BaseController {

    @Autowired
    IWechatUserService wechatUserService;

    @Autowired
    private ServerConfig serverConfig;

    /**
     * 你自己的微信小程序APPID
     */
    private final static String AppID = "wx00882cf95191a0c7";
    /**
     * 你自己的微信APP密钥
     */
    private final static String AppSecret = "a95b12b1e1ed95f36d4071181d328a39";

    /**
     * 登录时获取的 code（微信官方提供的临时凭证）
     * @param object
     * @return
     */
    @PostMapping("/wxlogin")
    public AjaxResult wxLogin(@RequestBody JSONObject object){
        //微信官方提供的微信小程序登录授权时使用的URL地址
        String url  = "https://api.weixin.qq.com/sns/jscode2session";
        System.out.println(object);

        /**
         * 拼接需要的参数
         * appid = AppID 你自己的微信小程序APPID
         * js_code = AppSecret 你自己的微信APP密钥
         * grant_type=authorization_code = code 微信官方提供的临时凭证
         */
        String params = StrUtil.format("appid={}&secret={}&js_code={}&grant_type=authorization_code", AppID, AppSecret, object.get("code"));
        //开始发起网络请求,若依管理系统自带网络请求工具，直接使用即可
        String res = HttpUtils.sendGet(url,params);
        JSONObject jsonObject = JSON.parseObject(res);
        String openid = (String) jsonObject.get("openid");
        if (StrUtil.isEmpty(openid)) {
            return AjaxResult.error("未获取到openid");
        }
        String sessionKey = (String) jsonObject.get("session_key");
        if (StrUtil.isEmpty(sessionKey)) {
            return AjaxResult.error("未获取到sessionKey");
        }
        /**先通过openid来查询是否存在*/
        WechatUser wechatUser = wechatUserService.selectWechatUserOpenID(openid);
        if (wechatUser == null){
            /**如果不存在就插入到我们的数据库里*/
            WechatUser wxuser= new WechatUser();
            wxuser.setOpenId(openid);
            wxuser.setSessionKey(sessionKey);
            wxuser.setCreateTime(DateUtils.getNowDate());
            wechatUserService.insertWechatUser(wxuser);
            return AjaxResult.success(wxuser);
        }else {
            /**返回结果集到前段*/
            return AjaxResult.success(wechatUser);
        }
    }

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        System.out.println(file);
        try
        {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url", url);
            ajax.put("fileName", fileName);
            ajax.put("newFileName", FileUtils.getName(fileName));
            ajax.put("originalFilename", file.getOriginalFilename());
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 保存昵称与头像信息到用户信息里
     * @param object
     * @return
     */
    @PostMapping("/saveUserInfo")
    public AjaxResult saveUserInfo(@RequestBody JSONObject object){
        System.out.println(object);
        WechatUser wechatUser = wechatUserService.selectWechatUserOpenID(String.valueOf(object.get("openid")));
        if (StringUtils.hasLength(String.valueOf(object.get("nickName")))){
            wechatUser.setNickName(String.valueOf(object.get("nickName")));
            wechatUser.setCreateBy(String.valueOf(object.get("nickName")));
        }
        if (StringUtils.hasLength(String.valueOf(object.get("avatarUrl")))){
            wechatUser.setAvatar(String.valueOf(object.get("avatarUrl")));
        }
        wechatUser.setUpdateTime(DateUtils.getNowDate());
        wechatUserService.updateWechatUser(wechatUser);
        //返回前段需要的数据
        return AjaxResult.success(wechatUser);
    }


    /**
     * 微信登录获取手机号并绑定手机号
     *
     * @param jsonObject
     * @return
     */
    @PostMapping("/getPhoneNumber")
    public AjaxResult getPhoneNumber(@RequestBody JSONObject jsonObject){
        String userId = jsonObject.getString("userId");
        String encryptedData = jsonObject.getString("encryptedData");
        String iv = jsonObject.getString("iv");
        String sessionKey = wechatUserService.selectWechatUserByUserId(Long.valueOf(userId)).getSessionKey();
        String result = AESForWeixinGetPhoneNumber.wxDecrypt(encryptedData, sessionKey, iv);
        JSONObject resultJson = JSON.parseObject(result);
        WechatUser wechatUser = new WechatUser();
        wechatUser.setUserId(Long.valueOf(userId));
        wechatUser.setPhoneNumber(resultJson.getString("phoneNumber"));
        wechatUserService.updateWechatUser(wechatUser);
        return AjaxResult.success(resultJson.getString("phoneNumber"));
    }

}