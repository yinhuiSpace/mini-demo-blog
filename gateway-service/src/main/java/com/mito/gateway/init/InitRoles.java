package com.mito.gateway.init;


import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mito.gateway.constants.RoleConstants;
import com.mito.gateway.po.Menu;
import com.mito.gateway.po.Role;
import com.mito.gateway.po.RoleMenu;
import com.mito.gateway.service.MenuService;
import com.mito.gateway.service.RoleMenuService;
import com.mito.gateway.service.RoleService;
import com.mito.gateway.service.UserRoleService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class InitRoles implements CommandLineRunner {

    @Resource
    RoleService roleService;

    @Resource
    MenuService menuService;

    @Resource
    UserRoleService userRoleService;

    @Resource
    RoleMenuService roleMenuService;

    @Resource
    StringRedisTemplate stringRedisTemplate;


    @Override
    public void run(String... args) throws Exception {

        List<Menu> menuList = menuService.list();

        for (Menu menu : menuList) {

            Integer menuId = menu.getId();

            LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(RoleMenu::getMenuId,menuId);
            List<RoleMenu> roleMenus = roleMenuService.list(wrapper);

            List<String> roleKeys = roleMenus.stream().map(new Function<RoleMenu, String>() {
                @Override
                public String apply(RoleMenu roleMenu) {

                    Integer roleId = roleMenu.getRoleId();
                    LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
                    queryWrapper.eq(Role::getId, roleId);

                    return roleService.getOne(queryWrapper).getRoleKey();
                }
            }).collect(Collectors.toList());

            stringRedisTemplate.opsForList().rightPushAll(RoleConstants.PERMISSION_PATH+menu.getPerms(),roleKeys);
        }

    }
}
