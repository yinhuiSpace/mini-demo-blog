<script setup lang="ts">
import {ref} from "vue";
import axiosInstance from "../../api";
import {ElMessage, FormInstance} from "element-plus";
import {useUserStore} from "../../stores/user.ts";
import router from "../../router";

const loginForm = ref({
  username: '',
  password: '',
  validCode: ''
})

const loading=ref(false)

const rules = {
  username: [
    {
      required: true,
      message: "账号名不能为空",
      trigger: 'change'
    }
  ],
  password: [
    {
      required: true,
      message: "密码不能为空",
      trigger: 'change'
    }
  ],
  validCode: [
    {
      required: true,
      message: "验证码不能为空",
      trigger: "change"
    }
  ]
}

const login = (formInstance) => {
  loading.value=true
  useUserStore().removeLogin()
  formInstance.validate((valid) => {
    if (valid) {
      axiosInstance.post('/auth-service/manager/login', loginForm.value)
          .then((resp) => {
            if (resp.data.isSuccess) {
              useUserStore().token = resp.data.content.token
              useUserStore().userInfo = resp.data.content.infoVo
              ElMessage.success("登录成功")
              loading.value=false
              // loginForm.value={
              //   username: '',
              //   password: '',
              //   validCode: ''
              // }
              router.replace({
                path:'/'
              })
            } else {
              ElMessage.error(resp.data.message)
              loading.value=false
            }
          })
    } else {
      ElMessage.error("请按要求填写信息")
      loading.value=false
    }
  })
}

const formInstance = ref<FormInstance>()
</script>

<template>
  <div style="height: 100vh;display: flex;align-items: center;justify-content: center;background-color: #0f9876">
    <div style="display: flex;background-color: white;width: 55%;height: 45vh;border-radius: 5px;overflow: hidden;">
      <div style="flex: 55;">
        <img src="../../assets/llogin.png" alt="" style="height: 100%"/>
      </div>
      <div style="flex: 45;display: flex;align-items: center;justify-content: center">
        <el-form ref="formInstance" :model="loginForm" style="width: 80%" :rules="rules" v-loading="loading">
          <div style="font-size: 20px;font-weight: bold;text-align: center">欢迎登录后台管理系统</div>
          <el-form-item prop="username">
            <el-input
                placeholder="请输入账号"
                type="text"
                autocomplete="off"
                v-model="loginForm.username"
                prefix-icon="user"
            />
          </el-form-item>

          <el-form-item prop="password">
            <el-input
                placeholder="请输入密码"
                type="password"
                autocomplete="off"
                show-password
                v-model="loginForm.password"
                prefix-icon="lock"
            />
          </el-form-item>

          <el-form-item prop="validCode">
            <div style="display: flex;width: 100%;">
              <el-input
                  style="flex: 1"
                  v-model="loginForm.validCode"
                  type="text"
                  prefix-icon="CircleCheck"
              />
              <div style="flex: 1"></div>
            </div>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" style="width: 100%;" @click="login(formInstance)">
              登 录
            </el-button>
          </el-form-item>
          <div style="display: flex;">
            <div style="flex: 1">
              还没有账号?请
              <span style="color: #0f9876;cursor: pointer;">
              注册
            </span>
            </div>
            <div style="flex: 1;text-align: right;">
            <span style="color: #0f9876;cursor: pointer;">
              忘记密码
            </span>
            </div>
          </div>
        </el-form>
      </div>
    </div>
  </div>
</template>

<style scoped lang="less">

</style>