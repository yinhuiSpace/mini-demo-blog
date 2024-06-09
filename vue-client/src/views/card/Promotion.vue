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
    <div class="el-header">
      <span>推广</span>
    </div>
    <div>
      <p v-for="(item,index) in promotions" :key="index" class="block">
        <a :href="item.address" target="_blank">{{item.name}}</a>
      </p>
    </div>
  </el-card>
</template>

<style scoped lang="less">

</style>