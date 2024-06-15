export const multiMenu=[
    {
        label:"账号管理",
        icon:"location",
        children:[
            {
                label:"用户管理",
                icon:"location",
                path:"/user"
            },
            {
                label:"管理员管理",
                icon:"location",
                path:"/"
            }
        ]
    },
    {
        label: "博客管理",
        icon: "location",
        children: [
            {
                label: "审核文章",
                path: "/review"
            },
            {
                label: "文章详情",
                path: "/blog"
            }
        ]
    },
    {
        label: "评论管理",
        icon: "location",
        children: [
            {
                label: "评论内容",
                path: "/comment"
            }
        ]
    }
]

export const singleMenu=[
    {
        label:"首页",
        icon:"location",
        path:"/charts"
    }
]