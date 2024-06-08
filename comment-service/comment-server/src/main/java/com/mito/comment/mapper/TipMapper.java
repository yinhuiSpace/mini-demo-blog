package com.mito.comment.mapper;

import com.mito.comment.pojo.po.Tip;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author root
 * @since 2024-06-08
 */
@Mapper
public interface TipMapper extends BaseMapper<Tip> {

    @Select("select * from db_xzz_blog.tb_tip order by rand() limit 1")
    Tip getTipByRandom();

}
