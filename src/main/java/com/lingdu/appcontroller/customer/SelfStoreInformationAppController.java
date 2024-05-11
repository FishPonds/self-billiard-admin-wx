package com.lingdu.appcontroller.customer;

import com.lingdu.framework.web.controller.BaseController;
import com.lingdu.framework.web.domain.AjaxResult;
import com.lingdu.framework.web.page.TableDataInfo;
import com.lingdu.project.billiard.domain.SelfStoreInformation;
import com.lingdu.project.billiard.service.ISelfStoreInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 自助台球系统门店信息Controller
 * 
 * @author 猛男波波
 * @date 2024-05-03
 */
@RestController
@RequestMapping("/app/customer/store")
public class SelfStoreInformationAppController extends BaseController
{
    @Autowired
    private ISelfStoreInformationService selfStoreInformationService;

    /**
     * 查询自助台球系统门店信息列表
     */
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody SelfStoreInformation selfStoreInformation)
    {
        List<SelfStoreInformation> list = selfStoreInformationService.selectSelfStoreInformationList(selfStoreInformation);
        return getDataTable(list);
    }

    /**
     * 获取自助台球系统门店信息详细信息
     */
    @GetMapping(value = "/{storeId}")
    public AjaxResult getInfo(@PathVariable("storeId") Long storeId)
    {
        return success(selfStoreInformationService.selectSelfStoreInformationByStoreId(storeId));
    }

}