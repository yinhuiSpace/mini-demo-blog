<script setup lang="ts">
import {Tip} from "./ts/tip.ts";
import {onBeforeMount, ref} from "vue";
import {getTip} from "./ts/tip.ts";

const tip=ref<Tip>({
  content:'',
  author:''
})

onBeforeMount(()=>{
  getTip().then((resp)=>{
    tip.value=resp.data.content
  })
})
</script>

<template>
  <el-card class="box-card" shadow="never">
    <template #header>
      <div style="font-size: 20px">
        社区寄语
      </div>
    </template>
    <div >
      <div class="has-text-left block">
        {{tip.content}}
      </div>

      <div class="has-text-right mt-5 block">
        - {{tip.author}}
      </div>
    </div>
  </el-card>
</template>

<style scoped lang="less">

</style>