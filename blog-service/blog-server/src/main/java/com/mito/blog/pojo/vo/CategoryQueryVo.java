package com.mito.blog.pojo.vo;

import com.mito.common.utils.DateTimeClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.bouncycastle.util.Times;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CategoryQueryVo {

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

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 创建时间
     */
    private String updateTime;

    public void setCreateTime(Timestamp createTime) {
        this.createTime = DateTimeClient.toStr(createTime,DateTimeClient.SIMPLE_FORMAT);
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = DateTimeClient.toStr(updateTime,DateTimeClient.SIMPLE_FORMAT);
    }

    public void setId(Long id) {
        this.id = id.toString();
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId.toString();
    }
}
