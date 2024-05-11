package com.lingdu.project.abucoder.items.controller;

import java.util.List;
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
import com.lingdu.project.abucoder.items.domain.AbucoderItems;
import com.lingdu.project.abucoder.items.service.IAbucoderItemsService;
import com.lingdu.framework.web.controller.BaseController;
import com.lingdu.framework.web.domain.AjaxResult;
import com.lingdu.common.utils.poi.ExcelUtil;
import com.lingdu.framework.web.page.TableDataInfo;

/**
 * 推荐项目Controller
 * 
 * @author 阿卜 QQ932696181
 * @date 2022-05-27
 */
@Controller
@RequestMapping("/abucoder/items")
public class AbucoderItemsController extends BaseController
{
    private String prefix = "abucoder/items";

    @Autowired
    private IAbucoderItemsService abucoderItemsService;

    @RequiresPermissions("abucoder:items:view")
    @GetMapping()
    public String items()
    {
        return prefix + "/items";
    }

    /**
     * 查询推荐项目列表
     */
    @RequiresPermissions("abucoder:items:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AbucoderItems abucoderItems)
    {
        startPage();
        List<AbucoderItems> list = abucoderItemsService.selectAbucoderItemsList(abucoderItems);
        return getDataTable(list);
    }

    /**
     * 导出推荐项目列表
     */
    @RequiresPermissions("abucoder:items:export")
    @Log(title = "推荐项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AbucoderItems abucoderItems)
    {
        List<AbucoderItems> list = abucoderItemsService.selectAbucoderItemsList(abucoderItems);
        ExcelUtil<AbucoderItems> util = new ExcelUtil<AbucoderItems>(AbucoderItems.class);
        return util.exportExcel(list, "推荐项目数据");
    }

    /**
     * 新增推荐项目
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存推荐项目
     */
    @RequiresPermissions("abucoder:items:add")
    @Log(title = "推荐项目", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AbucoderItems abucoderItems)
    {
        return toAjax(abucoderItemsService.insertAbucoderItems(abucoderItems));
    }

    /**
     * 修改推荐项目
     */
    @RequiresPermissions("abucoder:items:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        AbucoderItems abucoderItems = abucoderItemsService.selectAbucoderItemsById(id);
        mmap.put("abucoderItems", abucoderItems);
        return prefix + "/edit";
    }

    /**
     * 修改保存推荐项目
     */
    @RequiresPermissions("abucoder:items:edit")
    @Log(title = "推荐项目", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AbucoderItems abucoderItems)
    {
        return toAjax(abucoderItemsService.updateAbucoderItems(abucoderItems));
    }

    /**
     * 删除推荐项目
     */
    @RequiresPermissions("abucoder:items:remove")
    @Log(title = "推荐项目", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(abucoderItemsService.deleteAbucoderItemsByIds(ids));
    }
}