package com.mito.gateway.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2024-06-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父级菜单，顶级菜单此字段为0
     */
    private Integer parentId;

    /**
     * 优先级
     */
    private Integer orderNum;

    /**
     * 路由url
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 是否为内置
     */
    private Integer isFrame;

    /**
     * 菜单类型：D目录,M菜单
     */
    private String menuType;

    /**
     * 是否显示：0否1是
     */
    private String visible;

    /**
     * 是否停用：0否1是
     */
    private String status;

    /**
     * 对应的接口地址
     */
    private String perms;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 创建者
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private Long updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    private Integer delFlag;

    /**
     * 备注
     */
    private String remark;


}
