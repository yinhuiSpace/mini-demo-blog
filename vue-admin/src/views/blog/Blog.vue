<script setup lang="ts">
import {query, tableData, list, label, confirmDelete, isDelete, deleteById, total} from "../../api/blog.ts"
import Page from "../../components/page/Page.vue";

const handlePageNum=(pageNum)=>{
  query.value.pageNum=pageNum
  list()
}

const handlePageSize=(pageSize)=>{
  query.value.pageSize=pageSize
  list()
}

list()
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
            <el-button type="danger" icon="Delete" circle @click="confirmDelete(scope.row.id)"/>
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

    <Page
        :total="total"
        :handlePageNum="handlePageNum"
        :handlePageSize="handlePageSize"
    />

  </el-card>
</template>

<style scoped lang="less">

</style>