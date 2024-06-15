package com.mito.blog.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BlogReviewVo {

    /**
     * 文章id
     * */
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 文章内容
     * */
    private String content;


    /**
     * 摘要
     */
    private String summary;

    /**
     * 分类名
     * */
    private String categoryName;

    /**
     * 缩略图
     */
    private String thumbnail;

    /**
     * 是否允许评论
     */
    private String isComment;

    /**
     * 作者
     * */
    private String createBy;

    /**
     * 是否草稿0=已发布1=草稿
     */
    private String status;

    public void setId(Long id) {
        this.id = id.toString();
    }

}
