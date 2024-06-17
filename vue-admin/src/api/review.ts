import axiosInstance from "./index.ts";
import {ElMessage} from "element-plus";
import {ref} from "vue";
import {id, isUpdate} from "./user.ts";
import Vditor from "vditor";

export const query = ref({
    title: "",
    pageNum: 1,
    pageSize: 10,
})

export const tableData=ref([])

export const label=[
    {
        prop: "id",
        label: "id",
        width: "180"
    },
    {
        prop: "title",
        label: "标题",
        width: "150"
    },
    {
        prop: "summary",
        label: "摘要",
        width: "150"
    },
    {
        prop: "categoryName",
        label: "分类名",
        width: "150"
    },
    {
        prop: "thumbnail",
        label: "缩略图",
        width: "180"
    },
    {
        prop: "createBy",
        label: "创建人",
        width: "150"
    },
    {
        prop: "isComment",
        label: "是否评论",
        width: "150"
    },
    {
        prop: "status",
        label: "是否通过",
        width: "150"
    },
    {
        prop: "action",
        label: "操作",
        width: "180",
        fixed: "right"
    }
]

export const loading = ref(false)

export const list = () => {
    loading.value = true
    axiosInstance.get("/blog-service/article/review",{
        params:query.value
    })
        .then((resp) => {
            if (resp.data.isSuccess) {
                tableData.value=resp.data.content.rows
                total.value=resp.data.content.total
                loading.value = false
            } else {
                ElMessage.error(resp.data.message)
                loading.value = false
            }
        })
}

export const total=ref(1)

export const blog=ref({})

export const isUpdate=ref(false)
export const updateById=()=>{

    axiosInstance.put("/blog-service/article/update",blog.value)
        .then((resp) => {
            if (resp.data.isSuccess) {
                loading.value = false
                ElMessage.success("修改成功")
                isUpdate.value=false
            } else {
                ElMessage.error(resp.data.message)
                loading.value = false
            }
        })
}

export const confirmUpdate=(rowBlog)=> {
    Vditor.preview(document.getElementById('preview'), rowBlog.content, {
        hljs: {
            style: 'github'
        }
    }).then(r => {
    })
    isUpdate.value = true
    blog.value = rowBlog
}


