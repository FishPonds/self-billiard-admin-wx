package com.lingdu.project.billiard.controller;

import com.lingdu.common.utils.poi.ExcelUtil;
import com.lingdu.common.utils.uuid.IdUtils;
import com.lingdu.framework.aspectj.lang.annotation.Log;
import com.lingdu.framework.aspectj.lang.enums.BusinessType;
import com.lingdu.framework.interceptor.annotation.RepeatSubmit;
import com.lingdu.framework.web.controller.BaseController;
import com.lingdu.framework.web.domain.AjaxResult;
import com.lingdu.framework.web.page.TableDataInfo;
import com.lingdu.project.billiard.domain.SelfTableTop;
import com.lingdu.project.billiard.domain.SelfTableTopAlbum;
import com.lingdu.project.billiard.service.ISelfTableTopAlbumService;
import com.lingdu.project.billiard.service.ISelfTableTopService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 桌台信息Controller
 *
 * @author 猛男波波
 * @date 2024-05-18
 */
@Controller
@RequestMapping("/billiard/table")
public class SelfTableTopController extends BaseController {
    @Autowired
    private ISelfTableTopService selfTableTopService;

    @Autowired
    private ISelfTableTopAlbumService selfTableTopAlbumService;

    private String prefix = "billiard/table";

    /**
     * 跳转到桌台信息列表页面
     */
    @RequiresPermissions("billiard:table:view")
    @GetMapping("/table")
    public String storeIndex(ModelMap modelMap) {
        return prefix + "/table";
    }

    /**
     * 查询桌台信息列表
     */
    @RequiresPermissions("billiard:table:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HttpSession session, SelfTableTop selfTableTop) {
        selfTableTop.setSelfStoreId((Long) session.getAttribute("storeId"));
        startPage();
        List<SelfTableTop> list = selfTableTopService.selectSelfTableTopList(selfTableTop);
        return getDataTable(list);
    }

    /**
     * 获取桌台编码
     */
    @GetMapping("/getTableCode")
    @ResponseBody
    public AjaxResult getTableCode(HttpSession session) {
        String tableCode = IdUtils.generateRandomCode();
        SelfTableTop selfTableTop = new SelfTableTop();
        //生成桌台编码
        selfTableTop.setTableCode(tableCode);
        selfTableTop.setSelfStoreId((Long) session.getAttribute("storeId"));
        //查询桌台编码是否存在
        int count = selfTableTopService.selectCountSelfTableTopByTableCode(selfTableTop);
        if (count > 0) {
            tableCode = IdUtils.generateRandomCode();
        }
        return AjaxResult.success(tableCode);
    }

    /**
     * 导出桌台信息列表
     */
    @RequiresPermissions("billiard:table:export")
    @Log(title = "桌台信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HttpSession session, SelfTableTop selfTableTop) {
        selfTableTop.setSelfStoreId((Long) session.getAttribute("storeId"));
        List<SelfTableTop> list = selfTableTopService.selectSelfTableTopList(selfTableTop);
        ExcelUtil<SelfTableTop> util = new ExcelUtil<SelfTableTop>(SelfTableTop.class);
       return util.exportExcel(list, "桌台信息数据");
    }

    /**
     * 获取桌台信息详细信息
     */
    @RequiresPermissions("billiard:table:info")
    @GetMapping(value = "/{tableId}")
    @ResponseBody
    public AjaxResult getInfo(@PathVariable("tableId") Long tableId) {
        return success(selfTableTopService.selectSelfTableTopByTableId(tableId));
    }

    /**
     * 跳转到新增桌台信息页面
     */
    @RequiresPermissions("billiard:table:add")
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        return prefix + "/add";
    }

    /**
     * 新增桌台信息
     */
    @RequiresPermissions("billiard:table:add")
    @Log(title = "桌台信息", businessType = BusinessType.INSERT)
    @PostMapping("/addTable")
    @ResponseBody
    @RepeatSubmit()
    public AjaxResult add(@Validated SelfTableTop selfTableTop,HttpSession session) {
        //判断桌台编码是否存在
        if (selfTableTopService.selectCountSelfTableTopByTableCode(selfTableTop) > 0) {
            return AjaxResult.error("新增桌台'" + selfTableTop.getTableCode() + "'失败，桌台编码已存在，请刷新编码后重试！");
        }
        selfTableTop.setSelfStoreId((Long) session.getAttribute("storeId"));
        return toAjax(selfTableTopService.insertSelfTableTop(selfTableTop));
    }

    /**
     * 跳转到修改桌台信息页面
     */
    @RequiresPermissions("billiard:table:edit")
    @GetMapping("/edit/{tableId}")
    public String edit(@PathVariable("tableId") Long tableId, ModelMap mmap) {
        mmap.put("selfTableTop", selfTableTopService.selectSelfTableTopByTableId(tableId));
        return prefix + "/edit";
    }

    /**
     * 修改桌台信息
     */
    @RequiresPermissions("billiard:table:edit")
    @Log(title = "桌台信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    @RepeatSubmit
    public AjaxResult edit(@Validated SelfTableTop selfTableTop,HttpSession session) {
        selfTableTop.setSelfStoreId((Long) session.getAttribute("storeId"));
        return toAjax(selfTableTopService.updateSelfTableTop(selfTableTop));
    }

    /**
     * 删除桌台信息
     */
    @RequiresPermissions("billiard:table:del")
    @Log(title = "桌台信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    @RepeatSubmit
    public AjaxResult remove(String ids) {
        return toAjax(selfTableTopService.deleteSelfTableTopByTableIds(ids));
    }

    /**
     * 查询桌台相册列表
     */
    @RequiresPermissions("billiard:table:list")
    @GetMapping("/album/list/{tableId}")
    @ResponseBody
    public AjaxResult albumList(@PathVariable("tableId") Long tableId) {
        SelfTableTopAlbum selfTableTopAlbum = new SelfTableTopAlbum();
        selfTableTopAlbum.setTableId(tableId);
        List<SelfTableTopAlbum> list = selfTableTopAlbumService.selectSelfTableTopAlbumList(selfTableTopAlbum);
        List<Map<String,String>> mapList = new ArrayList<>();
        //返回一个listmap，里面包含了拼接的图片路径和图片id
        for (SelfTableTopAlbum album : list) {
            Map<String,String> map = new HashMap<>();
            map.put("id",album.getAlbumId().toString());
            map.put("url",album.getImageUrl());
            mapList.add(map);
        }
        return AjaxResult.success(mapList);
    }

    /**
     * 删除桌台相册
     */
    @RequiresPermissions("billiard:table:del")
    @Log(title = "桌台相册", businessType = BusinessType.DELETE)
    @PostMapping("/album/remove")
    @ResponseBody
    @RepeatSubmit
    public AjaxResult removeAlbum(String ids) {
        return toAjax(selfTableTopAlbumService.deleteSelfTableTopAlbumByAlbumIds(ids));
    }
}