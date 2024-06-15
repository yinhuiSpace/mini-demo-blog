import axiosInstance from "./index.ts";
import {ElMessage} from "element-plus";
import {ref} from "vue";
import {id} from "./user.ts";

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
        prop: "viewCount",
        label: "浏览量",
        width: "150"
    },
    {
        prop: "createBy",
        label: "创建人",
        width: "150"
    },
    {
        prop: "authorName",
        label: "作者名",
        width: "100"
    },
    {
        prop: "createTime",
        label: "创建时间",
        width: "100"
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
    axiosInstance.get("/blog-service/article/list",{
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

export const id=ref("")

export const deleteById = () => {
    loading.value = true
    axiosInstance.delete(`/blog-service/article/${id.value}`)
        .then((resp) => {
            if (resp.data.isSuccess) {
                list()
                ElMessage.success("删除成功")
                loading.value = false
                isDelete.value=false
            } else {
                ElMessage.error(resp.data.message)
                loading.value = false
            }
        })
}

export const isDelete = ref(false)

export const confirmDelete = (rowId) => {
    id.value = rowId
    isDelete.value = true
}

export const total=ref(1)

