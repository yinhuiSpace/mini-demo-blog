import {ref} from "vue";
import axiosInstance from "./index.ts";
import {ElMessage, FormInstance} from "element-plus";

export const query = ref({
    content: '',
    pageNum: '',
    pageSize: ''
})

export const label = [
    {
        prop: "id",
        label: "id",
        width: "180"
    },
    {
        prop: "title",
        label: "博客",
        width: "150"
    },
    {
        prop: "content",
        label: "评论内容",
        width: "150"
    },
    {
        prop: "username",
        label: "评论人",
        width: "150"
    },
    {
        prop: "children",
        label: "回复数",
        width: "180"
    },
    {
        prop: "createTime",
        label: "创建时间",
        width: "150"
    },
    {
        prop: "action",
        label: "操作",
        width: "180",
        fixed: "right"
    }
]

export const tableData = ref([])

export const total = ref(1)

export const loading = ref(false)

export const list = () => {
    axiosInstance.get('/comment-service/comment/page', {
        params: query.value
    }).then((resp) => {
        if (resp.data.isSuccess) {
            tableData.value = resp.data.content.rows
            total.value = resp.data.content.total
        } else {
            ElMessage.error(resp.data.message)
        }
    })
}


export const id = ref("")

export const deleteById = () => {
    loading.value = true
    axiosInstance.delete(`/comment-service/comment/${id.value}`)
        .then((resp) => {
            if (resp.data.isSuccess) {
                loading.value = false
                ElMessage.success("删除成功")
                isDelete.value = false
                list()
            } else {
                ElMessage.error(resp.data.message)
                loading.value = false
                isDelete.value = false
            }
        })
}

export const isDelete = ref(false)

export const confirmDelete = (rowId) => {
    id.value = rowId
    isDelete.value = true
}

export const handlePageNum=(pageNum)=>{
    query.value.pageNum=pageNum
    list()
}

export const handlePageSize=(pageSize)=>{
    query.value.pageSize=pageSize
    list()
}
