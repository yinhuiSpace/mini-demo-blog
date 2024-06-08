import { createApp } from 'vue'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from './router/index.ts'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import {createPinia, Pinia} from "pinia";
// import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
// import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import piniaPluginPersist from 'pinia-plugin-persist'
import Buefy from 'buefy'
import 'buefy/dist/buefy.css'
import '../src/assets/app.css'


const app = createApp(App);
const pinia:Pinia=createPinia()
pinia.use(piniaPluginPersist)
// pinia.use(piniaPluginPersistedstate)
app.use(ElementPlus)
app.use(Buefy)
app.use(router)
app.use(pinia)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}


app.mount('#app')

