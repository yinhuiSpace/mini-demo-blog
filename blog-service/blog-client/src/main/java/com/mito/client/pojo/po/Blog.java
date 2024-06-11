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
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 分类
     */
    private Long categoryId;

    /**
     * 缩略图
     */
    private String thumbnail;

    /**
     * 是否置顶0=否1=是
     */
    private String isTop;

    /**
     * 是否草稿0=已发布1=草稿
     */
    private String status;

    /**
     * 浏览量
     */
    private Long viewCount;

    /**
     * 是否允许评论
     */
    private String isComment;

    /**
     * 作者
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private Timestamp createTime;

    /**
     * 更新者
     */
    private Long updateBy;

    /**
     * 更新时间
     */
    private Timestamp updateTime;

    /**
     * 是否删除0=否
     */
    private Integer delFlag;


}
