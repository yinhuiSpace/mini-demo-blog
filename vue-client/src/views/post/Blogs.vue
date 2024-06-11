<script setup lang="ts">

import {useUserStore} from "../../stores/user.ts";
import {getAllBlogs} from "./blogs.ts";
import {onBeforeMount, ref} from "vue";
import Page from "../../components/page/Page.vue";
import router from "../../router";

const blogs = ref([])

onBeforeMount(() => {
  getAllBlogs(page.value).then((resp) => {
    if (resp.data.isSuccess) {
      blogs.value = resp.data.content.rows
      total.value=resp.data.content.total
    }
  })
})

const total=ref(0)

const page=ref({
  pageNum:1,
  pageSize:10
})
const handlePageNum=(pageNum)=>{
  page.value.pageNum=pageNum
  getAllBlogs(page.value).then((resp) => {
    if (resp.data.isSuccess) {
      blogs.value = resp.data.content.rows
      total.value=resp.data.content.total
    }
  })
}

const handlePageSize=(pageSize)=>{
  page.value.pageSize=pageSize
  getAllBlogs(page.value).then((resp) => {
    if (resp.data.isSuccess) {
      blogs.value = resp.data.content.rows
      total.value=resp.data.content.total
    }
  })
}

const activeTab=ref('1')

const toPerson=(id)=>{
  router.push({
    path:`/person/${id}`
  })
}

const toDetail=(id)=>{
  router.push({
    path:`/details/${id}`
  })
  // router.push({
  //   name:'details',
  //   params:{
  //     id:id
  //   }
  // })
}
</script>

<template>
  <div>
    <el-card shadow="never">
      <div class="el-header">
        帖子列表
      </div>

      <div class="is-clearfix">
        <el-tabs v-model="activeTab">
          <el-tab-pane label="最新文章" name="1">
            <article v-for="(item,index) in blogs" :key="index" class="media">
              <div class="media-left">
                <figure class="image is-48x48">
                  <img
                      src="https://gzbucket-1326631670.cos.ap-guangzhou.myqcloud.com/1800031117860954112.png"
                      alt="缩略图"
                  >

                </figure>
              </div>

              <div class="media-content">
                <div class=""  @click="toDetail(item.id)">
                  <p class="has-ellipsis">
                    <el-tooltip class="el-form-item" effect="dark" placement="top" :content="item.title">
                      <!--                    <router-link :to="{name:'blog-detail',params:{id:item.id}}">-->
                                            <span class="is-size-6">
                                              {{item.title}}
                                            </span>
                      <!--                    </router-link>-->
                    </el-tooltip>
                  </p>

                  <p class="has-text-grey is-size-7 mt-1">
                    {{ item.summary }}
                  </p>
                </div>

                <nav class="level has-text-grey is-mobile is-size-7 mt-2">
                  <div class="level-left">
                    <div class="level-left">
                      <span class="mr-1">
                        <el-link :underline="false" @click="toPerson(item.createBy)">
                          {{item.authorName}}
                        </el-link>
                        发布于{{ item.createTime }}
                      </span>

                      <span class="tag is-hidden-mobile is-success is-light mr-1">
                        {{item.categoryName}}
                      </span>

                      <span class="is-hidden-mobile">
                        浏览：{{item.viewCount}}
                      </span>
                    </div>


                  </div>
                </nav>
              </div>
            </article>
          </el-tab-pane>
        </el-tabs>
      </div>
    </el-card>

    <div class="column">
      <Page
          :total="total"
          :handlePageNum="handlePageNum"
          :handlePageSize="handlePageSize"
      />
    </div>
  </div>
</template>

<style scoped lang="less">

</style>