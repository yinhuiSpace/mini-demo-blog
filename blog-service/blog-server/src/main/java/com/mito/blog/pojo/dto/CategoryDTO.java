package com.mito.blog.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CategoryDTO {

    /**
     * id
     */
    private String id;

    /**
     * 分类名
     */
    private String name;

    /**
     * 父级id 没有所属父级则为0
     */
    private String parentId;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态0=正常1=禁用
     */
    private String status;


}
