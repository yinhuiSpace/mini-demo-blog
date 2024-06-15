package com.mito.blog.pojo.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.mito.common.utils.DateTimeClient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BlogDataVo {

    private String id;

    private Long viewCount;

    /**
     * 标题
     */
    private String title;

    /**
     * 分类
     */
    private Long categoryId;


    /**
     * 创建时间
     */
    private String createTime;


    /**
     * 更新时间
     */
    private String updateTime;

    public void setId(Long id) {
        this.id = id.toString();
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = DateTimeClient.toStr(createTime, DateTimeClient.SIMPLE_FORMAT);
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = DateTimeClient.toStr(updateTime,DateTimeClient.SIMPLE_FORMAT);
    }
}
