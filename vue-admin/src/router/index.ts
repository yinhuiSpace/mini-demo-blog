import {createRouter, createWebHashHistory} from "vue-router";
import {useUserStore} from "../stores/user.ts";
// import {useUserStore} from "../stores/user.ts";

const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: '/',
            name: '首页',
            component: () => import('../views/home/Home.vue'),
            meta:{
                title:'首页'
            },
            children:[
                {
                    path: '/user',
                    name: '用户',
                    component: () => import('../views/user/User.vue'),
                    meta:{
                        title:'用户'
                    }
                }
            ]
        },
        {
            path: '/login',
            name: '登录',
            component: () => import('../views/auth/Login.vue'),
            meta:{
                title:'登录'
            }
        }
    ]
});

router.beforeEach((to, from, next)=>{

    document.title=to.meta.title.toString()
    if (useUserStore().isLogin()){
        if (to.path==='/login' || to.path==='/register'){
            next({path:'/'})
        }else {
            next()
        }
    }
})

export default router