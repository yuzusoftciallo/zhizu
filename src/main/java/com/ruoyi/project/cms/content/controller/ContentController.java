package com.ruoyi.project.cms.content.controller;

import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.cms.content.domain.Content;
import com.ruoyi.project.cms.content.domain.ContentVo;
import com.ruoyi.project.cms.content.service.IContentService;
import com.ruoyi.project.cms.type.domain.CmsType;
import com.ruoyi.project.cms.type.domain.CmsTypeVo;
import com.ruoyi.project.cms.type.service.ICMSTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 这是cms-公告内容管理的控制器
 */
@Controller
@RequestMapping("/cms/content")
public class ContentController extends BaseController {

    @Autowired
    private IContentService contentService;

    @Autowired
    private ICMSTypeService cmsTypeService;

    //前缀字符串，用于跳转
    private String prefix = "cms/content";

    /**
     * 创建显示内容管理主页的控制器单元方法
     * RequiresPermissions("cms:content:view")访问主页的权限
     *
     * @return
     */
    @RequiresPermissions("cms:content:view")
    @GetMapping()
    public String content() {
        return prefix + "/content";
    }

    /**
     * 内容的分页查询
     * 1.使用BaseController中的pageHelper进行分页
     * 2.调用service查询数据
     * 3.使用BaseController中的getDataTable(data)返回数据
     *
     * @param content
     * @return
     */
    @RequiresPermissions("cms:content:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Content content) {
        startPage();
        List<ContentVo> data = contentService.selectContentList(content);
        return getDataTable(data);
    }

    /**
     * 新增公告页面跳转
     */
    @GetMapping("/add")
    public String add(Model model) {
        CmsType cmsType = new CmsType();
        List<CmsType> cmsTypes = cmsTypeService.selectCMSTypeList(cmsType);
        System.out.println("数据库中的数据："+cmsTypes.size());

        List<CmsTypeVo> vos = new ArrayList<>();
        for (CmsType cmsTypeSrc : cmsTypes) {
            CmsTypeVo cmsTypeVoDest = new CmsTypeVo();
            //spring为我们提供了一个属性拷贝工具，注意拷贝对象，不是拷贝集合
            BeanUtils.copyBeanProp(cmsTypeVoDest,cmsTypeSrc);
            vos.add(cmsTypeVoDest);
        }
        //将vos放到作用域中
        System.out.println("转换后的vo对象数量："+vos.size());
        model.addAttribute("vos",vos);
        return prefix + "/add";
    }

    /**
     * 需要给定权限（cms:content:add）
     * 是post请求
     * ajax的请求返回AjaxResult对象
     * @param content
     * @return
     */
    @RequiresPermissions("cms:content:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Content content){
        return toAjax(contentService.insertContent(content));
    }


    /**
     * 根据id删除指定的content数据，ids是一个数组，前台可以传递多个值
     * @param ids
     * @return
     */
    @RequiresPermissions("cms:content:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Integer [] ids){
        return toAjax(contentService.deleteContentByIds(ids));
    }

    /**
     *  修改公告内容页面跳转
     *      1.根据传递contentId查询Content对象，并将对象放入作用域中
     *      2.还需要将所有的CmsType对象，传递到edit页面中，供选择使用
     */
    @GetMapping("/edit/{contentId}")
    public String edit(@PathVariable Integer contentId, ModelMap modelMap){
        modelMap.addAttribute("content", contentService.selectContentById(contentId));

        CmsType cmsType = new CmsType();
        List<CmsType> cmsTypes = cmsTypeService.selectCMSTypeList(cmsType);

        List<CmsTypeVo> vos = new ArrayList<>();
        for (CmsType cmsTypeSrc : cmsTypes) {
            CmsTypeVo cmsTypeVoDest = new CmsTypeVo();
            //spring为我们提供了一个属性拷贝工具，注意拷贝对象，不是拷贝集合
            BeanUtils.copyBeanProp(cmsTypeVoDest,cmsTypeSrc);
            vos.add(cmsTypeVoDest);
        }
        modelMap.addAttribute("vos",vos);
        return prefix + "/edit";
    }




}
