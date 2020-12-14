package com.ruoyi.project.cms.type.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

public class CmsType extends BaseEntity {

    private Integer typeId;
    private String typeName;
    private String typeDesc;
    private Integer typeSort;

    @Override
    public String toString() {
        return "CmsType{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", typeDesc='" + typeDesc + '\'' +
                ", typeSort=" + typeSort +
                '}';
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public Integer getTypeSort() {
        return typeSort;
    }

    public void setTypeSort(Integer typeSort) {
        this.typeSort = typeSort;
    }
}
