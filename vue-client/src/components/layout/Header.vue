<script setup lang="ts">

import {useUserStore} from "../../stores/user.ts";
import {ref} from "vue";
import axiosInstance from "../../api";
import {ElMessage} from "element-plus";
import {useThemeStore} from "../../stores/theme.ts";
import {useToggle} from "@vueuse/shared";
import router from "../../router";
import {ArrowDown} from "@element-plus/icons-vue";


const change = (value) => {
  console.log(value);
}

const toggle = useToggle(useThemeStore().isDark);

const logout = () => {
  axiosInstance.post('/auth-service/auth/logout')
      .then((resp) => {
        useUserStore().removeLogin()
        ElMessage.success('退出登录成功')
      })
}

const toPerson = () => {
  router.push({path:`/me`})
}

const search=ref("")
</script>

<template>
  <b-navbar :fixed-top="true" style="height: 65px;box-shadow: 2px 0 6px rgba(0, 21, 41, .35);">
    <template #brand>
      <b-navbar-item tag="router-link" :to="{ path: '/' }">
        <img
            src="../../assets/logo.png"
            alt="图标"
            style="height: 65px;object-fit: fill"
            height="512"
        >
      </b-navbar-item>
    </template>

    <template #start>
      <b-navbar-item
          class="is-hidden-desktop"
          tag="router-link"
          :to="{path:'/'}"
      >
        主页
      </b-navbar-item>
    </template>

    <template #end>
      <b-navbar-item>
        <b-field>
          <el-input
              style="width: 300px"
              placeholder="搜索文章或资源"
              prefix-icon="Search"
              v-model="search"
          />
        </b-field>
      </b-navbar-item>
      <b-navbar-item v-if="useUserStore().isLogin()">
        <div class="block">
          <el-avatar fit="cover" :size="40"
                     :src="useUserStore().userInfo.avatar"
                     style="border: solid #F0F0F2 2px" @click="toPerson"/>
        </div>
<!--        <el-switch-->
<!--            v-model="useThemeStore().isDark"-->
<!--            inline-prompt-->
<!--            active-text="黑夜"-->
<!--            size="small"-->
<!--            inactive-text="白天"-->
<!--            @change="toggle"-->
<!--        />-->
      </b-navbar-item>

      <b-navbar-item v-if="useUserStore().isLogin()">
        <el-dropdown>
        <span class="el-dropdown-link">
          {{useUserStore().userInfo.username}}
          <el-icon class="el-icon--right">
            <arrow-down />
          </el-icon>
        </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item icon="Plus" @click="toPerson">个人中心</el-dropdown-item>
              <el-dropdown-item icon="CirclePlusFilled" @click="logout">
                退出登录
              </el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </b-navbar-item>


<!--      <b-navbar-dropdown-->
<!--          v-if="useUserStore().token!=null&&useUserStore().token.trim().length>0"-->
<!--          :label="useUserStore().userInfo.username"-->
<!--          class="mr-6"-->
<!--      >-->
<!--        <b-navbar-item @click="toPerson">-->
<!--          个人中心-->
<!--        </b-navbar-item>-->
<!--        <b-navbar-item @click="logout">-->
<!--          退出登录-->
<!--        </b-navbar-item>-->
<!--      </b-navbar-dropdown>-->

      <b-navbar-item v-else tag="div">
        <router-link to="/register">
          <el-button type="danger" plain icon="edit">注册</el-button>
        </router-link>

        <router-link to="/login">
          <el-button type="success" plain style="margin-left: 10px;" icon="user">登录</el-button>
        </router-link>
      </b-navbar-item>
    </template>
  </b-navbar>
</template>

<style scoped lang="less">

</style>