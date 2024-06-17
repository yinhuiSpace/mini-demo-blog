<script setup lang="ts">

import {useUserStore} from "../../stores/user.ts";
</script>

<template>
<el-card class="box-card" shadow="never">
  <template #header>
    <div style="font-size: 20px">
      发文
    </div>
  </template>
  <div v-if="useUserStore().token!=null&&useUserStore().token.trim().length>0" class="has-text-centered">
<!--    <b-button type="is-danger" tag="router-link" :to="{path:'/createBlog'}" outlined>-->
<!--      发布文章-->
<!--    </b-button>-->

    <router-link to="/createBlog">
      <el-button type="primary" plain icon="circlePlus">发布文章</el-button>
    </router-link>
  </div>

  <div v-else class="has-text-centered">
    <router-link to="/register">
      <el-button type="danger" plain>注册</el-button>
    </router-link>

    <router-link to="/login">
      <el-button type="success" plain style="margin-left: 10px;">登录</el-button>
    </router-link>
  </div>
</el-card>
</template>

<style scoped lang="less">

</style>