<script setup lang="ts">

import {Burger} from "@element-plus/icons-vue";
import {computed, onBeforeMount, onMounted, ref, toRef, toRefs} from "vue";
import axiosInstance from "../../api";
import {ElMessage} from "element-plus";
import router from "../../router";

const props = defineProps({
  id:String
});


const author=ref({})

const getAuthor=(id)=>{
  console.log(id);
  axiosInstance.get('/user-service/author',
      {
        params:{
          id:id
        }
      })
      .then((resp)=>{
        if (resp.data.isSuccess){
          author.value=resp.data.content
          console.log(author.value.follow);
        }else {
          ElMessage.error(resp.data.message)
        }
      })
}


const loading=ref(false)


const follow=()=>{
  loading.value=true
  axiosInstance.post(`/user-service/user/addFollow/${props.id}`)
      .then((resp)=>{
        if (resp.data.isSuccess){
          ElMessage.success('关注成功')
          author.value.follow=true
          author.value.fans=resp.data.content
          loading.value=false
        }else {
          ElMessage.error(resp.data.message)
          loading.value=false
        }
      })
}

const unFollow=()=>{
  axiosInstance.delete(`/user-service/user/unFollow/${props.id}`)
      .then((resp)=>{
        if (resp.data.isSuccess){
          ElMessage.success('取关成功')
          author.value.fans=resp.data.content
          author.value.follow=false
          loading.value=false
        }else {
          ElMessage.error(resp.data.message)
          loading.value=false
        }
      })
}

const id = toRef(props,'id');
console.log(id.value);
getAuthor(id.value)

const toPerson = (id) => {
  router.push({
    path: `/person/${id}`
  })
}
</script>

<template>
<section id="author" v-loading="loading">
  <el-card class="" shadow="never">
    <div class="el-header">
      <span class="has-text-weight-bold">
        关于作者
      </span>
    </div>

    <div class="has-text-centered">
      <div class="block">
        <el-avatar  @click="toPerson(id)"  fit="cover" :size="60" :src="author.avatar" style="border: solid #F0F0F2 2px;cursor:pointer;"/>
      </div>
      <p class="is-size-5 mb-5">
        <span class="is-size-7 has-text-grey" style="cursor: pointer" @click="toPerson(id)">
          {{author.nickname}}
        </span>
      </p>
      <div class="columns is-mobile">
        <div class="column is-half">
          <code>
            0
          </code>
          <p>
            文章
          </p>
        </div>

        <div class="column is-half">
          <code>
            {{author.fans}}
          </code>
          <p>
            粉丝
          </p>
        </div>
      </div>

      <div>
        <button v-if="author.follow" class="button is-success is-fullwidth" @click="unFollow">
          已关注
        </button>

        <button v-else class="button is-link is-fullwidth" @click="follow">
          关注
        </button>
      </div>
    </div>
  </el-card>
</section>
</template>

<style scoped lang="less">

</style>