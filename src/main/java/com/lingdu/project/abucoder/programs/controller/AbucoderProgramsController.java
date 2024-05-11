package com.lingdu.project.abucoder.programs.controller;

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
import com.lingdu.project.abucoder.programs.domain.AbucoderPrograms;
import com.lingdu.project.abucoder.programs.service.IAbucoderProgramsService;
import com.lingdu.framework.web.controller.BaseController;
import com.lingdu.framework.web.domain.AjaxResult;
import com.lingdu.common.utils.poi.ExcelUtil;
import com.lingdu.framework.web.page.TableDataInfo;

/**
 * 推荐开源项目Controller
 * 
 * @author 阿卜 QQ932696181
 * @date 2022-05-27
 */
@Controller
@RequestMapping("/abucoder/programs")
public class AbucoderProgramsController extends BaseController
{
    private String prefix = "abucoder/programs";

    @Autowired
    private IAbucoderProgramsService abucoderProgramsService;

    @RequiresPermissions("abucoder:programs:view")
    @GetMapping()
    public String programs()
    {
        return prefix + "/programs";
    }

    /**
     * 查询推荐开源项目列表
     */
    @RequiresPermissions("abucoder:programs:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AbucoderPrograms abucoderPrograms)
    {
        startPage();
        List<AbucoderPrograms> list = abucoderProgramsService.selectAbucoderProgramsList(abucoderPrograms);
        return getDataTable(list);
    }

    /**
     * 导出推荐开源项目列表
     */
    @RequiresPermissions("abucoder:programs:export")
    @Log(title = "推荐开源项目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AbucoderPrograms abucoderPrograms)
    {
        List<AbucoderPrograms> list = abucoderProgramsService.selectAbucoderProgramsList(abucoderPrograms);
        ExcelUtil<AbucoderPrograms> util = new ExcelUtil<AbucoderPrograms>(AbucoderPrograms.class);
        return util.exportExcel(list, "推荐开源项目数据");
    }

    /**
     * 新增推荐开源项目
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存推荐开源项目
     */
    @RequiresPermissions("abucoder:programs:add")
    @Log(title = "推荐开源项目", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AbucoderPrograms abucoderPrograms)
    {
        return toAjax(abucoderProgramsService.insertAbucoderPrograms(abucoderPrograms));
    }

    /**
     * 修改推荐开源项目
     */
    @RequiresPermissions("abucoder:programs:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        AbucoderPrograms abucoderPrograms = abucoderProgramsService.selectAbucoderProgramsById(id);
        mmap.put("abucoderPrograms", abucoderPrograms);
        return prefix + "/edit";
    }

    /**
     * 修改保存推荐开源项目
     */
    @RequiresPermissions("abucoder:programs:edit")
    @Log(title = "推荐开源项目", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AbucoderPrograms abucoderPrograms)
    {
        return toAjax(abucoderProgramsService.updateAbucoderPrograms(abucoderPrograms));
    }

    /**
     * 删除推荐开源项目
     */
    @RequiresPermissions("abucoder:programs:remove")
    @Log(title = "推荐开源项目", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(abucoderProgramsService.deleteAbucoderProgramsByIds(ids));
    }
}