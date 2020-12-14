package com.ruoyi.project.cms.content.service;

import com.ruoyi.project.cms.content.domain.Content;
import com.ruoyi.project.cms.content.domain.ContentVo;

import java.util.List;

public interface IContentService {

    /**
     * 查询所有Content对象
     *
     * @param content 条件查询--只通过cotentTitle进行条件查询，保证参数中cotentTitle有值即可
     * @return
     */
    List<ContentVo> selectContentList(Content content);

    /**
     * 添加公告内容业务代码
     *     调用mamper之前，需要给定createBy属性，从shiroUtils获取登录对象的名称
     * @param content
     * @return
     */
    int insertContent(Content content);

    /**
     * 删除一条或多条公告内容
     * @param ids
     * @return
     */
    int deleteContentByIds(Integer[] ids);

    Content selectContentById(Integer contentId);
}
