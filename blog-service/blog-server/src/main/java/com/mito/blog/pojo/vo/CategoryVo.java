package com.mito.blog.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CategoryVo {

    /**
     * id
     */
    private String id;

    /**
     * 分类名
     */
    private String name;

    public void setId(Long id) {
        this.id = id.toString();
    }
}
