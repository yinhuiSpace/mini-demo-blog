<script setup lang="ts">

import {ref} from "vue";

import {
  search,
  tableData,
  label,
  list,
  isAdd,
  add,
  userRegister,
  loading,
  rules,
  formInstance
} from "../../api/user.ts";

list()
</script>

<template>
<el-card>
  {{tableData}}
  <el-row :gutter="20">
    <el-col :span="7">
      <el-input placeholder="搜索框" clearable v-model="search.nickname"/>
    </el-col>

    <el-button type="primary" icon="Search" @click="list">
      搜索
    </el-button>

    {{isAdd}}
    <el-button type="success" icon="CirclePlus" @click="isAdd = true">
      添加用户
    </el-button>
  </el-row>

  <el-table :data="tableData" stripe style="width: 100%;margin-top: 30px;">
    <el-table-column v-for="(item,index) in label" :key="index" :prop="item.prop" :label="item.label" :width="item.width" :fixed="item.fixed">
      <template #default="scope">
        <div v-if="item.prop === 'action'">
          <el-button type="primary" icon="Edit" circle />
          <el-button type="danger" icon="Delete" circle />
        </div>

        <div v-else-if="item.prop === 'status'">
          <el-switch v-model="scope.row.status" active-value="1" inactive-value="0"/>
        </div>

        <div v-else-if="item.prop === 'gender'">
          <span>{{scope.row.gender === '0'?'男':'女'}}</span>
        </div>
      </template>
    </el-table-column>

  </el-table>

  <el-dialog
      v-model="isAdd"
      title="新增用户"
      width="500"
      align-center
  >
    {{userRegister}}
    <div style="flex: 45;display: flex;align-items: center;justify-content: center">
      <el-form ref="formInstance" :model="userRegister" style="width: 80%" :rules="rules" v-loading="loading">
        <el-form-item prop="username">
          <el-input
              placeholder="请输入账号"
              type="text"
              autocomplete="off"
              v-model="userRegister.username"
              prefix-icon="user"
          />
        </el-form-item>

        <el-form-item prop="nickname">
          <el-input
              placeholder="请输入昵称"
              type="text"
              autocomplete="off"
              v-model="userRegister.nickname"
              prefix-icon="user"
          />
        </el-form-item>

        <el-form-item prop="password">
          <el-input
              placeholder="请输入密码"
              type="password"
              autocomplete="off"
              show-password
              v-model="userRegister.password"
              prefix-icon="lock"
          />
        </el-form-item>

        <el-form-item prop="username">
          <el-input
              placeholder="请输入邮箱"
              type="text"
              autocomplete="off"
              v-model="userRegister.email"
              prefix-icon="user"
          />
        </el-form-item>

        <el-form-item prop="username">
          <el-input
              placeholder="请输入电话"
              type="text"
              autocomplete="off"
              v-model="userRegister.phone"
              prefix-icon="user"
          />
        </el-form-item>

        <el-form-item prop="gender">
          <el-radio-group v-model="userRegister.gender">
            <el-radio :value="0">男</el-radio>
            <el-radio :value="1">女</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item prop="avatar">
          <el-upload
              class="avatar-uploader"
              action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
          >
            <img v-if="imageUrl" :src="imageUrl" class="avatar" />
            <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
          </el-upload>
        </el-form-item>
      </el-form>
    </div>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="isAdd = false">取消</el-button>
        <el-button type="primary" @click="add(formInstance)">
          保存
        </el-button>
      </div>
    </template>
  </el-dialog>
</el-card>
</template>

<style scoped lang="less">

</style>