package com.ruoyi.project.bulletin.type.service;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.bulletin.type.domain.BulletinType;
import com.ruoyi.project.system.user.domain.User;

import java.util.List;

/**
 * 17361 15:54
 */
public interface IBulletinTypeService {

    List<BulletinType> selectTypeList(BulletinType bulletinType);

    String checkPostNameUnique(String typename);

    AjaxResult add(BulletinType bulletinType, String name);

    BulletinType selectById(Long typeId);

    AjaxResult edit(BulletinType bulletinType);

    AjaxResult remove(Long[] ids);
}
