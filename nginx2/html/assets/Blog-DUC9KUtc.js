import{a as x}from"./index-BgbCg4-D.js";import{r as p,E as h,d as L,c as V,w as l,a as i,o as r,b as t,u as a,i as _,g as w,h as M,F as R,e as f,s as y,t as j,x as A}from"./index-DhBAbreQ.js";import{_ as G}from"./Page.vue_vue_type_script_setup_true_lang-juzETF4l.js";import"./user-DyNFqZc8.js";const m=p({title:"",pageNum:1,pageSize:10}),N=p([]),H=[{prop:"id",label:"id",width:"180"},{prop:"title",label:"标题",width:"150"},{prop:"summary",label:"摘要",width:"150"},{prop:"categoryName",label:"分类名",width:"150"},{prop:"thumbnail",label:"缩略图",width:"180"},{prop:"viewCount",label:"浏览量",width:"150"},{prop:"createBy",label:"创建人",width:"150"},{prop:"authorName",label:"作者名",width:"100"},{prop:"createTime",label:"创建时间",width:"100"},{prop:"action",label:"操作",width:"180",fixed:"right"}],c=p(!1),u=()=>{c.value=!0,x.get("/blog-service/article/list",{params:m.value}).then(o=>{o.data.isSuccess?(N.value=o.data.content.rows,S.value=o.data.content.total,c.value=!1):(h.error(o.data.message),c.value=!1)})},B=p(""),C=()=>{c.value=!0,x.delete(`/blog-service/article/${B.value}`).then(o=>{o.data.isSuccess?(u(),h.success("删除成功"),c.value=!1,s.value=!1):(h.error(o.data.message),c.value=!1)})},s=p(!1),J=o=>{B.value=o,s.value=!0},S=p(1),K={key:0},O={key:1},Q={key:2},W=f("span",null," 确定删除该记录吗 ",-1),X={class:"dialog-footer"},Y=f("span",null," 确定删除该记录吗 ",-1),Z={class:"dialog-footer"},oe=L({__name:"Blog",setup(o){const D=v=>{m.value.pageNum=v,u()},U=v=>{m.value.pageSize=v,u()};return u(),(v,n)=>{const $=i("el-input"),I=i("el-col"),d=i("el-button"),z=i("el-row"),E=i("el-switch"),F=i("el-table-column"),P=i("el-table"),b=i("el-dialog"),T=i("el-card");return r(),V(T,null,{default:l(()=>[t(z,{gutter:20},{default:l(()=>[t(I,{span:7},{default:l(()=>[t($,{placeholder:"搜索框",clearable:"",modelValue:a(m).title,"onUpdate:modelValue":n[0]||(n[0]=e=>a(m).title=e),onInput:a(u)},null,8,["modelValue","onInput"])]),_:1}),t(d,{type:"primary",icon:"Search",onClick:a(u)},{default:l(()=>[_(" 搜索 ")]),_:1},8,["onClick"])]),_:1}),t(P,{data:a(N),stripe:"",style:{width:"100%","margin-top":"30px"}},{default:l(()=>[(r(!0),w(R,null,M(a(H),(e,q)=>(r(),V(F,{key:q,prop:e.prop,label:e.label,width:e.width,fixed:e.fixed},{default:l(g=>[e.prop==="action"?(r(),w("div",K,[t(d,{type:"danger",icon:"Delete",circle:"",onClick:k=>a(J)(g.row.id)},null,8,["onClick"])])):e.prop==="status"?(r(),w("div",O,[t(E,{modelValue:g.row.status,"onUpdate:modelValue":k=>g.row.status=k,"active-value":"1","inactive-value":"0"},null,8,["modelValue","onUpdate:modelValue"])])):e.prop==="gender"?(r(),w("div",Q,[f("span",null,j(g.row.gender==="0"?"男":"女"),1)])):A("",!0)]),_:2},1032,["prop","label","width","fixed"]))),128))]),_:1},8,["data"]),t(b,{modelValue:a(s),"onUpdate:modelValue":n[2]||(n[2]=e=>y(s)?s.value=e:null),title:"警告",width:"500","align-center":""},{footer:l(()=>[f("div",X,[t(d,{onClick:n[1]||(n[1]=e=>s.value=!1)},{default:l(()=>[_("取消")]),_:1}),t(d,{type:"danger",onClick:a(C)},{default:l(()=>[_(" 删除 ")]),_:1},8,["onClick"])])]),default:l(()=>[W]),_:1},8,["modelValue"]),t(b,{modelValue:a(s),"onUpdate:modelValue":n[4]||(n[4]=e=>y(s)?s.value=e:null),title:"警告",width:"500","align-center":""},{footer:l(()=>[f("div",Z,[t(d,{onClick:n[3]||(n[3]=e=>s.value=!1)},{default:l(()=>[_("取消")]),_:1}),t(d,{type:"danger",onClick:a(C)},{default:l(()=>[_(" 删除 ")]),_:1},8,["onClick"])])]),default:l(()=>[Y]),_:1},8,["modelValue"]),t(G,{total:a(S),handlePageNum:D,handlePageSize:U},null,8,["total"])]),_:1})}}});export{oe as default};
