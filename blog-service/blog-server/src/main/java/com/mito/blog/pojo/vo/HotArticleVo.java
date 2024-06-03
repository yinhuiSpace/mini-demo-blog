package com.mito.blog.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class HotArticleVo {

    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 浏览量
     */
    private Long viewCount;

}
