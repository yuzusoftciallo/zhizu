package com.ruoyi.project.task.taskbuild.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.bulletin.content.domain.BulletinContent;
import com.ruoyi.project.bulletin.content.domain.VO.BulletinContentVO;
import com.ruoyi.project.task.taskbuild.domain.Task;
import com.ruoyi.project.task.taskbuild.service.ITaskBuildService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/task/build")

public class TaskBuildController extends BaseController {

    private String prefix = "task/build";

    @Autowired
    private ITaskBuildService iTaskBuildService;

    @RequiresPermissions("task:build:view")
    @GetMapping()
    public String taskbuild() {
        return prefix + "/taskbuild";
    }


    @ResponseBody
    @PostMapping("/list")
    @RequiresPermissions("task:build:list")
    public TableDataInfo list(Task task){

        startPage();
        List<Task> tasks = iTaskBuildService.selectTaskList(task);
        return getDataTable(tasks);
    }




}
