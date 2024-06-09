<script setup lang="ts">

import {useUserStore} from "../../stores/user.ts";
import {ref} from "vue";
import axiosInstance from "../../api";
import {ElMessage} from "element-plus";
import {useThemeStore} from "../../stores/theme.ts";
import {useToggle} from "@vueuse/shared";


const change=(value)=>{
  console.log(value);
}

const toggle = useToggle(useThemeStore().isDark);

const logout=()=>{
  axiosInstance.post('/auth-service/auth/logout')
      .then((resp)=>{
        useUserStore().removeLogin()
        ElMessage.success('退出登录成功')
      })
}
</script>

<template>
  <b-navbar :fixed-top="true">
    <template #brand>
      <b-navbar-item tag="router-link" :to="{ path: '/' }">
        <img
            src="https://raw.githubusercontent.com/buefy/buefy/dev/static/img/buefy-logo.png"
            alt="图标"
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
          <b-autocomplete
              rounded
              :data="['jQuery', 'Vue', 'React']"
              placeholder="搜索内容"
              icon="magnify"
              clearable
              @keyup.enter.native=""
              @select="">
            <template #empty>搜索内容</template>
          </b-autocomplete>
        </b-field>
      </b-navbar-item>
      <b-navbar-item tag="div">
        <el-switch
            v-model="useThemeStore().isDark"
            inline-prompt
            active-text="黑夜"
            size="small"
            inactive-text="白天"
            @change="toggle"
        />
      </b-navbar-item>

      <b-navbar-dropdown
          v-if="useUserStore().token!=null&&useUserStore().token.trim().length>0"
          :label="useUserStore().userInfo.username"
          class="mr-6"
      >
        <b-navbar-item href="#">
          个人中心
        </b-navbar-item>
        <b-navbar-item @click="logout">
          退出登录
        </b-navbar-item>
      </b-navbar-dropdown>

      <b-navbar-item v-else tag="div">
        <b-button tag="router-link" :to="{path:'/register'}" class="is-primary">
          <strong>注册</strong>
        </b-button>

        <b-button tag="router-link" :to="{path:'/login'}" class="ml-2 is-light">
          登录
        </b-button>
      </b-navbar-item>
    </template>
  </b-navbar>
</template>

<style scoped lang="less">

</style>