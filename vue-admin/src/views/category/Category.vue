<script setup lang="ts">

import {
  add, category, categoryUpdate,
  confirmDelete,
  confirmUpdate,
  deleteById, formInstance, getCategories, handlePageNum, handlePageSize,
  isAdd,
  isDelete, isUpdate,
  label,
  list, loading, options, rules,
  search,
  tableData, total, update
} from "../../api/category.ts";
import Page from "../../components/page/Page.vue";
import {onMounted} from "vue";
import {blog} from "../../api/review.ts";

onMounted(()=>{
  getCategories()
  list()
})
</script>

<template>
  <el-card>
    <el-row :gutter="20">
      <el-col :span="7">
        <el-input placeholder="搜索框" clearable v-model="search.name" @input="list"/>
      </el-col>

      <el-button type="primary" icon="Search" @click="list">
        搜索
      </el-button>
      <el-button type="success" icon="CirclePlus" @click="isAdd = true">
        添加分类
      </el-button>
    </el-row>

    <el-table :data="tableData" stripe style="width: 100%;margin-top: 30px;">
      <el-table-column v-for="(item,index) in label" :key="index" :prop="item.prop" :label="item.label" :width="item.width" :fixed="item.fixed">
        <template #default="scope">
          <div v-if="item.prop === 'action'">
            <el-button type="primary" icon="Edit" circle @click="confirmUpdate(scope.row)"/>
            <el-button type="danger" icon="Delete" circle @click="confirmDelete(scope.row.id)"/>
          </div>

          <div v-else-if="item.prop === 'status'">
            <el-switch v-model="scope.row.status" active-value="1" inactive-value="0"/>
          </div>
        </template>
      </el-table-column>

    </el-table>

    <el-dialog
        v-model="isDelete"
        title="警告"
        width="500"
        align-center
    >
    <span>
      确定删除该记录吗
    </span>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="isDelete = false">取消</el-button>
          <el-button type="danger" @click="deleteById">
            删除
          </el-button>
        </div>
      </template>
    </el-dialog>

    <el-dialog
        v-model="isAdd"
        title="新增分类"
        width="500"
        align-center
    >
      <div style="flex: 45;display: flex;align-items: center;justify-content: center">
        <el-form ref="formInstance" :model="category" style="width: 80%" :rules="rules" v-loading="loading">
          <el-form-item prop="name">
            <el-input
                placeholder="分类名"
                type="text"
                autocomplete="off"
                v-model="category.name"
                prefix-icon="user"
            />
          </el-form-item>

          <el-form-item prop="parentId">
            <el-select
                v-model="category.parentId"
                clearable
                placeholder="选择分区"
                style="width: 240px"
            >
              <el-option
                  v-for="item in options"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
              />
            </el-select>
          </el-form-item>

          <el-form-item prop="description">
            <el-input
                placeholder="描述"
                type="textarea"
                autocomplete="off"
                v-model="category.description"
                prefix-icon="lock"
                minlength="2"
                maxlength="50"
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

    <el-dialog
        v-model="isDelete"
        title="警告"
        width="500"
        align-center
    >
    <span>
      确定删除该记录吗
    </span>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="isDelete = false">取消</el-button>
          <el-button type="danger" @click="deleteById">
            删除
          </el-button>
        </div>
      </template>
    </el-dialog>

    <el-dialog
        v-model="isUpdate"
        title="修改分类"
        width="500"
        align-center
    >
      <div style="flex: 45;display: flex;align-items: center;justify-content: center">
        <el-form ref="formInstance" :model="categoryUpdate" style="width: 80%" :rules="rules" v-loading="loading">
          <el-form-item label="id">
            <span>
              {{categoryUpdate.id}}
            </span>
          </el-form-item>

          <el-form-item label="分类名">
            <el-input
                v-model="categoryUpdate.name"
                placeholder="分类名"
                type="text"
                autocomplete="off"
                prefix-icon="user"
              />
          </el-form-item>

          <el-form-item prop="parentId" label="所属分类">
            <el-select
                v-model="categoryUpdate.parentId"
                clearable
                placeholder="选择分区"
                style="width: 240px"
            >
              <el-option
                  v-for="item in options"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
              />
            </el-select>
          </el-form-item>

          <el-form-item prop="description" label="描述">
            <el-input
                placeholder="描述"
                type="textarea"
                autocomplete="off"
                v-model="categoryUpdate.description"
                prefix-icon="lock"
                minlength="2"
                maxlength="50"
            />
          </el-form-item>

          <el-form-item label="是否封禁">
            <el-switch v-model="categoryUpdate.status" active-value="1" inactive-value="0"/>
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

    <Page
        :total="total"
        :handlePageNum="handlePageNum"
        :handlePageSize="handlePageSize"
    />

  </el-card>
</template>

<style scoped lang="less">

</style>