<script setup lang="ts">
import {formRules, userRegister} from './register.ts'
import {formInstance} from "./register.ts";
import {ruleForm} from "./register.ts";
import {loading} from "./register.ts";
</script>

<template>
  <div class="columns py-6">
    <div class="column is-half is-offset-one-quarter">
      <el-card shadow="never">
        <div class="el-header has-text-centered has-text-weight-bold">
          注册账号
        </div>

        <div>
          <el-form
              ref="formInstance"
              :model="ruleForm"
              :rules="formRules"
              label-width="100px"
              class=""
              status-icon
              v-loading="loading"
          >
            <el-form-item label="账号名" prop="username">
              <el-input
                  v-model="ruleForm.username"
                  autocomplete="off"
              />
            </el-form-item>

            <el-form-item label="密码" prop="password">
              <el-input
                  v-model="ruleForm.password"
                  type="password"
                  show-password
                  autocomplete="off"
              />
            </el-form-item>

            <el-form-item label="确认密码" prop="checkPass">
              <el-input
                  v-model="ruleForm.checkPass"
                  type="password"
                  show-password
                  autocomplete="off"
              />
            </el-form-item>

            <el-form-item label="邮箱" prop="email">
              <el-input
                  v-model="ruleForm.email"
                  autocomplete="off"
              />
            </el-form-item>

            <el-form-item label="手机号" prop="phone">
              <el-input
                  v-model="ruleForm.phone"
                  autocomplete="off"
              />
            </el-form-item>

            <el-form-item>
              <el-button
                type="primary"
                @click="userRegister(formInstance)"
              >
                立即注册
              </el-button>
            </el-form-item>

          </el-form>
        </div>
      </el-card>
    </div>

  </div>
</template>

<style scoped lang="less">

</style>