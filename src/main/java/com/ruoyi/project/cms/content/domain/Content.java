package com.ruoyi.project.cms.content.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

public class Content extends BaseEntity {

    private int contentId;
    private int typeId;
    private String contentTitle;
    private String contentText;

    @Override
    public String toString() {
        return "Content{" +
                "contentId=" + contentId +
                ", typeId=" + typeId +
                ", contentTitle='" + contentTitle + '\'' +
                ", contentText='" + contentText + '\'' +
                '}';
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }
}
