<script setup lang="ts">

import axiosInstance from "../../api";
import {onMounted, ref} from "vue";
import {useRoute} from "vue-router";
import Vditor from "vditor";
import 'vditor/dist/index.css';
import {ElMessage} from "element-plus";
import router from "../../router";
import Author from "../person/Author.vue";
import Recommend from "../../components/recommend/Recommend.vue";
import Comment from "../../components/comment/Comment.vue";

const blog = ref({})

const route = useRoute();

const getDetail = () => {
  axiosInstance.get(`/blog-service/article/details/${route.params.id}`)
      .then((resp) => {
        if (resp.data.isSuccess) {
          blog.value = resp.data.content
          console.log(blog.value.createBy);
          Vditor.preview(document.getElementById('preview'), blog.value.content, {
            hljs: {
              style: 'github'
            }
          })
          getAuthor(blog.value.createBy)

        } else {
          ElMessage.error(resp.data.message)
        }
      })
}

const author = ref({})

const getAuthor = (id) => {
  axiosInstance.get('/user-service/author',
      {
        params: {
          id: id
        }
      })
      .then((resp) => {
        if (resp.data.isSuccess) {
          author.value = resp.data.content
        } else {
          ElMessage.error(resp.data.message)
        }
      })
}


onMounted(() => {
  getDetail()
})
</script>

<template>
  <div class="columns">
    <div class="column is-three-quarters">
      <el-card class="box" shadow="never">
        <div class="el-header has-text-centered">
          <p class="is-size-5 has-text-weight-bold">
            {{ blog.title }}
          </p>

          <div class="has-text-grey is-size-7 mt-3">
          <span>
            时间：{{ blog.createTime }}
          </span>

            <el-divider direction="vertical"/>

            <span>发布者：{{ author.nickname }}</span>

            <el-divider direction="vertical"/>

            <span>浏览量：{{ blog.viewCount }}</span>

          </div>
        </div>

        <div id="preview"/>

        <nav class="level has-text-grey is-size-7 mt-6">
          <div class="level-left">
            <p class="level-item">
              <!--              <b-taglist>-->
              <!--                <b-tag type="is-info is-light mr-1"></b-tag>-->
              <!--              </b-taglist>-->
              <b-tag type="is-info is-light mr-1">
                {{ blog.categoryName }}
              </b-tag>
            </p>
          </div>
        </nav>
      </el-card>

      <Comment
          v-if="blog.id"
          :id="blog.id"
      />
    </div>

    <div class="column" v-if="blog.createBy">
      <Author
          :id="blog.createBy"
      />

      <Recommend/>
    </div>
  </div>
</template>

<style scoped lang="less">

</style>