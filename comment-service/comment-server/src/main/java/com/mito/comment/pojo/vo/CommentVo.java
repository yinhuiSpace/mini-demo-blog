package com.mito.comment.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CommentVo {

    private Long id;
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
    * 所属评论用户名
    * */
    private String toCommentUsername;

    /**
    * 评论人
    * */
    private String username;

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
    private Long createBy;

    /**
     * 创建时间
     */
    private String createTimeStr;

    /**
    * 子评论
    * */
    private List<CommentVo> children;
}
