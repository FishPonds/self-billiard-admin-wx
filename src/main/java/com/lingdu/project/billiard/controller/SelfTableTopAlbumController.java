package com.lingdu.project.billiard.controller;

import java.util.List;

import com.lingdu.project.billiard.domain.SelfTableTopAlbum;
import com.lingdu.project.billiard.service.ISelfTableTopAlbumService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.lingdu.framework.aspectj.lang.annotation.Log;
import com.lingdu.framework.aspectj.lang.enums.BusinessType;
import com.lingdu.framework.web.controller.BaseController;
import com.lingdu.framework.web.domain.AjaxResult;
import com.lingdu.common.utils.poi.ExcelUtil;
import com.lingdu.framework.web.page.TableDataInfo;

/**
 * 桌台相册Controller
 * 
 * @author 猛男波波
 * @date 2024-05-20
 */
@Controller
@RequestMapping("/billiard/tableAlbum")
public class SelfTableTopAlbumController extends BaseController
{
    private String prefix = "billiard/tableAlbum";

    @Autowired
    private ISelfTableTopAlbumService selfTableTopAlbumService;

    @RequiresPermissions("billiard:tableAlbum:view")
    @GetMapping()
    public String tableAlbum()
    {
        return prefix + "/tableAlbum";
    }

    /**
     * 查询桌台相册列表
     */
    @RequiresPermissions("billiard:tableAlbum:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SelfTableTopAlbum selfTableTopAlbum)
    {
        startPage();
        List<SelfTableTopAlbum> list = selfTableTopAlbumService.selectSelfTableTopAlbumList(selfTableTopAlbum);
        return getDataTable(list);
    }

    /**
     * 导出桌台相册列表
     */
    @RequiresPermissions("billiard:tableAlbum:export")
    @Log(title = "桌台相册", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SelfTableTopAlbum selfTableTopAlbum)
    {
        List<SelfTableTopAlbum> list = selfTableTopAlbumService.selectSelfTableTopAlbumList(selfTableTopAlbum);
        ExcelUtil<SelfTableTopAlbum> util = new ExcelUtil<SelfTableTopAlbum>(SelfTableTopAlbum.class);
        return util.exportExcel(list, "桌台相册数据");
    }

    /**
     * 新增桌台相册
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存桌台相册
     */
    @RequiresPermissions("billiard:tableAlbum:add")
    @Log(title = "桌台相册", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SelfTableTopAlbum selfTableTopAlbum)
    {
        return toAjax(selfTableTopAlbumService.insertSelfTableTopAlbum(selfTableTopAlbum));
    }

    /**
     * 修改桌台相册
     */
    @RequiresPermissions("billiard:tableAlbum:edit")
    @GetMapping("/edit/{albumId}")
    public String edit(@PathVariable("albumId") Long albumId, ModelMap mmap)
    {
        SelfTableTopAlbum selfTableTopAlbum = selfTableTopAlbumService.selectSelfTableTopAlbumByAlbumId(albumId);
        mmap.put("selfTableTopAlbum", selfTableTopAlbum);
        return prefix + "/edit";
    }

    /**
     * 修改保存桌台相册
     */
    @RequiresPermissions("billiard:tableAlbum:edit")
    @Log(title = "桌台相册", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SelfTableTopAlbum selfTableTopAlbum)
    {
        return toAjax(selfTableTopAlbumService.updateSelfTableTopAlbum(selfTableTopAlbum));
    }

    /**
     * 删除桌台相册
     */
    @RequiresPermissions("billiard:tableAlbum:remove")
    @Log(title = "桌台相册", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(selfTableTopAlbumService.deleteSelfTableTopAlbumByAlbumIds(ids));
    }
}