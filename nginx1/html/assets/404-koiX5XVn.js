import{d as s,e as a,f as o,l as n,c as r,h as e,t as c,o as l}from"./index-D4dsPctk.js";const i={class:"columns mt-6",style:{height:"60vh"}},u={class:"column mt-6"},_=e("p",{class:"content"}," 页面丢失 ",-1),m={class:"content subtitle mt-6"},f=s({__name:"404",setup(d){const t=a({duration:5,timer:null});return o(()=>{t.value.timer=setInterval(()=>{t.value.duration--,t.value.duration==0&&(clearInterval(t.value.timer),n.push({path:"/"}))},1e3)}),(p,h)=>(l(),r("div",i,[e("div",u,[_,e("p",m," 您所查找的页面不存在，"+c(t.value.duration)+"秒后，将跳至首页 ",1)])]))}});export{f as default};