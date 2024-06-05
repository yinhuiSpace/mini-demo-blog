package com.mito.comment.pojo.po;

import com.baomidou.mybatisplus.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author root
 * @since 2024-06-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 类型：博客评论，友链评论
     */
    private String type;

    /**
     * 所属博客
     */
    private Long articleId;

    /**
     * 区分是否为根评论
     */
    private Long rootId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 所属评论的用户，回复的目标用户
     */
    private Long toCommentUserId;

    /**
     * 所回复的评论
     */
    private Long toCommentId;

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Timestamp createTime;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp updateTime;

    /**
     * 是否删除0=否1=是
     */
    @TableLogic
    private Integer delFlag;


}
