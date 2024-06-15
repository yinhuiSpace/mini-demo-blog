import {useUserStore} from "../stores/user.ts";

export const logout=()=>{
    useUserStore().removeLogin()
}