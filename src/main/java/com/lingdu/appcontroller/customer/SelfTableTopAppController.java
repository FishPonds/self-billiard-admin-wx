package com.lingdu.appcontroller.customer;

import com.alibaba.fastjson.JSONObject;
import com.lingdu.framework.web.controller.BaseController;
import com.lingdu.framework.web.domain.AjaxResult;
import com.lingdu.project.billiard.service.ISelfStoreInformationService;
import com.lingdu.project.billiard.service.ISelfTableTopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 文件创建人：波波
 * 日期：2024/5/21
 * 时间：21:19
 * 类名：SelfTableTopAppController
 *
 * @author 猛男波波
 */
@RestController
@RequestMapping("/app/customer/tabletop")
public class SelfTableTopAppController extends BaseController {

    @Autowired
    private ISelfTableTopService selfTableTopService;

    @Autowired
    private ISelfStoreInformationService selfStoreInformationService;

    /**
     * 查询桌球预约信息
     *
     * @param tableId    桌台id
     * @param tableKey   桌台编码
     * @param changeTime 当前时间
     */
    @PostMapping("/checkRoomTimeSlot")
    public AjaxResult checkRoomTimeSlot(@RequestBody JSONObject json) {
        String tableId = json.getString("tableId");
        String tableCode = json.getString("tableCode");
        String changeTime = json.getString("changeTime");
        return success(selfTableTopService.checkRoomTimeSlot(Long.parseLong(tableId), tableCode, changeTime));
    }

}