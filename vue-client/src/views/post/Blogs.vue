<script setup lang="ts">

import {useUserStore} from "../../stores/user.ts";
import {getAllBlogs} from "./blogs.ts";
import {onBeforeMount, onMounted, ref} from "vue";
import Page from "../../components/page/Page.vue";
import router from "../../router";
import axiosInstance from "../../api";
import {ElMessage} from "element-plus";

const blogs = ref([])

onBeforeMount(() => {
  getAllBlogs(page.value).then((resp) => {
    if (resp.data.isSuccess) {
      blogs.value = resp.data.content.rows
      total.value = resp.data.content.total
    }
  })
})

const total = ref(0)

const page = ref({
  pageNum: 1,
  pageSize: 10,
  categoryId: ""
})
const handlePageNum = (pageNum) => {
  page.value.pageNum = pageNum
  getAllBlogs(page.value).then((resp) => {
    if (resp.data.isSuccess) {
      blogs.value = resp.data.content.rows
      total.value = resp.data.content.total
    }
  })
}

const handlePageSize = (pageSize) => {
  page.value.pageSize = pageSize
  getAllBlogs(page.value).then((resp) => {
    if (resp.data.isSuccess) {
      blogs.value = resp.data.content.rows
      total.value = resp.data.content.total
    }
  })
}

const activeTab = ref('1')

const toPerson = (id) => {
  router.push({
    path: `/person/${id}`
  })
}

const toDetail = (id) => {
  router.push({
    path: `/details/${id}`
  })
  // router.push({
  //   name:'details',
  //   params:{
  //     id:id
  //   }
  // })
}

const firstCategories = ref([])

const getFirst = () => {
  axiosInstance.get("/blog-service/category/getFirst")
      .then((resp) => {
        if (resp.data.isSuccess) {
          firstCategories.value = resp.data.content
          parentId.value = firstCategories.value[0].id
          page.value.categoryId = parentId.value
          getSecond()
          getBlogs()
        } else {
          ElMessage.error(resp.data.message)
        }
      })
}

onMounted(() => {
  getFirst()
})

const parentId = ref("")

const secondCategories = ref([])

const getSecond = () => {
  axiosInstance.get("/blog-service/category/getSecond", {
    params: {
      parentId: parentId.value
    }
  })
      .then((resp) => {
        if (resp.data.isSuccess) {
          secondCategories.value = resp.data.content
          page.value.categoryId = parentId.value
          getBlogs()
        } else {
          ElMessage.error(resp.data.message)
        }
      })
}


const getBlogs = () => {
  axiosInstance.get('/blog-service/article/articleList', {
    params: page.value
  }).then((resp) => {
    if (resp.data.isSuccess) {
      blogs.value = resp.data.content.rows
      total.value = resp.data.content.total
    } else {
      ElMessage.error(resp.data.message)
    }
  })
}
</script>

<template>
  <div style="margin-top: 10px;">
    <el-card shadow="never">
      <template #header>
        <div style="font-size: 20px">
          首页
        </div>
      </template>

      <div class="is-clearfix">
        <el-tabs v-model="parentId" @click="getSecond">
          <el-tab-pane v-for="(item,index) in firstCategories" :key="index" :label="item.name" :name="item.id"/>
        </el-tabs>

        <el-tabs v-model="page.categoryId" @click="getBlogs">
          <el-tab-pane v-for="(e,i) in secondCategories" :key="i" :label="e.name" :name="e.id"/>
        </el-tabs>

        <article v-if="blogs.length>0" v-for="(item,index) in blogs" :key="index" class="media"
                 style="margin-top: 20px;">
          <div class="media-left">
            <figure class="">
              <div class="block">
                <el-avatar @click="toDetail(item.id)" shape="square" fit="cover" :size="60" :src="item.thumbnail"
                           style="border: solid #F0F0F2 2px;cursor:pointer;"/>
              </div>
            </figure>
          </div>

          <div class="media-content">
            <div class="" @click="toDetail(item.id)">
              <el-link :underline="false" class="has-ellipsis">
                <el-tooltip class="el-form-item" effect="dark" placement="top" :content="item.title">
                  <!--                    <router-link :to="{name:'blog-detail',params:{id:item.id}}">-->
                  <span class="is-size-6">
                                                          {{ item.title }}
                                                        </span>
                  <!--                    </router-link>-->
                </el-tooltip>
              </el-link>

              <p style="cursor: pointer" class="has-text-grey is-size-7 mt-1">
                {{ item.summary }}
              </p>
            </div>

            <nav class="level has-text-grey is-mobile is-size-7 mt-2">
              <div class="level-left">
                <div class="level-left">
                                  <span class="mr-1">
                                    <el-link :underline="false" @click="toPerson(item.createBy)">
                                      {{ item.authorName }}
                                    </el-link>
                                    发布于{{ item.createTime }}
                                  </span>

                  <span class="tag is-hidden-mobile is-success is-light mr-1">
                                    {{ item.categoryName }}
                                  </span>

                  <span class="is-hidden-mobile">
                                    浏览：{{ item.viewCount }}
                                  </span>
                </div>


              </div>
            </nav>
          </div>
        </article>

        <p v-else>
          暂时还没有文章哦~期待你的博客
        </p>
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