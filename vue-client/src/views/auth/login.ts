import {ref} from "vue";
import {ElMessage, FormInstance, FormRules} from "element-plus";
import axiosInstance from "../../api";
import {useUserStore} from "../../stores/user.ts";
import router from "../../router";

export const loginForm = ref({
    username: '',
    password: '',
    verifyCode: ''
})

export const formRules = ref<FormRules>({
    username: [
        {
            required: true,
            message: '请填写用户名',
            trigger: 'blur'
        }
    ],
    password: [
        {
            required: true,
            message: '请填写密码',
            trigger: 'blur'
        }
    ],
    verifyCode: [
        {
            required: true,
            message: '验证码不能为空',
            trigger: 'blur'
        }
    ]
})

export const formInstance = ref<FormInstance>({})

export const loading = ref(false)

export const isCode = ref(false)

export const login = (formInstance) => {
    useUserStore().removeLogin()
    formInstance.validate((valid) => {
        if (valid) {
            loading.value = true
            return axiosInstance.post('/auth-service/auth/login', loginForm.value)
                .then((resp) => {
                    if (resp.data.isSuccess) {
                        isCode.value=false
                        loading.value = false
                        useUserStore().token = resp.data.content.token
                        useUserStore().userInfo = resp.data.content.infoVo
                        ElMessage.success('登录成功')
                        loginForm.value = {
                            username: '',
                            password: ''
                        }
                        router.push({path: '/'}).then()
                    } else {
                        ElMessage.error(resp.data.message)
                        isCode.value=false
                        loading.value = false
                    }
                    loading.value = false
                })
        }else {
            isCode.value=false
            ElMessage.error("请按要求填写")
        }
    })
}

export const confirmLogin = () => {
    isCode.value=true
    if (loginForm.value.username===null||loginForm.value.username.trim().length===0){
        ElMessage.error("请按要求填写用户名密码")
    }else {
        getCode()
    }
}

export const getCode=()=>{
    axiosInstance.post("/auth-service/auth/verifyCode",{
        username:loginForm.value.username
    },{
        responseType:"arraybuffer"
    })
        .then((resp)=>{
            if (resp.data){
                const blob = new Blob([resp.data],{type:"image/png"});
                url.value=URL.createObjectURL(blob)
            }
        })
}

export const url=ref("")