<script setup lang="ts">
import {loginForm,formInstance,formRules,loading,login} from './login.ts'
import {codeLoading, disable, getCheckCode, ruleForm, text, userRegister} from "./register.ts";
</script>

<template>
<div class="columns py-6">
  <div class="column is-half is-offset-one-quarter">
    <el-card shadow="never">

      <div class="el-header has-text-centered has-text-weight-bold">
        登录
      </div>

      <el-form
        :model="loginForm"
        ref="formInstance"
        :rules="formRules"
        label-width="100px"
        class=""
        status-icon
        v-loading="loading"
      >
        <el-form-item label="账号名" prop="username">
          <el-input
              v-model="loginForm.username"
              autocomplete="off"
          />
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input
              v-model="loginForm.password"
              type="password"
              show-password
              autocomplete="off"
          />
        </el-form-item>

        <el-form-item label="验证码" prop="code" style="display: flex">
          <el-input style="flex: 1;" v-model="loginForm.code" placeholder="请输入验证码" minlength="6" maxlength="6"></el-input>
          <img style="flex:1;margin-left: 20px;" alt=""/>
        </el-form-item>

        <el-form-item>
          <el-button
              type="primary"
              @click="login(formInstance)"
          >
            登录
          </el-button>

          <b-button type="is-danger" tag="router-link" :to="{path:'/register'}" outlined class="ml-2">
            注册
          </b-button>
        </el-form-item>
      </el-form>

    </el-card>
  </div>
</div>
</template>

<style scoped lang="less">

</style>