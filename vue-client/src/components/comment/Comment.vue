<script setup lang="ts">

import {reactive, ref, toRef} from "vue";
import axiosInstance from "../../api";
import {ElMessage} from "element-plus";
import {ConfigApi, UComment, CommentApi} from "undraw-ui";
import {useUserStore} from "../../stores/user.ts";
import router from "../../router";


const props = defineProps({
  id: String
});

const blogId = toRef(props, 'id');

const comments = ref([])

const getComments = (articleId) => {
  axiosInstance.get('/comment-service/comment/commentList', {
    params: {
      articleId: articleId,
      pageNum: 1,
      pageSize: 10
    }
  }).then((resp) => {
    if (resp.data.isSuccess) {
      comments.value = resp.data.content.rows
    } else {
      ElMessage.error(resp.data.message)
    }
  })
}

getComments(blogId.value)

const onSubmit = () => {
  loading.value = true
  const tmp = commentText.value
  commentText.value=''
  axiosInstance.post('/comment-service/comment',
      {
        articleId: props.id,
        type: 0,
        rootId: -1,
        toCommentId: -1,
        toCommentUserId: -1,
        content: tmp
      }).then((resp) => {
    if (resp.data.isSuccess) {
      ElMessage.success('评论成功')
      getComments(blogId.value)
      loading.value = false
    } else {
      ElMessage.error(resp.data.message)
    }
  })
}

const commentText = ref('')

const loading = ref(false)

const toPerson = (id) => {
  router.push({
    path: `/person/${id}`
  })
}
</script>

<template>
  <article v-if="useUserStore().token!=null&&useUserStore().token.trim().length>0" class="media">
    <div class="media-content">
      <form @submit.prevent="onSubmit">
        <b-field>
          <b-input
              v-model.lazy="commentText"
              type="textarea"
              maxlength="400"
              placeholder="请输入你的评论"
              :disabled="loading"
              @keydown.enter="onSubmit"
          ></b-input>
        </b-field>

        <nav class="level">
          <div class="level-left">
            <b-button
                type="is-primary"
                native-type="submit"
                class="level-item"
                :disabled="loading||commentText.trim().length==0"
            >
              发表
            </b-button>
          </div>
        </nav>
      </form>
    </div>
  </article>

  <div v-else>
    <p class="has-text-grey">
      请先
      <router-link to="/login">
        登录
      </router-link>
      后评论
    </p>
  </div>


  <article v-for="(item,index) in comments" :key="index" class="media">

    <figure class="media-left">
<!--      <img src="https://gzbucket-1326631670.cos.ap-guangzhou.myqcloud.com/1800031117860954112.png" alt="图片">-->
      <div class="block">
        <el-avatar @click="toPerson(item.createBy)"  fit="cover" :size="50" :src="item.avatar" style="border: solid #F0F0F2 2px;cursor:pointer;"/>
      </div>
    </figure>

    <div class="media-content">
      <div class="content">
        <p>
          <strong style="cursor: pointer" @click="toPerson(item.createBy)">
            {{ item.username }}
          </strong>
          <small class="ml-2 has-text-grey is-size-7">
            {{ item.createTime }}
          </small>
          <br/>
          {{ item.content }}
        </p>
      </div>

      <div v-if="item.children.length>0" class="content"
           style="background-color: #d2cdcd;padding: 2vh;border-radius: 5px;">
        <p v-for="(child,i) in item.children" :key="i">
          <span style="color: #3C88C0">{{ child.username }}</span> 回复 <span
            style="color: #3C88C0">{{ child.toCommentUsername }}</span> : {{ child.content }}
        </p>
      </div>
    </div>

  </article>
</template>

<style scoped lang="less">

</style>