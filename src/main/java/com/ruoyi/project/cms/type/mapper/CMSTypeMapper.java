package com.ruoyi.project.cms.type.mapper;

import com.ruoyi.project.cms.type.domain.CmsType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CMSTypeMapper {

    List<CmsType> selectCMSTypeList(CmsType cmsType);

    int insertCmsType(CmsType cmsType);

    CmsType checkTypeNameUnique(String typeName);

    CmsType selectCMSTypeById(int id);

    int editCMSType(CmsType cmsType);

    int deleteCMSTypeByIds(Integer[] ids);
}
