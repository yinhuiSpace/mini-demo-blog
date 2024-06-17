<script setup lang="ts">

import {useRoute} from "vue-router";
import axiosInstance from "../../api";
import {ElMessage} from "element-plus";
import {ref} from "vue";

const route = useRoute();

const person = ref({})

const getPerson = (id) => {
  axiosInstance.get('/user-service/author', {
    params: {
      id: id
    }
  })
      .then((resp) => {
        if (resp.data.isSuccess) {
          person.value = resp.data.content
        } else {
          ElMessage.error(resp.data.message)
        }
      })
}

getPerson(route.params.id)
</script>

<template>
  <div class="member">
    <div class="columns">
      <div class="column is-one-quarter">
        <el-card shadow="never">
          <div class="el-header has-text-centered">
            <div class="block">
              <el-avatar  fit="cover" :size="60" :src="person.avatar" style="border: solid #F0F0F2 2px"/>
            </div>
          </div>

          <div class="el-header has-text-centered has-text-weight-bold">
            <p class="mt-3">
              {{person.username}}
            </p>
          </div>

          <div>
            <p class="content">
              积分：
              <code>
                100
              </code>
            </p>

            <p class="content">
              入住时间：{{person.createTime}}
            </p>

            <p class="content">
              简介：描述
            </p>
          </div>
        </el-card>
      </div>

      <div class="column">
        <el-card class="box" shadow="never">
          <div class="el-header">
            文章
          </div>
        </el-card>
      </div>
    </div>
  </div>
</template>

<style scoped lang="less">

</style>