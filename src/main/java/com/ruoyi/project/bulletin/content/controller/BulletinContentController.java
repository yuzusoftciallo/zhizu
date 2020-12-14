package com.ruoyi.project.bulletin.content.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.bulletin.content.domain.BulletinContent;
import com.ruoyi.project.bulletin.content.service.IBulletinContentService;
import com.ruoyi.project.bulletin.content.domain.VO.BulletinContentVO;
import com.ruoyi.project.system.user.domain.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公告内容控制器
 * 17361 21:21
 */
@Controller
@RequestMapping("/bulletin/content")
public class BulletinContentController extends BaseController {

    @Autowired
    private IBulletinContentService iBulletinContentService;

    /**
     * 公告内容页面请求
     */
    private String prefix = "bulletin/content";

    @RequiresPermissions("bulletin:content:view")
    @GetMapping()
    public String content() {
        return prefix + "/content";
    }

    /**
     * 查询公告内容
     */
    @ResponseBody
    @PostMapping("/list")
    @RequiresPermissions("bulletin:content:list")
    public TableDataInfo list(BulletinContent bulletinContent){
        //调用分页设置
        startPage();
        List<BulletinContentVO> bulletinContents = iBulletinContentService.selectList(bulletinContent);
        return getDataTable(bulletinContents);
    }

    /**
     * add页面跳转
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 公告内容新增
     */
    @ResponseBody
    @RequiresPermissions("bulletin:content:add")
    @PostMapping("/add")
    public AjaxResult add(BulletinContent bulletinContent){
        User sysUser = getSysUser();
        return iBulletinContentService.add(bulletinContent,sysUser.getUserName());
    }

    /**
     * 公告内容修改页面跳转
     */
    @GetMapping("/edit/{contentId}")
    public String edit(@PathVariable Long contentId, Model model){
        model.addAttribute("content",iBulletinContentService.selectById(contentId));
        return prefix + "/edit";
    }

    /**
     * 修改公告内容
     */
    @PostMapping("/edit")
    @RequiresPermissions("bulletin:content:edit")
    @ResponseBody
    public AjaxResult edit(BulletinContent bulletinContent){
        User sysUser = getSysUser();
        return iBulletinContentService.editContent(bulletinContent,sysUser.getUserName());
    }

    /**
     * 删除公告内容
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("bulletin:content:remove")
    public AjaxResult remove(Long[] ids){
       return iBulletinContentService.remove(ids);
    }
}
