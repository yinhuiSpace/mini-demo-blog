import{a as k}from"./index-BgbCg4-D.js";import{d as C,r as d,g as w,b as a,w as t,F as B,a as r,q as E,o as f,i as u,e as p,f as I,c as N,E as m}from"./index-DhBAbreQ.js";import"./user-DyNFqZc8.js";const $={style:{flex:"45",display:"flex","align-items":"center","justify-content":"center"}},q={class:"dialog-footer"},j=C({__name:"Board",setup(D){const n=d({}),_=i=>{i.validate(e=>{e?k.post("/comment-service/board",n.value).then(o=>{o.data.isSuccess?(m.success("发布成功"),s.value=!1,n.value={}):m.error(o.data.message)}):m.error("请填写完整信息")})},v={content:[{required:!0,message:"公告内容不能为空",trigger:"blur"},{min:2,max:500,trigger:"blur",message:"字数只能位于2-500字之间"}]},s=d(!1),c=d();return(i,e)=>{const o=r("el-button"),g=r("el-input"),x=r("el-form-item"),y=r("el-form"),V=r("el-dialog"),b=E("loading");return f(),w(B,null,[a(o,{type:"success",icon:"CirclePlus",onClick:e[0]||(e[0]=l=>s.value=!0)},{default:t(()=>[u(" 发布公告 ")]),_:1}),a(V,{modelValue:s.value,"onUpdate:modelValue":e[4]||(e[4]=l=>s.value=l),title:"新增分类",width:"500","align-center":""},{footer:t(()=>[p("div",q,[a(o,{onClick:e[2]||(e[2]=l=>s.value=!1)},{default:t(()=>[u("取消")]),_:1}),a(o,{type:"primary",onClick:e[3]||(e[3]=l=>_(c.value))},{default:t(()=>[u(" 保存 ")]),_:1})])]),default:t(()=>[p("div",$,[I((f(),N(y,{ref_key:"formInstance",ref:c,model:n.value,style:{width:"80%"},rules:v},{default:t(()=>[a(x,{prop:"content",label:"公告内容"},{default:t(()=>[a(g,{placeholder:"公告",type:"textarea",autocomplete:"off",modelValue:n.value.content,"onUpdate:modelValue":e[1]||(e[1]=l=>n.value.content=l),"prefix-icon":"lock",minlength:"2",maxlength:"500"},null,8,["modelValue"])]),_:1})]),_:1},8,["model"])),[[b,i.loading]])])]),_:1},8,["modelValue"])],64)}}});export{j as default};
