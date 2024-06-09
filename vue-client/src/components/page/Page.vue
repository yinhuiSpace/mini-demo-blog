<script setup lang="ts">

import {ref} from "vue";

defineProps({
  total:Number,
  handlePageNum:Function,
  handlePageSize:Function
})
</script>

<template>
<div>
  <el-pagination
      layout="total, sizes, prev, pager, next, jumper"
      small
      background
      :page-sizes="[2,5,10,20]"
      :total="total"
      @current-change="handlePageNum"
      @size-change="handlePageSize"
  />
</div>
</template>

<style scoped lang="less">

</style>