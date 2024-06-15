import {ref} from "vue";
import axiosInstance from "./index.ts";
import {ElMessage, FormInstance} from "element-plus";

export const search = ref({
    nickname: '',
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
        prop: "avatar",
        label: "头像",
        width: "150"
    },
    {
        prop: "username",
        label: "用户名",
        width: "150"
    },
    {
        prop: "nickname",
        label: "昵称",
        width: "150"
    },
    {
        prop: "email",
        label: "邮箱",
        width: "180"
    },
    {
        prop: "phone",
        label: "电话",
        width: "150"
    },
    {
        prop: "gender",
        label: "性别",
        width: "150"
    },
    {
        prop: "status",
        label: "是否禁用",
        width: "100"
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
    axiosInstance.get('/user-service/user/list', {
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

export const userRegister = ref({
    username: '',
    nickname: '',
    password: '',
    email: '',
    phone: '',
    gender: '',
    avatar: ''
})

export const rules = {
    username: [
        {
            required: true,
            trigger: "change",
            message: "用户名不能为空"
        }
    ],
    password: [
        {
            required: true,
            trigger: "change",
            message: "密码不能为空"
        }
    ],
    email: [
        {
            required: true,
            trigger: "change",
            message: "邮箱不能为空"
        }
    ],
    phone: [
        {
            required: true,
            trigger: "change",
            message: "手机号不能为空"
        }
    ]
}

export const formInstance = ref<FormInstance>()

export const isAdd = ref(false)

export const add = (formInstance) => {
    loading.value = true
    formInstance.validate((valid) => {
        if (valid) {
            axiosInstance.post('/user-service/user/add', userRegister.value)
                .then((resp) => {
                    if (resp.data.isSuccess) {
                        userRegister.value = {}
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
    axiosInstance.delete(`/user-service/user/${id.value}`)
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

export const user = ref({})

export const confirmUpdate = (rowUser) => {

    user.value = rowUser
    isUpdate.value = true
}

export const update = () => {
    loading.value = true
    axiosInstance.put('/user-service/user/update', user.value)
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