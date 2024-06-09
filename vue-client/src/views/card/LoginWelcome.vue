<script setup lang="ts">

import {useUserStore} from "../../stores/user.ts";
</script>

<template>
<el-card class="box-card" shadow="never">
  <div slot="header" class="el-header">
    <span>发帖</span>
  </div>
  <div v-if="useUserStore().token!=null&&useUserStore().token.trim().length>0" class="has-text-centered">
    <b-button type="is-danger" tag="router-link" :to="{path:'/createBlog'}" outlined>
      发布文章
    </b-button>
  </div>

  <div v-else class="has-text-centered">
    <b-button type="is-primary" tag="router-link" :to="{path:'/register'}" outlined>
      立即注册
    </b-button>

    <b-button type="is-danger" tag="router-link" :to="{path:'/login'}" outlined class="ml-2">
      登录
    </b-button>
  </div>
</el-card>
</template>

<style scoped lang="less">

</style>