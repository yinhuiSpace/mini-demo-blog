<script setup lang="ts">

import {ArrowRight, Expand, Fold, FullScreen, Location} from "@element-plus/icons-vue";
import {ref} from "vue";
import {useRoute} from "vue-router";
import {multiMenu, singleMenu} from "../../api/menu.ts";
import {useUserStore} from "../../stores/user.ts";
import {logout} from "../../api/auth.ts";

const isCollapse = ref(false)

const asideWidth = ref('200px')

const handleCollapse = () => {
  isCollapse.value = !isCollapse.value
  asideWidth.value = isCollapse.value ? '64px' : '200px'
}

const handleFull = () => {
  document.documentElement.requestFullscreen()
}
</script>

<template>
  <el-container>
    <el-aside :width="asideWidth" style="min-height: 100vh;background-color: #001529">
      <div
          style="height: 60px;line-height: 60px;color: white;display: flex;align-items: center;justify-content: center">
        <img src="../../assets/logo.webp" alt="" style="width: 40px;"/>
        <transition name="el-zoom-in-center">
          <span style="margin-left: 5px;font-size: 20px;" v-show="!isCollapse">
            后台管理系统
          </span>
        </transition>
      </div>

      <el-menu :collapse="isCollapse" :collapse-transition="false" style="border: none;" router :default-active="useRoute().fullPath"
               background-color="#001529"
               text-color="rgba(255,255,255,0.65)" active-text-color="#fff">
        <el-menu-item v-for="(item,index) in singleMenu" :key="index" :index="item.path">
          <el-icon>
            <component class="icon" :is="item.icon"/>
          </el-icon>

          <template #title>
            <span>
              {{item.label}}
            </span>
          </template>
        </el-menu-item>

        <el-sub-menu v-for="(item,index) in multiMenu" :key="index" >
          <template #title>
            <el-icon>
              <component class="icon" :is="item.icon"/>
            </el-icon>
            <span>
              {{item.label}}
            </span>
          </template>
          <el-menu-item v-for="(e,i) in item.children" :key="i" :index="e.path">
            {{e.label}}
          </el-menu-item>

        </el-sub-menu>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header>
        <el-icon size="26px" @click="handleCollapse">
          <Fold v-if="!isCollapse"/>
          <Expand v-else/>
        </el-icon>
        <el-breadcrumb :separator-icon="ArrowRight" style="margin-left: 20px">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item :to="{ path: '/user' }">用户管理</el-breadcrumb-item>
        </el-breadcrumb>

        <div style="flex: 1;width: 0;display: flex;align-items: center;justify-content: flex-end">
          <el-icon size="40px" style="margin-right: 4px" @click="handleFull">
            <FullScreen/>
          </el-icon>
          <el-dropdown placement="bottom">
            <div style="display: flex;align-items: center;cursor: default;">
              <img src="../../assets/logo.webp" alt="" style="width: 40px;"/>
              <span style="margin-left: 2px">管理员</span>
            </div>
            <template #dropdown>
              <el-dropdown-menu v-if="useUserStore().isLogin()">
                <el-dropdown-item>个人中心</el-dropdown-item>
                <el-dropdown-item divided @click="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
              <el-dropdown-menu v-else>
                <router-link to="/login">
                  <el-dropdown-item>登录</el-dropdown-item>
                </router-link>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>

      </el-header>

      <el-main>
        <router-view/>
<!--        <div style="box-shadow: 0 0 10px rgba(0,0,0,.1);padding: 10px 20px;border-radius: 5px;margin-bottom: 10px;">-->
<!--          公告：-->
<!--        </div>-->
<!--        <el-card style="width: 500px;">-->
<!--          <template #header>-->
<!--            <span>-->
<!--              标题-->
<!--            </span>-->
<!--          </template>-->
<!--        </el-card>-->
      </el-main>
    </el-container>
  </el-container>
</template>

<style lang="less">
.el-menu--inline {
  background-color: #000c17 !important;
  padding-left: 13px;

  .el-menu-item {
    margin: 0;
    height: 40px;
    line-height: 40px;
  }

  .el-menu-item.is-active {
    margin-left: -10px !important;
    padding-left: 45px !important;
  }
}

.el-menu-item:hover {
  color: #fff !important;
}

.el-sub-menu:hover {
  color: #fff !important;
}

.el-sub-menu__title:hover {
  color: #fff !important;
}

.el-menu-item.is-active {
  background-color: #40a9ff;
  border-radius: 5px;
  margin: 4px;

  .el-menu-tooltip__trigger {
    //margin-left: -10px;
    i {
      margin-left: -3px;
    }
  }

  i {
    margin-left: -3px;
  }

  span {
    margin-left: -3px;
  }
}

//.el-menu-tooltip__trigger .el-tooltip__trigger .el-tooltip__trigger{
//  i{
//    margin-left: -30px;
//  }
//}

.el-menu-item {
  //margin: 4px;
  height: 40px;
  line-height: 40px;
}

.el-sub-menu__title {
  //margin: 4px;
  height: 40px !important;
  line-height: 40px !important;
}

.el-aside {
  transition: width .3s;
  box-shadow: 2px 0 6px rgba(0, 21, 41, .35);
}

.el-header {
  box-shadow: 2px 0 6px rgba(0, 21, 41, .35);
  display: flex;
  align-items: center;
}
</style>