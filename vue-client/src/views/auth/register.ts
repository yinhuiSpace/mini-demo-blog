import {ref} from "vue";
import {ElMessage, FormInstance, FormRules} from "element-plus";
import axiosInstance from "../../api";
import router from "../../router";
import {useUserStore} from "../../stores/user.ts";

export const ruleForm = ref({
    username: '',
    nickname: '',
    password: '',
    checkPass: '',
    email: '',
    phone: '未知',
    code:''
})

const checkPass = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请再次输入密码'))
    } else if (value !== ruleForm.value.password) {
        callback(new Error('两次输入密码不一致'))
    } else {
        callback()
    }
}

export const loading = ref(false)
export const formRules = ref<FormRules>({
    username: [
        {
            required: true,
            message: '请输入账号',
            trigger: 'change'
        },
        {
            min: 2,
            max: 10,
            message: '长度在2到10个字符之间',
            trigger: 'blur'
        }
    ],
    password: [
        {
            required: true,
            message: '请输入密码',
            trigger: 'change'
        },
        {
            min: 6,
            max: 20,
            message: '长度在6到20个字符之间',
            trigger: 'blur'
        }
    ],
    checkPass: [
        {
            required: true,
            message: '请再次输入密码',
            trigger: 'change'
        },
        {
            validator: checkPass,
            trigger: 'change'
        }
    ],
    email: [
        {
            required: true,
            message: '请输入邮箱',
            trigger: 'change'
        }
    ],
    phone: [
        {
            required: true,
            message: '请输入手机号',
            trigger: 'change'
        },
        {
            min: 11,
            max: 11,
            message: '手机号格式错误',
            trigger: 'blur'
        }
    ],
    code:[
        {
            required: true,
            message: '请输入验证码',
            trigger: 'blur'
        }
    ]
})

export const formInstance = ref<FormInstance>({})

export const userRegister = (formInstance) => {
    useUserStore().removeLogin()
    formInstance.validate((valid) => {
        if (valid) {
            return axiosInstance.post('/user-service/user/register', ruleForm.value)
                .then((resp) => {
                    if (resp.data.isSuccess) {
                        ElMessage.success('注册成功')
                        router.push({path: '/login'}).then()
                    } else {
                        ElMessage.error(resp.data.message)
                    }
                    loading.value = false
                })
        } else {

        }
    })
}

export const codeLoading=ref(false)

export const disable=ref(false)

export const text=ref("获取验证码")

export const duration=ref(60)

export const getCheckCode=()=>{

    if (ruleForm.value.email===null||ruleForm.value.email.trim().length===0){
        ElMessage.error("请先填写邮箱")
    }else {
        ElMessage.success("验证码已发送，请到邮箱查看")
        disable.value = true

        const timer = setInterval(()=>{
            duration.value--
            const tmp =duration.value
            text.value=`${tmp}秒`
            if (tmp<=0){
                clearInterval(timer)
                duration.value=60
                text.value="重新获取"
                disable.value=false

            }
        },1000);
    }

}