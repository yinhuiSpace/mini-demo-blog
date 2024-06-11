<script setup>

import Header from "./components/layout/Header.vue";
import Footer from "./components/layout/Footer.vue";
</script>

<template>
  <el-header class="">
    <Header/>
  </el-header>
  <el-main class="container " style="min-height: 70vh;">
    <router-view></router-view>
  </el-main>

  <div>
    <Footer/>
  </div>
</template>

<style lang="less" scoped>

</style>