package com.mito.blog.pojo.vo;

import com.mito.common.utils.DateTimeClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BlogVo {

    /**
    * 文章id
    * */
    private String id;

    /**
     * 标题
     */
    private String title;

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
     * 浏览量
     */
    private Long viewCount;

    /**
    * 作者
    * */
    private String createBy;

    /**
    * 作者名
    * */
    private String authorName;

    private String createTime;



    public void setId(Long id) {
        this.id = id.toString();
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = DateTimeClient.toStr(createTime, DateTimeClient.SIMPLE_FORMAT);
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy.toString();
    }
}
