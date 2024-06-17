<script setup lang="ts">

import axiosInstance from "../../api";
import {ElMessage, FormInstance} from "element-plus";
import {ref} from "vue";
import {isArray} from "undraw-ui";

const board=ref({})
const add=(formInstance)=>{
 formInstance.validate((valid)=>{
   if (valid){
     axiosInstance.post("/comment-service/board",board.value)
         .then((resp)=>{
           if (resp.data.isSuccess){
             ElMessage.success("发布成功")
             isAdd.value=false
             board.value={}
           }else {
             ElMessage.error(resp.data.message)
           }
         })
   }else {
     ElMessage.error("请填写完整信息")
   }
 })
}

const rules={
  content:[
    {
      required:true,
      message:"公告内容不能为空",
      trigger:"blur"
    },
    {
      min:2,
      max:500,
      trigger: "blur",
      message: "字数只能位于2-500字之间"
    }
  ]
}

const isAdd=ref(false)

const formInstance=ref<FormInstance>()
</script>

<template>
  <el-button type="success" icon="CirclePlus" @click="isAdd = true">
    发布公告
  </el-button>

  <el-dialog
      v-model="isAdd"
      title="新增分类"
      width="500"
      align-center
  >
    <div style="flex: 45;display: flex;align-items: center;justify-content: center">
      <el-form ref="formInstance" :model="board" style="width: 80%" :rules="rules" v-loading="loading">


        <el-form-item prop="content" label="公告内容">
          <el-input
              placeholder="公告"
              type="textarea"
              autocomplete="off"
              v-model="board.content"
              prefix-icon="lock"
              minlength="2"
              maxlength="500"
          />
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
</template>

<style scoped lang="less">

</style>