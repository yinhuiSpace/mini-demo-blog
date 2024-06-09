<script setup lang="ts">
import {onMounted, ref} from "vue";
import Vditor from "vditor";
import 'vditor/dist/index.css';
import axiosInstance from "../../api";
import {ElMessage} from "element-plus";
import router from "../../router";

const blogForm = ref({
  title: '',
  content: '',
  tags: [],
  categoryId: 1,
  thumbnail: 'http://',
  summary: '',
  status: 1
})

const vditor = ref()

const loading = ref(false)

const save = () => {
  loading.value = true
  blogForm.value.content=vditor.value.getValue()
  axiosInstance.post('/blog-service/article', blogForm.value)
      .then((resp) => {
        if (resp.data.isSuccess) {
          ElMessage.success('保存成功')
          loading.value = false
        } else {
          ElMessage.error(resp.data.message)
          loading.value = false
        }
      })
}

const submit = () => {
  loading.value = true
  blogForm.value.content=vditor.value.getValue()
  blogForm.value.status=0
  axiosInstance.post('/blog-service/article', blogForm.value)
      .then((resp) => {
        if (resp.data.isSuccess) {
          ElMessage.success('发布成功')
          loading.value = false
          router.replace({path: '/'})
        } else {
          ElMessage.error(resp.data.message)
          loading.value = false
        }
      })
}

onMounted(() => {
  vditor.value = new Vditor('vditor', {
    height: '60vh',
    width: '100vw',
    placeholder: '文章内容',
    theme: 'classic',
    counter: {
      enable: true,
      type: "markdown"
    },
    preview: {
      delay: 0,
      hljs: {
        style: 'monokai',
        lineNumber: true
      }
    },
    tab: '\t',
    typewriterMode: true,
    toolbarConfig: {
      pin: true
    },
    cache: {
      enable: false
    },
    mode: 'sv'
  });
})
</script>

<template>
  <el-form v-loading="loading">
    <el-form-item>
      <el-input
          v-model="blogForm.title"
          placeholder="请输入文章标题"
      />
    </el-form-item>

    <el-form-item>
      <div id="vditor"/>
    </el-form-item>

    <el-form-item>
      <b-taginput
          v-model="blogForm.tags"
          class="my-3"
          maxlength="15"
          maxtags="5"
          ellipsis
          placeholder="请输入文章标签"
          style="width: 100vw;"
      />
    </el-form-item>

    <el-form-item>
      <el-input
          type="textarea"
          autocomplete="off"
          v-model="blogForm.summary"
          minlength="4"
          maxlength="6"
          placeholder="请输入文章摘要"
      />
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="submit">
        发布文章
      </el-button>

      <el-button type="danger" @click="save" class="ml-2">
        保存草稿
      </el-button>
    </el-form-item>
  </el-form>
</template>

<style scoped lang="less">

</style>