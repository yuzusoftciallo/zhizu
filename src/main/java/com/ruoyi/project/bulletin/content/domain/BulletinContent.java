package com.ruoyi.project.bulletin.content.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 17361 21:23
 */
@Data
public class BulletinContent extends BaseEntity {

    private Long contentId;
    private Long typeId;
    private String contentTitle;
    private String contentText;
}
