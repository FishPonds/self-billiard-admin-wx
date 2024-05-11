package com.lingdu.project.billiard.controller;

import com.lingdu.framework.web.controller.BaseController;
import com.lingdu.project.billiard.service.ISelfStoreInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 文件创建人：波波
 * 日期：2024/5/10
 * 时间：10:39
 * 类名：SelfStoreController 门店管理
 * @author 猛男波波
 */
@Controller
@RequestMapping("/billiard/store")
public class SelfStoreController extends BaseController {


    @Autowired
    private ISelfStoreInformationService selfStoreInformationService;

    /**
     * 门店列表
     *
     * @return
     */
//    @RequestMapping("/storeList")
//    @ResponseBody
//    public AjaxResult storeList() {
//        //获取当前登录用户
//        SelfUser selfUser = getSelfUser();
//        //根据用户id查询商户信息
//        SelfMerchants selfMerchants = selfMerchantsService.selectMerchantsListBySelfUserId(selfUser.getSelfUserId());
//        if (selfMerchants == null) {
//            return error("未查询到商户信息");
//        }
//        SelfStoreInformation selfStoreInfo = new SelfStoreInformation();
//        selfStoreInfo.setMerchantId(selfMerchants.getCustomMerchantId());
//        //存储商户信息
//        setSelfMerchants(selfMerchants);
//        return success(selfStoreInformationService.selectSelfStoreInformationList(selfStoreInfo));
//    }

}