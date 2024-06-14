import {defineStore} from "pinia";
import {ref} from "vue";

export const useUserStore = defineStore(
    'userStore',
    () => {

        const token = ref('')

        const userInfo = ref({})

        const removeLogin = () => {
            token.value = ''
            userInfo.value = {}
        }

        const isLogin = () => {

            return token.value != null && token.value.trim().length > 0
        }


        return {
            token,
            userInfo,
            removeLogin,
            isLogin
        }

    },
    {
        persist: {
            enabled: true,
            strategies: [
                {
                    storage: localStorage,
                    paths: [
                        'token',
                        'userInfo'
                    ]
                }
            ]
        }
    })