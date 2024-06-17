<script setup lang="ts">

import Page from "../../components/page/Page.vue";
import 'vditor/dist/index.css';
import {Plus} from "@element-plus/icons-vue";
import {
  blog, confirmUpdate,
  isUpdate,
  label,
  list,
  loading,
  query,
  tableData,
  total,
  updateById
} from "../../api/review.ts"
import Vditor from "vditor";

list()
const handlePageNum=(pageNum)=>{
  query.value.pageNum=pageNum
  list()
}

const handlePageSize=(pageSize)=>{
  query.value.pageSize=pageSize
  list()
}
</script>

<template>
  <el-card>
    <el-row :gutter="20">
      <el-col :span="7">
        <el-input placeholder="搜索框" clearable v-model="query.title" @input="list"/>
      </el-col>

      <el-button type="primary" icon="Search" @click="list">
        搜索
      </el-button>
    </el-row>

    <el-table :data="tableData" stripe style="width: 100%;margin-top: 30px;">
      <el-table-column v-for="(item,index) in label" :key="index" :prop="item.prop" :label="item.label" :width="item.width" :fixed="item.fixed">
        <template #default="scope">
          <div v-if="item.prop === 'action'">
            <el-button type="primary" icon="Edit" circle @click="confirmUpdate(scope.row)"/>
          </div>

          <div v-else-if="item.prop === 'status'">
            <el-switch v-model="scope.row.status" active-value="1" inactive-value="0"/>
          </div>

          <div v-else-if="item.prop === 'isComment'">
            <el-switch v-model="scope.row.isComment" active-value="1" inactive-value="0"/>
          </div>

          <div v-else-if="item.prop === 'thumbnail'">
            <div class="block">
              <el-avatar shape="square"  fit="cover" :size="60" :src="scope.row.thumbnail" style="border: solid #F0F0F2 2px;cursor:pointer;"/>
            </div>
          </div>

          <div v-else-if="item.prop === 'gender'">
            <span>{{scope.row.gender === '0'?'男':'女'}}</span>
          </div>
        </template>
      </el-table-column>

    </el-table>

    <Page
        :total="total"
        :handlePageNum="handlePageNum"
        :handlePageSize="handlePageSize"
    />

    <el-dialog
        v-model="isUpdate"
        title="审核博客"
        width="800"
        align-center
        style="min-width: 60vw"
    >
      <div style="flex: 45;display: flex;align-items: center;justify-content: center">
        <el-form ref="formInstance" :model="blog" style="width: 80%"  v-loading="loading">
          <el-form-item prop="title" label="标题：" >
            <p>
              {{ blog.title }}
            </p>
          </el-form-item>

          <el-form-item prop="content" label="内容：">
            <div id="preview"/>
          </el-form-item>

          <el-form-item prop="summary" label="摘要：">
              <span>
                {{blog.summary}}
              </span>
          </el-form-item>

          <el-form-item prop="categoryName" label="分类：">
            <p>
              {{blog.categoryName}}
            </p>
          </el-form-item>

          <el-form-item prop="thumbnail" label="缩略图：">
            <div class="block">
              <el-avatar shape="square"  fit="cover" :size="60" :src="blog.thumbnail" style="border: solid #F0F0F2 2px;cursor:pointer;"/>
            </div>
          </el-form-item>

          <el-form-item prop="createBy" label="创建人：">
            <p>
              {{blog.createBy}}
            </p>
          </el-form-item>

          <el-form-item prop="status" label="是否通过：">
            <el-switch v-model="blog.status" active-value="1" inactive-value="0"/>
          </el-form-item>

          <el-form-item prop="isComment" label="是否允许评论：">
            <el-switch v-model="blog.isComment" active-value="1" inactive-value="0"/>
          </el-form-item>
        </el-form>
      </div>

      <template #footer>
        <div class="dialog-footer">
          <el-button @click="isUpdate = false">取消</el-button>
          <el-button type="primary" @click="updateById">
            保存
          </el-button>
        </div>
      </template>
    </el-dialog>

  </el-card>
</template>

<style scoped lang="less">

</style>