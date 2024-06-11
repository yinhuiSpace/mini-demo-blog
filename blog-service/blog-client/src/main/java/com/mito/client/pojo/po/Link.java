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
public class Link implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 图标
     */
    private String logo;

    /**
     * 描述
     */
    private String description;

    /**
     * 网站连接
     */
    private String address;

    /**
     * 状态0=通过1=不通过2=待审核
     */
    private String status;

    /**
     * 创建者·1
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
     * 更新时间
     */
    private Timestamp updateTime;

    /**
     * 删除标志0=未删除1=已删除
     */
    private Integer delFlag;


}
