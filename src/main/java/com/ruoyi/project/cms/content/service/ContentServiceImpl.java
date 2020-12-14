package com.ruoyi.project.cms.content.service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.cms.content.domain.Content;
import com.ruoyi.project.cms.content.domain.ContentVo;
import com.ruoyi.project.cms.content.mapper.ContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;
import java.util.List;

@Service
public class ContentServiceImpl implements IContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public List<ContentVo> selectContentList(Content content) {
        return contentMapper.selectContentList(content);
    }

    @Override
    public int insertContent(Content content) {
        content.setCreateBy(ShiroUtils.getLoginName());
        return contentMapper.insertContent(content);
    }

    @Override
    public int deleteContentByIds(Integer[] ids) {
        return contentMapper.deleteContentByIds(ids);
    }

    @Override
    public Content selectContentById(Integer contentId) {
        return contentMapper.selectContentById(contentId);
    }

}
