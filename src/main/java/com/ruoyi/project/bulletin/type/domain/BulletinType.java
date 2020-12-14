package com.ruoyi.project.bulletin.type.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 17361 15:54
 */
@Data
public class BulletinType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long typeId;

    private String typeName;

    private String typeDesc;

    private Integer typeSort;

}
