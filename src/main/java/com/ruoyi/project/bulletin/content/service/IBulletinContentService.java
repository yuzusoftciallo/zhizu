package com.ruoyi.project.bulletin.content.service;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.bulletin.content.domain.BulletinContent;
import com.ruoyi.project.bulletin.content.domain.VO.BulletinContentVO;

import java.util.List;

/**
 * 17361 21:23
 */
public interface IBulletinContentService {

    List<BulletinContentVO> selectList(BulletinContent bulletinContent);

    AjaxResult add(BulletinContent bulletinContent,String username);

    BulletinContent selectById(Long contentId);

    AjaxResult editContent(BulletinContent bulletinContent,String username);

    AjaxResult remove(Long[] contentIds);
}
