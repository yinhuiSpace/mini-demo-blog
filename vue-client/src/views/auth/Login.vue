<script setup lang="ts">
import {loginForm, formInstance, formRules, loading, login, isCode, confirmLogin, url} from './login.ts'
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
            style="width: 80%;"
        >
          <el-form-item label="用户名" prop="username">
            <el-input
                v-model="loginForm.username"
                autocomplete="off"
                type="text"
                prefix-icon="user"
            />
          </el-form-item>

          <el-form-item label="密码" prop="password">
            <el-input
                v-model="loginForm.password"
                type="password"
                show-password
                autocomplete="off"
                prefix-icon="lock"
            />
          </el-form-item>

          <el-form-item>
            <el-button
                type="primary"
                @click="confirmLogin(formInstance)"
                plain
            >
              登录
            </el-button>

            <router-link to="/register">
              <el-button
                  type="danger"
                  style="margin-left: 20px;"
                  plain>
                注册
              </el-button>
            </router-link>
          </el-form-item>
        </el-form>

        <el-dialog
            v-model="isCode"
            title="验证码"
            width="400"
            align-center>
         <el-form
             :model="loginForm"
             ref="formInstance"
             :rules="formRules"
         >
           <el-form-item
               label="验证码"
               prop="code"
               style="display: flex;"
           >
             <el-input
                 style="flex: 1;"
                 v-model="loginForm.verifyCode"
                 placeholder="请输入验证码"
             ></el-input>
<!--             <img :src="url" style="flex:1;margin-left: 20px;" alt=""/>-->
             <el-image :src="url" fit="fill" style="flex: 1;height: 40px;"/>
           </el-form-item>

           <el-button type="primary" @click="login(formInstance)">
             确定
           </el-button>
         </el-form>
        </el-dialog>

      </el-card>
    </div>
  </div>
</template>

<style scoped lang="less">

</style>