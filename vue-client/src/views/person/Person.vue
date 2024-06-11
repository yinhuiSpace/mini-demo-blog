<script setup lang="ts">

import axiosInstance from "../../api";
import {ElMessage} from "element-plus";
import {useUserStore} from "../../stores/user.ts";
import {ref} from "vue";

const person = ref({})

const getPerson = () => {
  axiosInstance.get('/user-service/info', {
    params: {
      id: useUserStore().userInfo.id
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

getPerson()
</script>

<template>
  <div class="columns" >
    <el-card class="column" style="max-width: 60vw; margin: auto; ">
      <div class="el-header has-text-centered">
        <img style="width: 60px;border-radius: 50%;" alt="头像"
             src="https://gzbucket-1326631670.cos.ap-guangzhou.myqcloud.com/1800031117860954112.png"/>
      </div>

      <div class="el-header has-text-centered has-text-weight-bold">
        <p class="mt-3">
          {{person.username}}
        </p>
      </div>

      <div>
        <p class="has-text-centered">
          <div class="">
            <span>
              文章：{{person.articles}}
            </span>

            <el-divider direction="vertical"/>

            <span>
              粉丝：{{person.fans}}
            </span>

            <el-divider direction="vertical"/>

            <span>
              关注：{{person.followers}}
            </span>

          </div>
        </p>
        <p class="content">
          积分：
          <code>
            100
          </code>
        </p>

        <p class="content">
          入驻时间：{{person.createTime}}
        </p>

        <p class="content">
          性别：{{person.gender==0?'男':'女'}}
        </p>
      </div>
    </el-card>
  </div>
</template>

<style scoped lang="less">

</style>