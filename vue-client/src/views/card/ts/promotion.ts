import axiosInstance from "../../../api";

export const getPromotions=()=>{
    return axiosInstance.get('/blog-service/link/getAllLinks')
}