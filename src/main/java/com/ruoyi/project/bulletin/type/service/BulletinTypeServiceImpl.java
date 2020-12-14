package com.ruoyi.project.bulletin.type.service;

import com.ruoyi.common.constant.BulletinTypeConstants;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.bulletin.content.service.BulletinContentServiceImpl;
import com.ruoyi.project.bulletin.type.domain.BulletinType;
import com.ruoyi.project.bulletin.type.mapper.BulletinTypeMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 17361 15:55
 */
@Service
public class BulletinTypeServiceImpl implements IBulletinTypeService{

    //注入mapper层
    @Autowired
    private BulletinTypeMapper mapper;

    //注入BulletinContentServiceImpl层
    @Autowired
    private BulletinContentServiceImpl bulletinContentService;

    @Override
    public List<BulletinType> selectTypeList(BulletinType bulletinType) {
        return mapper.selectTypeList(bulletinType);
    }

    @Override
    public String checkPostNameUnique(String typename) {

        //查询公告名称是否重复
        BulletinType bulletinType = mapper.checkPostNameUnique(typename);
        if (bulletinType != null){
            return BulletinTypeConstants.ANNOUNCEMENT_NAME_EXISTS;
        }

        return BulletinTypeConstants.ANNOUNCEMENT_NAME_NOT_EXISTS;
    }

    /**
     * 添加公告类型
     * @param bulletinType
     * @param name
     * @return
     */
    @Override
    public AjaxResult add(BulletinType bulletinType, String name) {
        bulletinType.setCreateBy(name);
        int result = mapper.add(bulletinType);
        if (result == 0){
           return AjaxResult.error("添加公告失败");
        }
        return AjaxResult.success("添加公告成功");
    }


    /**
     * 根据id查询公告
     * @param typeId
     * @return
     */
    @Override
    public BulletinType selectById(Long typeId) {
        return mapper.selectById(typeId);
    }


    /**
     * 修改公告类型
     * @param bulletinType
     * @return
     */
    @Override
    public AjaxResult edit(BulletinType bulletinType) {
        int result = mapper.edit(bulletinType);
        if (result == 0){
            return AjaxResult.error("更新公告失败");
        }
        return AjaxResult.success("更新公告成功");
    }

    /**
     * 删除公告
     * @param ids
     * @return
     */
    @Override
    public AjaxResult remove(Long[] ids) {
        int Count = bulletinContentService.selectCount(ids);
        if (Count > 0){
            return AjaxResult.error("该公告类型正在被使用，无法删除");
        }
        int result = mapper.remove(ids);
        if (result == 0){
            return AjaxResult.error("删除公告失败");
        }
        return  AjaxResult.success("删除公告成功");
    }
}
