package com.ruoyi.project.cms.type.service;

import com.ruoyi.common.constant.TypeConstants;
import com.ruoyi.common.exception.cms.CMSTypeNotDeleteException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.cms.content.domain.Content;
import com.ruoyi.project.cms.content.mapper.ContentMapper;
import com.ruoyi.project.cms.type.domain.CmsType;
import com.ruoyi.project.cms.type.mapper.CMSTypeMapper;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIgnoredError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CMSTypeServiceImpl implements ICMSTypeService {

    @Autowired
    private CMSTypeMapper cmsTypeMapper;

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public List<CmsType> selectCMSTypeList(CmsType cmsType) {
        return cmsTypeMapper.selectCMSTypeList(cmsType);
    }

    @Override
    public int insertCmsType(CmsType cmsType) {
        cmsType.setCreateBy(ShiroUtils.getLoginName());
        return cmsTypeMapper.insertCmsType(cmsType);
    }

    @Override
    public String checkTypeNameUnique(CmsType cmsType) {
        // 修改前的typeName校验
        //考虑如果是修改公告类型名称时，如果修改的数据在数据库中是这条数据本身，就直接校验通过
        //根据Id去数据中查询cmsType.getTypeId()， 如果查询到的数据库的TypeName和页面传递过来的数据值相等时，我们就直接也是通过验证
        if(cmsType.getTypeId() != null && cmsType.getTypeId() != 0){
            CmsType cmsTypeResult = cmsTypeMapper.selectCMSTypeById(cmsType.getTypeId());
            if(cmsTypeResult.getTypeName().equals(cmsType.getTypeName())){
                return TypeConstants.TYPE_NAME_UNIQUE;
            }
        }
        /**
         * 由前台页面传递typeId以及typeName
         * 根据typeName从数据库进行查询，查询是否查询出一个CmsType对象
         * 如果CmsType对象存在且查询的对象的id和页面传递typeId值相同时
         * 则说明typeName是不可用的
         */
        Integer typeId = StringUtils.isNull(cmsType.getTypeId()) ? -1 : cmsType.getTypeId();
        CmsType info = cmsTypeMapper.checkTypeNameUnique(cmsType.getTypeName());

        if (StringUtils.isNotNull(info) && info.getTypeId().intValue() != typeId.intValue())
        {
            return TypeConstants.TYPE_NAME_NOT_UNIQUE;
        }
        return TypeConstants.TYPE_NAME_UNIQUE;
    }

    @Override
    public CmsType selectCMSTypeById(int id) {
        return cmsTypeMapper.selectCMSTypeById(id);
    }

    @Override
    public int editCMSType(CmsType cmsType) {
        cmsType.setUpdateBy(ShiroUtils.getLoginName());
        return cmsTypeMapper.editCMSType(cmsType);
    }

    /**
     *  如果我们要删除一个公告类型，就必须保证该公告类型下没有其他的公告内容
     */
    @Override
    public int deleteCMSTypeByIds(Integer[] ids) {
        boolean flag = true;
        for (Integer id : ids) {
            List<Content> contents = contentMapper.selectContentByTypeId(id);
            if (contents != null && contents.size()>0){
                flag = false;
                break;
            }
        }
        if (!flag){
            throw new CMSTypeNotDeleteException("公告类型还有公告内容，不可以删除！");
        }
        return cmsTypeMapper.deleteCMSTypeByIds(ids);
    }

}
