<script setup lang="ts">

import {onBeforeMount, ref} from "vue";
import Blogs from "../post/Blogs.vue";
import CardBar from "../card/CardBar.vue";
import {getBoard} from "./home.ts";

const billBoard=ref({
  content:''
})

onBeforeMount(()=>{
  getBoard().then((resp)=>{
    billBoard.value=resp.data.content
  })
})
</script>

<template>
<div>
  <div class="box">
    {{billBoard.content}}
  </div>

  <div class="columns">
    <div class="column is-three-quarters">
      <Blogs/>
    </div>
    <div class="column">
      <CardBar/>
    </div>
  </div>
</div>
</template>

<style scoped lang="less">

</style>