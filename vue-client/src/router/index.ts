import {createRouter, createWebHashHistory} from "vue-router";

const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: '/',
            name: '首页',
            component: () => import('../views/home/Home.vue')
        }
    ]
});

export default router