import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus, {ElMessage} from 'element-plus'
import 'element-plus/dist/index.css'
import router from './router/index.ts'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import {createPinia, Pinia} from "pinia";
import 'element-plus/theme-chalk/dark/css-vars.css';
// import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
// import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import piniaPluginPersist from 'pinia-plugin-persist'
import Buefy from 'buefy'
import 'buefy/dist/buefy.css'
import '../src/assets/app.css'
import zhCN from "element-plus/dist/locale/zh-cn.mjs"


const app = createApp(App);
const pinia:Pinia=createPinia()
pinia.use(piniaPluginPersist)
// pinia.use(piniaPluginPersistedstate)
app.use(ElementPlus,{locale:zhCN})
app.use(Buefy)
app.use(router)
app.use(pinia)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}


app.mount('#app')

