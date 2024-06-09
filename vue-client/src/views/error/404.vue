<script setup lang="ts">
import {onBeforeMount, ref} from "vue";
import router from "../../router";

const time=ref({
  duration:5,
  timer:null
})

onBeforeMount(()=>{
time.value.timer=setInterval(
    ()=>{
      time.value.duration--
      if (time.value.duration==0){
        clearInterval(time.value.timer)
        router.push({
          path:'/'
        })
      }
    },
    1000
)
})
</script>

<template>
<div class="columns mt-6" style="height: 60vh;">
  <div class="column mt-6">
    <p class="content">
      页面丢失
    </p>
    <p class="content subtitle mt-6">
      您所查找的页面不存在，{{time.duration}}秒后，将跳至首页
    </p>
  </div>
</div>
</template>

<style scoped lang="less">

</style>