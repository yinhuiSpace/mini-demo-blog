package com.mito.client.pojo.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 * 
 * </p>
 *
 * @author root
 * @since 2024-06-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 分类名
     */
    private String name;

    /**
     * 父级id 没有所属父级则为0
     */
    private Long parentId;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态0=正常1=禁用
     */
    private String status;

    /**
     * 创建者
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 更新人
     */
    private Long updateBy;

    /**
     * 创建时间
     */
    private Timestamp updateTime;

    /**
     * 是否删除0=否1=是
     */
    private Integer delFlag;


}
