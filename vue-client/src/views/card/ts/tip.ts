import axiosInstance from "../../../api";

export interface Tip{
    content:string,
    author:string
}

export const getTip=()=>{
    return axiosInstance.get('/comment-service/tip')
}