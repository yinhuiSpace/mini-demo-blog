import axiosInstance from "./index.ts";
import {ElMessage, FormInstance} from "element-plus";
import {ref} from "vue";

export const search = ref({
    name: '',
    pageNum: 1,
    pageSize: 10
})

export const label = [
    {
        prop: "id",
        label: "id",
        width: "180"
    },
    {
        prop: "name",
        label: "分类",
        width: "150"
    },
    {
        prop: "parentId",
        label: "父级",
        width: "180"
    },
    {
        prop: "description",
        label: "描述",
        width: "150"
    },
    {
        prop: "status",
        label: "状态",
        width: "180"
    },
    {
        prop: "createTime",
        label: "创建时间",
        width: "150"
    },
    {
        prop: "updateTime",
        label: "更新时间",
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
    axiosInstance.get('/blog-service/category/page',
        {
            params: search.value
        }).then((resp) => {
        if (resp.data.isSuccess) {
            tableData.value = resp.data.content.rows
            total.value = resp.data.content.total
        } else {
            ElMessage.error(resp.data.message)
        }
    })
}


export const rules = {
    name: [
        {
            required: true,
            message: "分类名不能为空",
            trigger: "blur"
        }
    ],
    parentId: [
        {
            required: true,
            message: "所属分类不能为空",
            trigger: "blur"
        }
    ],
    description: [
        {
            required: true,
            message: "描述信息不能为空",
            trigger: "blur"
        },
        {
            min: 2,
            max: 50,
            message: "描述信息字数只能在2-50个字之间"
        }
    ]
}

export const formInstance = ref<FormInstance>()

export const isAdd = ref(false)

export const category = ref({
    name: "",
    parentId: "",
    description: ""
})

export const add = (formInstance) => {
    loading.value = true
    formInstance.validate((valid) => {
        if (valid) {
            axiosInstance.post('/blog-service/category/add', category.value)
                .then((resp) => {
                    if (resp.data.isSuccess) {
                        category.value = {}
                        ElMessage.success('增加成功！')
                        loading.value = false
                        list()
                        isAdd.value = false
                    } else {
                        ElMessage.error(resp.data.message)
                        loading.value = false
                    }
                })
        } else {
            ElMessage.error("请填写完整信息")
            loading.value = false
        }
    })
}

export const id = ref("")

export const deleteById = () => {
    loading.value = true
    axiosInstance.delete(`/blog-service/category/${id.value}`)
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

export const isUpdate = ref(false)

export const categoryUpdate = ref({})

export const confirmUpdate = (rowData) => {

    categoryUpdate.value = rowData
    isUpdate.value = true
}

export const update = () => {
    loading.value = true
    axiosInstance.put('/blog-service/category/update', categoryUpdate.value)
        .then((resp) => {
            if (resp.data.isSuccess) {
                loading.value = false
                ElMessage.success("更新成功")
                isUpdate.value = false
                list()
            } else {
                ElMessage.error(resp.data.message)
                loading.value = false
            }
        })
}

export const handlePageNum = (pageNum) => {
    search.value.pageNum = pageNum
    list()
}

export const handlePageSize = (pageSize) => {
    search.value.pageSize = pageSize
    list()
}

export const options = ref([])

export const getCategories = () => {
    axiosInstance.get("/blog-service/category/getFirst")
        .then((resp) => {
            if (resp.data.isSuccess) {
                options.value = resp.data.content
                options.value.push({
                    id: "0",
                    name: "父级分类"
                })
            } else {
                ElMessage.error(resp.data.message)
            }
        })
}