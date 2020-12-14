package com.ruoyi.project.cms.type.service;

import com.ruoyi.project.cms.type.domain.CmsType;

import java.util.List;

public interface ICMSTypeService {

    List<CmsType> selectCMSTypeList(CmsType cmsType);

    int insertCmsType(CmsType cmsType);

    String checkTypeNameUnique(CmsType cmsType);

    CmsType selectCMSTypeById(int id);

    int editCMSType(CmsType cmsType);

    int deleteCMSTypeByIds(Integer[] ids);
}
