<script setup lang="ts">

import BackTop from "../backTop/BackTop.vue";
</script>

<template>
  <footer class="footer" style="background-color:#EDEDED">
    <div class="container" style="display: flex">
      <div class="" style="flex:1;">
        <h1>
          关于本社区：
        </h1>
        <p>
          本社区于2024-06-17上线运行
        </p>
        <p>
          本社区意在以一种更加形象和简洁的方式，达成更高效更可靠的学习
        </p>
        <p>
          本社区目前以二级分类的形式管理资源，尽量开设更全面的板块
        </p>
        <p>
          本社区欢迎各位在此发文
        </p>
      </div>

      <div style="flex: 1">
        <p>
          欢迎联系我们：
        </p>
        <p>
          心流社区官方群：429759913
        </p>
      </div>
    </div>
    <BackTop/>
  </footer>
</template>

<style scoped lang="less">
p{
  color: #4C4F57;
  line-height: 30px;
}
</style>