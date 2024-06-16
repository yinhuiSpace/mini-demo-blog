<script setup lang="ts">
import {onMounted, ref} from "vue";
import Vditor from "vditor";
import 'vditor/dist/index.css';
import axiosInstance from "../../api";
import {ElMessage, FormInstance} from "element-plus";
import router from "../../router";

const blogForm = ref({
  title: '',
  content: '',
  tags: [],
  categoryId: '',
  thumbnail: 'http://',
  summary: '',
  status: 1
})

const vditor = ref()

const loading = ref(false)

const save = (form) => {
  form.validate((valid)=>{
    if(valid){
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
    }else {
      ElMessage.error("请按照要求编写文章")
    }
  })
}

const submit = (form) => {
  form.validate((valid)=>{
    if (valid){
      loading.value = true
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
    }else {
      ElMessage.error("请按照要求编写文章")
    }
  })
}



const formInstance=ref<FormInstance>()

const uploadImg=(file,callback)=>{
  console.log(file);
}

const rules=ref({
  title:[
    {
      required:true,
      message:"标题不能为空",
      trigger:"blur"
    },
    {
      min:2,
      max:20,
      message: "标题长度必须控制在2-20个字符",
      trigger: "blur"
    }
  ],
  summary:[
    {
      required:true,
      message:"摘要不能为空",
      trigger:"blur"
    },
    {
      min:2,
      max:50,
      message: "摘要长度必须控制在2-50个字符",
      trigger: "blur"
    }
  ],
  content:[
    {
      required:true,
      message:"内容不能为空",
      trigger:"blur"
    }
  ],
  categoryId:[
    {
      required:true,
      message:"文章分区不能为空",
      trigger:"blur"
    }
  ]
})

onMounted(() => {
  getCategories()
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
    mode: 'sv',
    upload: {
      url: "http://localhost:15000/blog-service/upload/img",
      accept: 'image/*',
      fieldName: 'blogImg',
      token: 'token',
      multiple: false,
      max: 2 * 1024 * 1024,
      filename(name) {
        // return name.replace(/[^(a-zA-Z0-9\u4e00-\u9fa5\.)]/g, '')
        //     .replace(/[\?\\/:|<>\*\[\]\(\)\$%\{\}@~]/g, '')
        //     .replace('/\\s/g', '')
        return name
      },
      format(files, responseText) {
        // const res = JSON.parse(responseText)
        // const name = files[0].name
        // const url = res.data
        // const result = JSON.stringify({
        //   code: 0,
        //   data: { errFiles: '', succMap: { [name]: url }}
        // })
        // return result
        console.log(files[0]);
        const data = JSON.parse(responseText);
        if (data.isSuccess){
          const url=data.content
          return JSON.stringify({
            code: 0,
            data: { errFiles: '', succMap: { [files[0].name]: url }}
          })
        }else {
          ElMessage(data.message)
        }
      },
      // success(ele, msg) {
      //   console.log(ele);
      //   console.log(msg)
      //   ElMessage.success("上传成功")
      //   const data = JSON.parse(msg);
      //   const url=data.content
      //   // const image = new Image(200);
      //   // image.src=url
      //   // const name="图片"
      //   // const div = new HTMLDivElement();
      //   // div.innerText=`![${name}](${url})`
      //   // ele.appendChild(div)
      // },
      // error(ele, msg) {
      //   console.log(ele)
      //   console.log(msg)
      //   const data = JSON.parse(msg);
      //   ElMessage.error(data.message)
      // }
    },
  })
})

const getCategories=()=>{
  axiosInstance.get("/blog-service/category")
      .then((resp)=>{
        if (resp.data.isSuccess){
          options.value=resp.data.content
        }else {
          ElMessage.error(resp.data.message)
        }
      })
}

const options = ref([])

const handleContent=()=>{
  blogForm.value.content=vditor.value.getValue()
}
</script>

<template>
  <el-card>
    <template #header>
      新增文章
    </template>
    <el-form :model="blogForm" v-loading="loading" :rules="rules" ref="formInstance">
      <el-form-item prop="title">
        <el-input
            v-model="blogForm.title"
            placeholder="请输入文章标题"
        />
      </el-form-item>

      <el-form-item prop="content">
        <div id="vditor" @input="handleContent"/>
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

      <el-form-item prop="summary">
        <el-input
            type="textarea"
            autocomplete="off"
            v-model="blogForm.summary"
            minlength="2"
            maxlength="50"
            placeholder="请输入文章摘要"
        />
      </el-form-item>

      <el-form-item prop="categoryId">
        <el-select
            v-model="blogForm.categoryId"
            clearable
            placeholder="Select"
            style="width: 240px"
        >
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.name"
              :value="item.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submit(formInstance)">
          发布文章
        </el-button>

        <el-button type="danger" @click="save(formInstance)" class="ml-2">
          保存草稿
        </el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<style scoped lang="less">

</style>