import {createRouter, createWebHashHistory} from "vue-router";
import {useUserStore} from "../stores/user.ts";

const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: '/',
            name: '首页',
            component: () => import('../views/home/Home.vue'),
            meta:{
                title:'首页'
            }
        },
        {
            path:'/register',
            name:'注册',
            component:()=>import('../views/auth/Register.vue'),
            meta:{
                title:'注册'
            }
        },
        {
            path:'/:pathMatch(.*)',
            name:'404',
            component:()=>import('../views/error/404.vue'),
            meta:{
                title:'404-NotFound'
            }
        },
        {
            path:'/login',
            name:'登录',
            component:()=>import('../views/auth/Login.vue'),
            meta:{
                title:'登录'
            }
        },
        {
            path:'/createBlog',
            name:'发布文章',
            component:()=>import('../views/post/Create.vue'),
            meta:{
                title:'发布文章'
            }
        },
        {
            path:'/details/:id',
            name:'details',
            component:()=>import('../views/details/Detail.vue'),
            meta:{
                title:'文章详情'
            }
        },
        {
            path:'/person/:id',
            name:'person',
            component:()=>import('../views/person/Profile.vue'),
            meta:{
                title:'个人中心'
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

    next()

})

export default router