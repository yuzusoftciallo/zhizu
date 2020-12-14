package com.ruoyi.project.bulletin.content.mapper;

import com.ruoyi.project.bulletin.content.domain.BulletinContent;
import com.ruoyi.project.bulletin.content.domain.VO.BulletinContentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 17361 21:23
 */
public interface BulletinContentMapper {
    List<BulletinContentVO> selectList(BulletinContent bulletinContent);

    int add(BulletinContent bulletinContent);

    BulletinContent selectById(Long contentId);

    int editContent(@Param("bulletinContent") BulletinContent bulletinContent,@Param("username") String username);

    int remove(Long[] contentIds);

    int selectCount(Long[] typeId);
}
