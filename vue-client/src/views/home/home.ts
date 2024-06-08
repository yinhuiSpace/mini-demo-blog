import axiosInstance from "../../api";

export const getBoard=()=>{

    return axiosInstance.get('/comment-service/board')
}