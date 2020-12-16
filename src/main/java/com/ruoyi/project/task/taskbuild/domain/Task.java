package com.ruoyi.project.task.taskbuild.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    private Integer tId;
    private String tTitle;
    private String tText;
    private Date tStart;
    private Date tEnd;
    private String depName;
    private String sName;
    private Integer tStatus;
    private String tAnnex;
    private Integer tComplete;






}
