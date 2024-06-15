package com.mito.comment.pojo.vo;

import com.mito.common.utils.DateTimeClient;
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
public class CommentQueryVo {

    private String id;
    /**
     * 所属博客
     */
    private String title;

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
     * 子评论数量
     * */
    private Long children;

    /**
    * 创建时间
    * */
    private String createTime;

    public void setId(Long id) {
        this.id = id.toString();
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = DateTimeClient.toStr(createTime,DateTimeClient.SIMPLE_FORMAT);
    }
}
