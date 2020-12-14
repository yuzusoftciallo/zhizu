package com.ruoyi.project.bulletin.type.mapper;

import com.ruoyi.project.bulletin.type.domain.BulletinType;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * 17361 15:54
 */
@Mapper
public interface BulletinTypeMapper {
    List<BulletinType> selectTypeList(BulletinType bulletinType);

    BulletinType checkPostNameUnique(String typeName);

    int add(BulletinType bulletinType);

    BulletinType selectById(Long typeId);

    int edit(BulletinType bulletinType);

    int remove(Long[] typeId);
}
