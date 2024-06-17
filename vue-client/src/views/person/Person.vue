<script setup lang="ts">

import axiosInstance from "../../api";
import {ElMessage} from "element-plus";
import {useUserStore} from "../../stores/user.ts";
import {ref} from "vue";
import {Plus} from "@element-plus/icons-vue";

const person = ref({})

const loading=ref(false)

const isUpdate=ref(false)


const getPerson = () => {
  axiosInstance.get('/user-service/info', {
    params: {
      id: useUserStore().userInfo.id
    }
  })
      .then((resp) => {
        if (resp.data.isSuccess) {
          person.value = resp.data.content
          user.value.id=person.value.id
        } else {
          ElMessage.error(resp.data.message)
        }
      })
}

getPerson()

const url=ref("")

const handleUpload=(item)=>{
  loading.value=true
  const formData = new FormData();
  formData.append("img",item.file)
  axiosInstance.post("/user-service/user/upload",formData)
      .then((resp)=>{
        if (resp.data.isSuccess){
          url.value = resp.data.content;
          user.value.avatar=resp.data.content
          ElMessage.success("上传成功")
          loading.value=false
        }else {
          ElMessage.error(resp.data.message)
          loading.value=false
        }
      })
}

const header=ref({ "Content-Type": "multipart/form-data" })

const user=ref({
  avatar:"",
  gender:"",
  id:""
})

const update=()=>{
  loading.value = true
  axiosInstance.put('/user-service/user/update', user.value)
      .then((resp) => {
        if (resp.data.isSuccess) {
          loading.value = false
          ElMessage.success("更新成功")
          isUpdate.value = false
          getPerson()
        } else {
          ElMessage.error(resp.data.message)
          loading.value = false
        }
      })
}
</script>

<template>
  <div class="columns" style="margin-top: 10px;">
    <el-card class="column" style="max-width: 60vw; margin: auto; ">
      <div class="el-header has-text-centered">
        <!--        <img style="width: 60px;border-radius: 50%;" alt="头像"-->
        <!--             src="https://gzbucket-1326631670.cos.ap-guangzhou.myqcloud.com/1800031117860954112.png"/>-->

        <div class="block">
          <el-avatar fit="cover" :size="60"
                     :src="person.avatar"
                     style="border: solid #F0F0F2 2px"/>
        </div>
      </div>

      <div class="el-header has-text-centered has-text-weight-bold">
        <p class="mt-3">
          {{ person.username }}
        </p>
      </div>

      <div>
        <p class="has-text-centered">
          <div class="">
            <span>
              文章：{{ person.articles }}
            </span>

            <el-divider direction="vertical"/>

            <span>
              粉丝：{{ person.fans }}
            </span>

            <el-divider direction="vertical"/>

            <span>
              关注：{{ person.followers }}
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
          入驻时间：{{ person.createTime }}
        </p>

        <p class="content">
          性别：{{ person.gender == 0 ? '男' : '女' }}
        </p>

        <el-button type="primary" icon="Edit" circle @click="isUpdate=true"/>
      </div>
    </el-card>
  </div>

  <el-dialog
      v-model="isUpdate"
      title="修改信息"
      width="500"
      align-center
  >
    <div style="flex: 45;display: flex;align-items: center;justify-content: center">
      <el-form ref="formInstance" :model="user" style="width: 80%" :rules="rules" v-loading="loading">

        <el-form-item prop="gender" label="性别">
          <el-radio-group v-model="user.gender">
            <el-radio :value="0">男</el-radio>
            <el-radio :value="1">女</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item prop="avatar" label="头像">
          <el-upload
              class="avatar-uploader"
              action=""
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :http-request="handleUpload"
              :headers="header"
          >
            <img v-if="url" :src="url" class="avatar" alt=""/>
            <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
          </el-upload>
        </el-form-item>
      </el-form>
    </div>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="isUpdate = false">取消</el-button>
        <el-button type="primary" @click="update">
          保存
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style lang="less">
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>