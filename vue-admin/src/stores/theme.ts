import {ref} from "vue";
import {defineStore} from "pinia";
import {useDark} from "@vueuse/core";

export const useThemeStore = defineStore(
    'useTheme',
    () => {

        const isDark = useDark({
            storageKey: 'vitepress-theme-appearance',
        })



        return {
            isDark
        }

    },
    {
        persist: {
            enabled: true,
            strategies: [
                {
                    storage: localStorage,
                    paths: [
                        'isDark'
                    ]
                }
            ]
        }
    })