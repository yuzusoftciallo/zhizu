package com.ruoyi.project.bulletin.type.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.bulletin.type.domain.BulletinType;
import com.ruoyi.project.bulletin.type.service.IBulletinTypeService;
import com.ruoyi.project.system.user.domain.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 17361 15:53
 */

@Controller
@RequestMapping("/bulletin/type")
public class BulletinTypeController extends BaseController {

    //注入service层
    @Autowired
    private IBulletinTypeService iBulletinTypeService;


    private String prefix = "bulletin/type";

    @RequiresPermissions("bulletin:type:view")
    @GetMapping()
    public String user() {
        return prefix + "/type";
    }

    //分页查询方法
    @PostMapping("/list")
    @ResponseBody
    @RequiresPermissions("bulletin:type:list")
    public TableDataInfo selectTypeList(BulletinType bulletinType) {
        //设置分页参数
        startPage();
        //调用iBulletinTypeService方法
        List<BulletinType> list = iBulletinTypeService.selectTypeList(bulletinType);
        return getDataTable(list);
    }


    /**
     * 表单校验
     */
    @PostMapping("/checkPostNameUnique")
    @ResponseBody
    public String checkPostNameUnique(String typeName) {
        return iBulletinTypeService.checkPostNameUnique(typeName);
    }


    /**
     * 跳转添加公告页面
     */
    @RequiresPermissions("bulletin:type:add")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 添加公告
     */
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(BulletinType bulletinType) {
        //设置bulletinType创建信息
        User user = getSysUser();
        return iBulletinTypeService.add(bulletinType, user.getUserName());
    }


    /**
     * 修改公告类型页面跳转
     */
    @RequiresPermissions("bulletin:type:edit")
    @GetMapping("/edit/{typeId}")
    public String edit(@PathVariable Long typeId, Model model) {
        //跳转前根据id查询公告信息，并且使用ModelMap共享给页面
        model.addAttribute("bulletinType", iBulletinTypeService.selectById(typeId));
        return prefix + "/edit";
    }

    /**
     * 修改公告
     */
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult edit(BulletinType bulletinType) {
        return iBulletinTypeService.edit(bulletinType);
    }

    /**
     * 删除公告
     */
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Long[] ids) {
        return iBulletinTypeService.remove(ids);
    }
}
