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
                    path: 'user',
                    name: '用户',
                    component: () => import('../views/user/User.vue'),
                    meta:{
                        title:'用户'
                    }
                },
                {
                    path: 'blog',
                    name: '博客',
                    component: () => import('../views/blog/Blog.vue'),
                    meta:{
                        title:'博客管理'
                    }
                },
                {
                    path: 'review',
                    name: '审核',
                    component: () => import('../views/blog/Review.vue'),
                    meta:{
                        title:'博客审核'
                    }
                },
                {
                    path: 'charts',
                    name: '图表',
                    component: () => import('../views/charts/Chart.vue'),
                    meta:{
                        title:'统计图表'
                    }
                },
                {
                    path: 'comment',
                    name: '评论',
                    component: () => import('../views/comment/Comment.vue'),
                    meta:{
                        title:'评论内容'
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
    // if (useUserStore().isLogin()){
    //     if (to.path==='/login' || to.path==='/register'){
    //         next({path:'/'})
    //     }else {
    //         next()
    //     }
    // }else {
    //     next({path:'/login'})
    // }
    console.log(to);
    next()
})

export default router