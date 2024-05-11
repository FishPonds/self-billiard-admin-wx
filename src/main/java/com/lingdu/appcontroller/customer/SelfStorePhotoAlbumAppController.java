package com.lingdu.appcontroller.customer;

import com.lingdu.framework.web.controller.BaseController;
import com.lingdu.project.billiard.service.ISelfStorePhotoAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件创建人：波波
 * 日期：2024/5/7
 * 时间：11:35
 * 类名：SelfStorePhotoAlbumAppController
 * @author 猛男波波
 */

@RestController
@RequestMapping("/app/customer/photoAlbum")
public class SelfStorePhotoAlbumAppController extends BaseController {

    @Autowired
    private ISelfStorePhotoAlbumService selfStorePhotoAlbumService;

}