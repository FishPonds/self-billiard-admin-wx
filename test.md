# 使用若依打通微信小程序登录授权认证接口及其他接口完整项目（UniApp版）

## 简介

一直想做一款基于若依后台管理系统的微信小程序，最近比较闲了，所以想做个简单的东西。主要为了学习，走一遍整个过程，也是为像我这种小白提供些简单的学习内容。
#### 这是一套前后端一起的脚手架，省去了好多需要写的东西，开箱即用

喜欢的话可以给个赞赏，谢谢~

若依是一套全部开源的快速开发平台，毫无保留给个人及企业免费使用。

* 感谢若依后端开发框架，本项目使用的是Ruoyi-fast单体版，可以体现下载下来
* 连接地址是 https://gitee.com/y_project/RuoYi-fast

## 使用方法
1. 下载UniApp代码
2. 导入到Hbuilder
3. 搭建自己的后端（下载Ruoyi-Fast其他版本也可以https://gitee.com/y_project/RuoYi-fast）
4. 修改小程序代码里的接口地址
5. 启动你的后端代码
6. 测试前段
7. 继续开发你自己的逻辑
## 小程序扫码体验
![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/QQ%E6%88%AA%E5%9B%BE20220603224525.jpg)
## 后端体验过两天上线下

## 使用UniApp准备前段
![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_30.png)
![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_31.png)
![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/img_2.png)
![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_32.png)
## 开始搭建
*此处忽略了你环境搭建以及若依框架下载部署
### 第一步创建数据库
![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img.png)
![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_1.png)
![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_2.png)
![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_3.png)
```mysql
CREATE TABLE `ruoyi_abucoder_uniappwx`.`abucoder_banners`  (
`id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`banner_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '轮播图标题',
`banner_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '轮播图连接',
`banner_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片',
`create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
`create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
`update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
`remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '轮播图表' ROW_FORMAT = Dynamic;
CREATE TABLE `ruoyi_abucoder_uniappwx`.`abucoder_items`  (
`id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`item_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目名称',
`item_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目介绍',
`item_content` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目内容',
`create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
`create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
`update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
`remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '推荐项目表' ROW_FORMAT = Dynamic;

CREATE TABLE `ruoyi_abucoder_uniappwx`.`abucoder_programs`  (
`id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`program_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目名称',
`program_tag` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签',
`program_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片',
`program_content` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '项目介绍',
`create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
`create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
`update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
`remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '推荐开源项目表' ROW_FORMAT = Dynamic;

CREATE TABLE `ruoyi_abucoder_uniappwx`.`abucoder_wxuser`  (
`id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
`nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信名称',
`avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
`openid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信唯一标识符',
`gender` int(1) NULL DEFAULT NULL COMMENT '性别',
`create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
`create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
`update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
`remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '微信用户表' ROW_FORMAT = Dynamic;

```
### 第二步开始生成代码
1. 我们要代码生成前需要先建立我们自业务的菜单
   ![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_5.png)
2. 导入我们需要生成的四个表
   ![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_4.png)
3. 编辑我们要生成的功能（我这里就一张表为介绍了，其他都是一样的操作了）
   ![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_6.png)
   ![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_7.png)
   ![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_8.png)
   ![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_9.png)
   ![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_10.png)
### 第三步开始导入生成的代码
1. 导入到IDEA中测试
   ![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_11.png)
   ![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_12.png)
   ![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_13.png)
   ![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_14.png)
   ![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_15.png)
   ![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_16.png)
   ![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_17.png)
2. 此时我们已经生成后的代码都导入到我们的IDEA里了，现在可以测试下了
   ![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_22.png)
   ![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_23.png)
3. 重新启动IDEA之前一定要先Rbuild Project重新编译下，要不然出现404
   ![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_21.png)
4. 启动登录后我们发现我们的功能都正常了
   ![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_24.png)
   ![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_25.png)
5. ![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_26.png)

### 第四步开始写微信接口并测试Api
1. 首先要放心我们需要做的Api路径
```java
filterChainDefinitionMap.put("/wxapi/**", "anon");//放行微信请求的所有接口地址
filterChainDefinitionMap.put("/profile/**", "anon");//放行微信请求的所有图片接口地址
```
![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_27.png)
2. 编写我们的微信登录授权接口
   ![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_28.png)
#### 登录接口代码如下
```java
/**
 * author：AbuCoder QQ:932696181
 * 微信小程序登录授权接口
 * Gitee:https://gitee.com/rahman
 */
@RestController
@RequestMapping("/wxapi")
public class WxloginController {

    @Autowired
    IAbucoderWxuserService iAbucoderWxuserService;
    /**
     * 你自己的微信小程序APPID
     */
<<<<<<< HEAD
    private final static String AppID = "";
    /**
     * 你自己的微信APP密钥
     */
    private final static String AppSecret = "";
=======
    private final static String AppID = "你自己的appid";
    /**
     * 你自己的微信APP密钥
     */
    private final static String AppSecret = "你自己的密钥";
>>>>>>> f34851bf2ab2f8b25f1d381c3949a3b7246d68d9

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
        System.out.println("输出请求到的结果");
        System.out.println(res);
        JSONObject jsonObject = JSON.parseObject(res);
        String session_key = (String) jsonObject.get("session_key");
        String openid = (String) jsonObject.get("openid");
        if (StrUtil.isEmpty(openid)) {
            return AjaxResult.error("未获取到openid");
        }
        String token = UUID.randomUUID().toString();
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("session_key", session_key);
        data.put("openid", openid);
        data.put("state",true);
        data.put("nickName",object.get("nickName"));
        data.put("avatarUrl",object.get("avatarUrl"));
        /**先通过openid来查询是否存在*/
        AbucoderWxuser abucoderWxuser = iAbucoderWxuserService.selectAbucoderWxuserOpenID(openid);
        if (abucoderWxuser == null){
            /**如果不存在就插入到我们的数据库里*/
            AbucoderWxuser  wxuser = new AbucoderWxuser();
            wxuser.setNickname(String.valueOf(object.get("nickName")));
            wxuser.setGender((Integer) object.get("gender"));
            wxuser.setAvatar(String.valueOf(object.get("avatarUrl")));
            wxuser.setOpenid(openid);
            wxuser.setCreateTime(DateUtils.getNowDate());
            wxuser.setCreateBy("Abu");
            iAbucoderWxuserService.insertAbucoderWxuser(wxuser);
        }else {
            /**如果存在就更新数据库里原有的数据*/
            abucoderWxuser.setNickname(String.valueOf(object.get("nickName")));
            abucoderWxuser.setGender((Integer) object.get("gender"));
            abucoderWxuser.setAvatar(String.valueOf(object.get("avatarUrl")));
            abucoderWxuser.setUpdateTime(DateUtils.getNowDate());
            abucoderWxuser.setUpdateBy("Abu");
            iAbucoderWxuserService.updateAbucoderWxuser(abucoderWxuser);
        }
        /**返回结果集到前段*/
        return AjaxResult.success(data);
    }
}

```
#### 前段登录接口代码如下
```javascript
login(){
    var that = this;
    uni.getUserProfile({
        desc: '获取您的微信信息以授权小程序',
        lang: 'zh_CN',
        success: UserProfileRes => {  
            uni.showLoading({title:"登录中....",mask:true})
            console.log(UserProfileRes)
            uni.login({
                provider: 'weixin',
                success: function(loginRes) {
                    let form = {};
                    form.code = loginRes.code; //用户code  注:用户的code每次登录都是随机的，所以不需要进行存储
                    form.avatarUrl = UserProfileRes.userInfo.avatarUrl; //用户头像
                    form.nickName = UserProfileRes.userInfo.nickName; //用户微信名
                    form.gender = UserProfileRes.userInfo.gender;//性别
                    that.request('wxlogin', form, 'POST').then(wxloginres=>{
                        console.log("wxloginres: ", wxloginres)
                        if(wxloginres.data.state){
                            setTimeout(function () {
                                uni.hideLoading();
                            }, 2000);
                            that.userinfo = wxloginres.data 
                            setToken(wxloginres.data.openid)
                            setUserInfo(wxloginres.data);  //模拟存储用户信息
                        }else{
                            uni.showToast({
                                icon:"none",
                                title: "登录失败，请稍后试试！",
                                duration:2000
                            })
                        }
                        
                    })
                },
                fail(err) {
                    console.log(err)
                }
            });
        },
        fail:err=>{
            console.log(err)
        }
    })
}
```
3. 在后端页面查看数据
   ![](https://gitee.com/rahman/abu-coder-ruoyi-uniapp-wx/raw/master/doc/img/img_29.png)
4. 其他接口同理
* 1.轮播图接口代码及演示
* java代码
```java
/**
 * author：AbuCoder QQ:932696181
 * 微信小程序获取轮播图接口
 * Gitee:https://gitee.com/rahman
 */
@RestController
@RequestMapping("/wxapi")
public class WxBannerController {

    @Autowired
    IAbucoderBannersService iAbucoderBannersService;
    @GetMapping("/loadBaneer")
    public AjaxResult loadBaneer(AbucoderBanners abucoderBanners){
        List<AbucoderBanners> bannerslist = iAbucoderBannersService.selectAbucoderBannersList(abucoderBanners);
        return AjaxResult.success(bannerslist);
    }
}
```
* 前段代码
```javascript

getBannerlist(){
    this.request('loadBaneer', 'GET').then(res=>{
        console.log("res:",res)
        if(res){
            this.bannerList = res.data
        }
    })
},
```
* 2.通知公告接口代码及演示
* java接口代码
```java
/**
 * author：AbuCoder QQ:932696181
 * 微信小程序获取通知公告接口
 * Gitee:https://gitee.com/rahman
 */
@RestController
@RequestMapping("/wxapi")
public class WxNoticeController {

    @Autowired
    INoticeService iNoticeService;
    @GetMapping("/loadNotice")
    public AjaxResult loadNotice(){
        List<Notice> notices = iNoticeService.selectAllNotice();
        return AjaxResult.success(notices);
    }
}
```
* 前段代码
```javascript
getNoticelist(){
    this.request('loadNotice', 'GET').then(res=>{
        console.log("res:",res)
        if(res){
            this.messageData = res.data
        }
    })
},
```
* 3.推荐小程序接口代码及演示
* java接口代码
```java
/**
 * author：AbuCoder QQ:932696181
 * 微信小程序获取推荐小程序接口
 * Gitee:https://gitee.com/rahman
 */
@RestController
@RequestMapping("/wxapi")
public class WxItemsController {

    @Autowired
    IAbucoderItemsService iAbucoderItemsService;
    @GetMapping("/loadItems")
    public AjaxResult loadItems(AbucoderItems abucoderItems){
        List<AbucoderItems> itemslist = iAbucoderItemsService.selectAbucoderItemsList(abucoderItems);
        return AjaxResult.success(itemslist);
    }
}

```
* 前段代码
```javascript
getItemslist(){
    this.request('loadItems', 'GET').then(res=>{
        console.log("res:",res)
        if(res){
            this.curriculum = res.data
        }
    })
},
```
* 4.推荐开源项目接口代码及演示
* java接口代码
```java
/**
 * author：AbuCoder QQ:932696181
 * 微信小程序获取推荐项目接口
 * Gitee:https://gitee.com/rahman
 */
@RestController
@RequestMapping("/wxapi")
public class WxProgramController {

    @Autowired
    IAbucoderProgramsService iAbucoderProgramsService;
    @GetMapping("/loadProgramms")
    public AjaxResult loadProgramms(AbucoderPrograms abucoderPrograms){
        List<AbucoderPrograms> programmslist = iAbucoderProgramsService.selectAbucoderProgramsList(abucoderPrograms);
        return AjaxResult.success(programmslist);
    }
}
```
* 前段代码
```javascript
getProgrammslist(){
    this.request('loadProgramms', 'GET').then(res=>{
        console.log("res:",res)
        if(res){
            this.projectList = res.data
        }
    })
}
```