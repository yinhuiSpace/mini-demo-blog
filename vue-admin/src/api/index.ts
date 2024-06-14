import axios from "axios";
import router from "../router";
import {useUserStore} from "../stores/user.ts";

const axiosInstance = axios.create({
    baseURL: 'http://localhost:15000'
});

axiosInstance.interceptors.request.use((requestConfig) => {

    requestConfig.headers['token'] = useUserStore().token

    return requestConfig
})

axiosInstance.interceptors.response.use((response) => {

    if (!response.data.isSuccess){
        if (response.data.code===401){
            useUserStore().removeLogin()
            router.replace({path:'/login'}).then()
        }
    }

    return response
})

export default axiosInstance