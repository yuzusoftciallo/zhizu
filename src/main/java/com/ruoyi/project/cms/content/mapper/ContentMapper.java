package com.ruoyi.project.cms.content.mapper;

import com.ruoyi.project.cms.content.domain.Content;
import com.ruoyi.project.cms.content.domain.ContentVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentMapper {

    /**
     *  根据条件 查询所有的Content对象
     * @param content 查询条件
     * @return
     */
    List<ContentVo> selectContentList(Content content);

    /**
     * 向数据库cms_content中插入一条数据
     * @param content
     * @return
     */
    int insertContent(Content content);

    /**
     * 根据id删除指定的cms_content表中的数据
     * @param ids
     * @return
     */
    int deleteContentByIds(Integer[] ids);

    Content selectContentById(Integer contentId);

    List<Content> selectContentByTypeId(Integer typeId);
}
