package com.lingdu.appcontroller.customer;

import com.lingdu.framework.web.controller.BaseController;
import com.lingdu.project.billiard.service.ISelfStoreInformationService;
import com.lingdu.project.billiard.service.ISelfTableTopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件创建人：波波
 * 日期：2024/5/21
 * 时间：21:19
 * 类名：SelfTableTopAppController
 * @author 猛男波波
 */
@RestController
@RequestMapping("/app/customer/tabletop")
public class SelfTableTopAppController extends BaseController {

    @Autowired
    private ISelfTableTopService selfTableTopService;

    @Autowired
    private ISelfStoreInformationService selfStoreInformationService;


}