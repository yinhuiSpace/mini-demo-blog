<script setup lang="ts">

import {reactive, ref, toRef} from "vue";
import axiosInstance from "../../api";
import {ElMessage} from "element-plus";
import {ConfigApi, UComment,CommentApi} from "undraw-ui";



const props = defineProps({
  id:String
});

const blogId = toRef(props,'id');

const comments=ref([])

const getComments=(articleId)=>{
  axiosInstance.get('/comment-service/comment/commentList',{
    params:{
      articleId:articleId,
      pageNum:1,
      pageSize:10
    }
  }).then((resp)=>{
    if (resp.data.isSuccess){
      comments.value=resp.data.content.rows
    }else {
      ElMessage.error(resp.data.message)
    }
  })
}

getComments(blogId.value)
</script>

<template>
<article v-for="(item,index) in comments" :key="index" class="media">

  <figure class="media-left image is-48x48">
    <img src="https://gzbucket-1326631670.cos.ap-guangzhou.myqcloud.com/1800031117860954112.png" alt="图片">
  </figure>

  <div class="media-content">
    <div class="content">
      <p>
        <strong>
          {{item.username}}
        </strong>
        <small class="ml-2 has-text-grey is-size-7">
          {{item.createTimeStr}}
        </small>
        <br/>
        {{item.content}}
      </p>
    </div>

    <div  v-if="item.children.length>0" class="content" style="background-color: #d2cdcd;padding: 2vh;border-radius: 5px;">
      <p v-for="(child,i) in item.children" :key="i">
        <span style="color: #3C88C0">{{child.username}}</span> 回复 <span style="color: #3C88C0">{{child.toCommentUsername}}</span> : {{child.content}}
      </p>
    </div>
  </div>

</article>
</template>

<style scoped lang="less">

</style>