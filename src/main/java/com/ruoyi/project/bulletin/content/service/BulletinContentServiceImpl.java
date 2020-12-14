package com.ruoyi.project.bulletin.content.service;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.bulletin.content.domain.BulletinContent;
import com.ruoyi.project.bulletin.content.mapper.BulletinContentMapper;
import com.ruoyi.project.bulletin.content.domain.VO.BulletinContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 17361 21:24
 */
@Service
public class BulletinContentServiceImpl implements IBulletinContentService{

    @Autowired
    private BulletinContentMapper bulletinContentMapper;

    /**
     * 查询公告内容
     * @param bulletinContent
     * @return
     */
    @Override
    public List<BulletinContentVO> selectList(BulletinContent bulletinContent) {
        return bulletinContentMapper.selectList(bulletinContent);
    }

    /**
     * 添加公告内容
     * @param bulletinContent
     * @return
     */
    @Override
    public AjaxResult add(BulletinContent bulletinContent,String username) {
        bulletinContent.setCreateBy(username);
        int result = bulletinContentMapper.add(bulletinContent);
        if (result == 0){
            return  AjaxResult.error("添加公告内容失败");
        }
        return AjaxResult.success("添加公告内容成功");
    }

    /**
     * 根据id查询公告内容
     * @param contentId
     * @return
     */
    @Override
    public BulletinContent selectById(Long contentId) {
        return bulletinContentMapper.selectById(contentId);
    }

    /**
     * 更新公告内容
     * @param bulletinContent
     * @param username
     * @return
     */
    @Override
    public AjaxResult editContent(BulletinContent bulletinContent,String username) {
        int result = bulletinContentMapper.editContent(bulletinContent,username);
        if (result == 0){
            return AjaxResult.error("更新公告内容失败");
        }
        return AjaxResult.success("更新公告内容成功");
    }

    /**
     * 删除公告内容
     * @param contentIds
     * @return
     */
    @Override
    public AjaxResult remove(Long[] contentIds) {
        int result = bulletinContentMapper.remove(contentIds);
        if (result == 0){
            return AjaxResult.error("删除公告内容失败");
        }
        return AjaxResult.success("删除公告内容成功");
    }


    /**
     * 查询公告类型下是否有公告内容
     */
    public int selectCount(Long[] typeId){
       return bulletinContentMapper.selectCount(typeId);
    }
}
