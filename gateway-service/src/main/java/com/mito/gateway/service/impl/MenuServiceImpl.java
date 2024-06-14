package com.mito.gateway.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mito.gateway.mapper.MenuMapper;
import com.mito.gateway.po.Menu;
import com.mito.gateway.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author root
 * @since 2024-06-13
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
