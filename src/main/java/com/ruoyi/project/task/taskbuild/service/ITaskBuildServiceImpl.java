package com.ruoyi.project.task.taskbuild.service;

import com.ruoyi.project.task.taskbuild.domain.Level;
import com.ruoyi.project.task.taskbuild.domain.Task;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("task")
public class ITaskBuildServiceImpl implements ITaskBuildService{

    @Autowired
    private com.ruoyi.project.task.taskbuild.mapper.TaskBuildMapper TaskBuildMapper;
    @Override
    public List<Task> selectTaskList(Task task) {

        return TaskBuildMapper.selectTaskList(task);
    }

    @Override
    public List<Level> selectLevel() {
        return TaskBuildMapper.selectLevel() ;
    }


}
