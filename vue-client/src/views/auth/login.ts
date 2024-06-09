import {ref} from "vue";
import {ElMessage, FormInstance, FormRules} from "element-plus";
import axiosInstance from "../../api";
import {useUserStore} from "../../stores/user.ts";
import router from "../../router";

export const loginForm=ref({
    username:'',
    password:''
})

export const formRules=ref<FormRules>({
    username:[
        {
            required:true,
            message:'请填写用户名',
            trigger:'change'
        }
    ],
    password:[
        {
            required:true,
            message:'请填写密码',
            trigger:'change'
        }
    ]
})

export const formInstance=ref<FormInstance>({})

export const loading=ref(false)

export const login=(formInstance)=>{
    useUserStore().removeLogin()
    formInstance.validate((valid)=>{
        if (valid){
            loading.value=true
            return axiosInstance.post('/auth-service/auth/login',loginForm.value)
                .then((resp)=>{
                    if (resp.data.isSuccess){
                        useUserStore().token=resp.data.content.token
                        useUserStore().userInfo=resp.data.content.userInfoVo
                        ElMessage.success('登录成功')
                        router.push({path:'/'}).then()
                    }else {
                        ElMessage.error(resp.data.message)
                    }
                    loading.value=false
                })
        }
    })
}