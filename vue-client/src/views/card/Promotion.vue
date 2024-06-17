<script setup lang="ts">
import {getPromotions} from "./ts/promotion.ts";
import {onBeforeMount, ref} from "vue";

const promotions=ref([])

onBeforeMount(()=>{
  getPromotions().then((resp)=>{
    promotions.value=resp.data.content
  })
})
</script>

<template>
  <el-card class="box-card" shadow="never">
    <template #header>
      <div style="font-size: 20px">
        友链
      </div>
    </template>
    <li v-for="(item,index) in promotions" :key="index" class="block">
      <a :href="item.address" target="_blank">{{item.name}}</a>
    </li>
  </el-card>
</template>

<style scoped lang="less">

</style>