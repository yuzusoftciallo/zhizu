package com.ruoyi.project.cms.type.controller;

import com.ruoyi.common.constant.TypeConstants;
import com.ruoyi.common.exception.cms.CMSTypeNotDeleteException;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.cms.type.domain.CmsType;
import com.ruoyi.project.cms.type.service.ICMSTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cms/type")
public class CMSTypeController extends BaseController {

    @Autowired
    private ICMSTypeService cmsTypeService;

    private String prefix = "cms/type";

    @RequiresPermissions("cms:type:view")
    @GetMapping()
    public String type()
    {
        return prefix + "/type";
    }

    @RequiresPermissions("cms:type:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CmsType cmsType){
        startPage();
        List<CmsType> cmsTypes = cmsTypeService.selectCMSTypeList(cmsType);
        return getDataTable(cmsTypes);
    }

    /**
     * 新增公告类型
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    @RequiresPermissions("cms:type:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(CmsType cmsType)
    {
        if (TypeConstants.TYPE_NAME_NOT_UNIQUE.equals(cmsTypeService.checkTypeNameUnique(cmsType)))
        {
            return error("新增公告类型'" + cmsType.getTypeName() + "'失败，公告类型名称已存在");
        }
        return toAjax(cmsTypeService.insertCmsType(cmsType));
    }

    @PostMapping("/checkTypeNameUnique")
    @ResponseBody
    public String checkTypeNameUnique(CmsType cmsType){
        return cmsTypeService.checkTypeNameUnique(cmsType);
    }

    /**
     * 修改数据的回显
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, ModelMap modelMap){
        //根据传递过来的typeId查询一个CmsType对象，并将该对象放入到作用域中
        modelMap.addAttribute("CmsType",cmsTypeService.selectCMSTypeById(id));
        return prefix + "/edit";
    }

    @RequiresPermissions("cms:type:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CmsType cmsType){
        if (TypeConstants.TYPE_NAME_NOT_UNIQUE.equals(cmsTypeService.checkTypeNameUnique(cmsType)))
        {
            return error("修改公告类型'" + cmsType.getTypeName() + "'失败，公告类型名称已存在");
        }
        return toAjax(cmsTypeService.editCMSType(cmsType));
    }

    @RequiresPermissions("cms:type:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Integer[] ids){
        try{
            int result = cmsTypeService.deleteCMSTypeByIds(ids);
            return toAjax(result);
        }catch (CMSTypeNotDeleteException e){
            return AjaxResult.error(e.getMessage());
        }
    }

}
