import axiosInstance from "../../api";

export const getAllBlogs=(page)=>{
    return axiosInstance.get('/blog-service/article/articleList',{
        params:page
    })
}