import axios from "axios";
import router from "../router";

const axiosInstance = axios.create({
    baseURL: '/api'
});

axiosInstance.interceptors.request.use((requestConfig) => {
    // const key = localStorage.getItem("tokenName");
    // const value = localStorage.getItem("tokenValue");
    //
    // if (key && value) {
    //     requestConfig.headers[key] = value
    //     console.log("从缓存中读取token %s:%s，并加进请求头中", key, value)
    // }
    //
    // requestConfig.headers[key]=value
    // requestConfig.headers["user-id"]=''

    return requestConfig
})

axiosInstance.interceptors.response.use((response)=>{

    // const code = response.data.code;
    // console.log(code);
    // if (code==403){
    //     localStorage.removeItem('tokenName')
    //     localStorage.removeItem('tokenValue')
    //     console.log('登录失败');
    //     router.push('/login').then()
    //
    // }

    return response
})

export default axiosInstance