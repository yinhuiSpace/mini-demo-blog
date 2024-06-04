package com.mito.auth.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mito.auth.pojo.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author root
 * @since 2024-06-04
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
