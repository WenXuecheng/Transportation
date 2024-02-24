"use strict";(self["webpackChunkvue"]=self["webpackChunkvue"]||[]).push([[685],{2685:function(e,t,s){s.r(t),s.d(t,{default:function(){return h}});var r=function(){var e=this,t=e._self._c;e._self._setupProxy;return t("div",{staticStyle:{width:"100%"}},[t("el-table",{staticStyle:{"border-radius":"10px",width:"276px",margin:"auto"},attrs:{"header-cell-style":"background-color: #F2F6FC; color: #606266;",data:e.tableData,border:"",height:"780",size:"medium"}},[t("el-table-column",{attrs:{label:e.$t("create_time"),prop:"createTime",width:"100px"}}),t("el-table-column",{attrs:{label:e.$t("opt"),width:"175px"},scopedSlots:e._u([{key:"default",fn:function(s){return[t("el-button",{staticStyle:{"margin-left":"16px","margin-top":"16px"},attrs:{icon:"el-icon-search",round:"",type:"primary"},on:{click:function(t){return e.orderDetails(s.row)}}},[e._v(" "+e._s(e.$t("moredetails"))+" ")]),t("el-button",{staticStyle:{"margin-left":"16px","margin-top":"16px"},attrs:{icon:"el-icon-tickets",round:"",type:"primary"},on:{click:function(t){return e.orderDsDraw(s.row)}}},[e._v(" "+e._s(e.$t("快递号"))+" ")]),t("el-button",{staticStyle:{"margin-left":"16px","margin-top":"16px"},attrs:{type:"danger",icon:"el-icon-delete",round:""},on:{click:function(t){return e.deleteOrder(s.row.id)}}},[e._v(" "+e._s(e.$t("Cancellationorders"))+" ")])]}}])})],1),t("el-dialog",{attrs:{visible:e.details,fullscreen:""},on:{"update:visible":function(t){e.details=t}}},[t("el-descriptions",{staticClass:"margin-top",attrs:{column:1,size:"medium",border:""}},[t("el-descriptions-item",[t("template",{slot:"label"},[t("i",{staticClass:"el-icon-user"}),e._v(" "+e._s(e.$t("username"))+" ")]),e._v(" "+e._s(e.Corder.username)+" ")],2),t("el-descriptions-item",[t("template",{slot:"label"},[t("i",{staticClass:"el-icon-finished"}),e._v(" "+e._s(e.$t("isConfirmed"))+" ")]),e._v(" "+e._s(e.Corder.isConfirmed)+" ")],2),t("el-descriptions-item",[t("template",{slot:"label"},[t("i",{staticClass:"el-icon-s-promotion"}),e._v(" "+e._s(e.$t("email"))+" ")]),e._v(" "+e._s(e.Corder.email)+" ")],2),t("el-descriptions-item",[t("template",{slot:"label"},[t("i",{staticClass:"el-icon-user"}),e._v(" "+e._s(e.$t("name"))+" ")]),e._v(" "+e._s(e.Corder.name)+" ")],2),t("el-descriptions-item",[t("template",{slot:"label"},[t("i",{staticClass:"el-icon-mobile-phone"}),e._v(" "+e._s(e.$t("phone"))+" ")]),e._v(" "+e._s(e.Corder.phone)+" ")],2),t("el-descriptions-item",[t("template",{slot:"label"},[t("i",{staticClass:"el-icon-office-building"}),e._v(" "+e._s(e.$t("receiveAdress"))+" ")]),e._v(" "+e._s(e.Corder.receiveAdress)+" ")],2),t("el-descriptions-item",[t("template",{slot:"label"},[t("i",{staticClass:"el-icon-plus"}),e._v(" "+e._s(e.$t("deliveryNum"))+" ")]),e._v(" "+e._s(e.Corder.deliveryNum)+" ")],2),t("el-descriptions-item",[t("template",{slot:"label"},[t("i",{staticClass:"el-icon-circle-check"}),e._v(" "+e._s(e.$t("others.mode1"))+" ")]),e._v(" "+e._s(e.Corder.others)+" ")],2),t("el-descriptions-item",[t("template",{slot:"label"},[t("i",{staticClass:"el-icon-shopping-cart-2"}),e._v(" "+e._s(e.$t("weight"))+" ")]),e._v(" "+e._s(e.Corder.weight)+" ")],2),t("el-descriptions-item",[t("template",{slot:"label"},[t("i",{staticClass:"el-icon-time"}),e._v(" "+e._s(e.$t("dateOut"))+" ")]),e._v(" "+e._s(e.Corder.dateOut)+" ")],2),t("el-descriptions-item",[t("template",{slot:"label"},[t("i",{staticClass:"el-icon-money"}),e._v(" "+e._s(e.$t("USD"))+" ")]),e._v(" "+e._s(e.Corder.usd)+" ")],2),t("el-descriptions-item",[t("template",{slot:"label"},[t("i",{staticClass:"el-icon-coin"}),e._v(" "+e._s(e.$t("RUB"))+" ")]),e._v(" "+e._s(e.Corder.rubs)+" ")],2),t("el-descriptions-item",[t("template",{slot:"label"},[t("i",{staticClass:"el-icon-set-up"}),e._v(" "+e._s(e.$t("details"))+" ")]),t("el-tag",{attrs:{size:"small"}},[e._v(e._s(e.Corder.details))])],2),t("el-descriptions-item",[t("template",{slot:"label"},[t("i",{staticClass:"el-icon-chat-line-square"}),e._v(" "+e._s(e.$t("remark"))+" ")]),e._v(" "+e._s(e.Corder.remark)+" ")],2)],1)],1),t("el-dialog",{attrs:{visible:e.drawer,fullscreen:"",width:"40%"},on:{"update:visible":function(t){e.drawer=t}}},[[t("el-form",[t("el-form-item",e._l(e.orderDs.length,(function(s){return t("div",{key:s},[t("h6",[e._v(e._s(e.$t("快递号"))+"."+e._s(s))]),t("el-input",{attrs:{disabled:e.NoeditAlble,"show-word-limit":"",maxlength:"30"},model:{value:e.orderDs[s-1].trackingNumber,callback:function(t){e.$set(e.orderDs[s-1],"trackingNumber",t)},expression:"orderDs[o-1].trackingNumber"}}),t("h6",[e._v(e._s(e.$t("备注"))+"."+e._s(s))]),t("el-input",{attrs:{type:"textarea",disabled:e.NoeditAlble,maxlength:"500","show-word-limit":""},model:{value:e.orderDs[s-1].remark,callback:function(t){e.$set(e.orderDs[s-1],"remark",t)},expression:"orderDs[o-1].remark"}}),t("h6",[e._v(e._s(e.$t("货物类型"))+"."+e._s(s))]),t("el-radio-group",{attrs:{disabled:e.NoeditAlble},model:{value:e.orderDs[s-1].type,callback:function(t){e.$set(e.orderDs[s-1],"type",t)},expression:"orderDs[o-1].type"}},[t("el-radio",{attrs:{label:"快递货(курьер)"}},[e._v(e._s(e.$t("快递货")))]),t("el-radio",{attrs:{label:"鞋子(обувь)"}},[e._v(e._s(e.$t("鞋子")))]),t("el-radio",{attrs:{label:"食品(еда)"}},[e._v(e._s(e.$t("食品")))]),t("el-radio",{attrs:{label:"电子产品(электронное изделие)"}},[e._v(e._s(e.$t("电子产品")))]),t("el-radio",{attrs:{label:"箱包(сумка)"}},[e._v(e._s(e.$t("箱包")))]),t("el-radio",{attrs:{label:"小百货(универмаг)"}},[e._v(e._s(e.$t("小百货")))]),t("el-radio",{attrs:{label:"化妆品(косметические продукты)"}},[e._v(e._s(e.$t("化妆品")))]),t("el-radio",{attrs:{label:"服装(одежда)"}},[e._v(e._s(e.$t("服装")))]),t("el-radio",{attrs:{label:"其他(другой)"}},[e._v(e._s(e.$t("其他")))])],1),e.isUpload[s-1]?t("el-result",{attrs:{icon:"success",title:e.$t("图片已经更新点击查看")}},[t("template",{slot:"extra"},[t("el-button",{attrs:{type:"primary",size:"medium",disabled:!e.isUpload[s-1]},on:{click:function(t){return e.openPhoto(e.orderDs[s-1].id)}}},[e._v(e._s(e.$t("查看照片")))])],1)],2):e._e(),e.isUpload[s-1]?e._e():t("el-result",{attrs:{icon:"info",title:e.$t("包裹还未到达仓库")}},[t("template",{slot:"extra"},[t("el-button",{attrs:{type:"primary",size:"medium",disabled:!e.isUpload[s-1]},on:{click:function(t){return e.openPhoto(e.orderDs[s-1].id)}}},[e._v(e._s(e.$t("查看照片")))])],1)],2),t("el-divider",[t("i",{staticClass:"el-icon-goods"})])],1)})),0)],1)],t("div",{staticStyle:{margin:"auto"}},[t("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{attrs:{type:"primary"},on:{click:function(t){e.drawer=!1}}},[e._v(e._s(e.$t("return")))]),t("el-button",{attrs:{type:"primary",disabled:e.NoeditAlble},on:{click:function(t){e.addOrderDDialogVisible=!0}}},[e._v(e._s(e.$t("新增")))]),t("el-button",{directives:[{name:"no-more-click-ten",rawName:"v-no-more-click-ten"}],attrs:{type:"primary",disabled:e.NoeditAlble},on:{click:e.updateOrderD}},[e._v(e._s(e.$t("更新")))])],1)])],2),t("el-dialog",{attrs:{visible:e.openPhotoDialogVisible,fullscreen:""},on:{"update:visible":function(t){e.openPhotoDialogVisible=t}}},[t("div",{staticClass:"demo-image__lazy"},e._l(e.urls,(function(e){return t("el-image",{key:e,attrs:{src:e,lazy:""}})})),1)]),t("el-dialog",{attrs:{visible:e.addOrderDDialogVisible,fullscreen:""},on:{"update:visible":function(t){e.addOrderDDialogVisible=t}}},[[t("el-form",[t("el-row",[t("el-col",{attrs:{xs:20,sm:18,md:18,lg:18,xl:18}},[t("el-form-item",{attrs:{label:e.$t("deliveryNum")}},[[[t("el-input-number",{attrs:{step:1,"step-strictly":"",max:10,min:1},model:{value:e.addDeliveryNum,callback:function(t){e.addDeliveryNum=t},expression:"addDeliveryNum"}})]]],2)],1)],1),t("el-row",[t("el-col",{attrs:{xs:20,sm:18,md:18,lg:18,xl:18}},[t("el-form-item",e._l(e.addDeliveryNum,(function(s){return t("div",{key:s},[t("el-col",{attrs:{xs:3,sm:3,md:3,lg:3,xl:3}},[t("h3",[e._v("No."+e._s(s))])]),t("el-col",{attrs:{xs:24,sm:24,md:24,lg:24,xl:24}},[t("el-input",{attrs:{placeholder:e.$t("DN"),"show-word-limit":"",maxlength:"30"},model:{value:e.orderDeliverys[s-1].trackingNumber,callback:function(t){e.$set(e.orderDeliverys[s-1],"trackingNumber",t)},expression:"orderDeliverys[o-1].trackingNumber"}})],1),t("el-col",{staticStyle:{"margin-top":"1%"},attrs:{xs:24,sm:24,md:24,lg:24,xl:24}},[t("el-radio-group",{model:{value:e.orderDeliverys[s-1].type,callback:function(t){e.$set(e.orderDeliverys[s-1],"type",t)},expression:"orderDeliverys[o-1].type"}},[t("el-radio",{attrs:{label:"快递货(курьер)"}},[e._v(e._s(e.$t("快递货")))]),t("el-radio",{attrs:{label:"鞋子(обувь)"}},[e._v(e._s(e.$t("鞋子")))]),t("el-radio",{attrs:{label:"食品(еда)"}},[e._v(e._s(e.$t("食品")))]),t("el-radio",{attrs:{label:"电子产品(электронное изделие)"}},[e._v(e._s(e.$t("电子产品")))]),t("el-radio",{attrs:{label:"箱包(сумка)"}},[e._v(e._s(e.$t("箱包")))]),t("el-radio",{attrs:{label:"小百货(универмаг)"}},[e._v(e._s(e.$t("小百货")))]),t("el-radio",{attrs:{label:"化妆品(косметические продукты)"}},[e._v(e._s(e.$t("化妆品")))]),t("el-radio",{attrs:{label:"服装(одежда)"}},[e._v(e._s(e.$t("服装")))]),t("el-radio",{attrs:{label:"其他(другой)"}},[e._v(e._s(e.$t("其他")))])],1)],1),t("el-col",{attrs:{xs:24,sm:24,md:24,lg:24,xl:24}},[t("el-input",{attrs:{type:"textarea",maxlength:"500","show-word-limit":"",placeholder:e.$t("remark")},model:{value:e.orderDeliverys[s-1].remark,callback:function(t){e.$set(e.orderDeliverys[s-1],"remark",t)},expression:"orderDeliverys[o-1].remark"}})],1)],1)})),0)],1)],1)],1)],t("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{directives:[{name:"no-more-click-ten",rawName:"v-no-more-click-ten"}],attrs:{type:"primary"},on:{click:e.addOrderD}},[e._v("确 定")])],1)],2)],1)},i=[],l=(s(7658),s(6369)),a=s(2618),o=s(4471),d=(0,l.defineComponent)({name:"searchorder",data(){return{openPhotoDialogVisible:!1,urls:[],addOrderDDialogVisible:!1,addDeliveryNum:1,orderDeliverys:[],orderId:null,COrder:{id:null,deliveryNum:null},Corder:{},details:!1,NoeditAlble:!1,isUpload:[],orderDs:[],tableData:[],serverIp:a.IV,serverPort:a.Zi,http:a.dJ,uploadUsername:"",uploadOrdername:"",user:"",fileList:[],currentOrdername:"",limitnum:10,numOrders:null,CS:{"text-align":"center","min-width":"250px","word-break":"break-all"},drawer:!1}},created(){this.user=localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{},this.searchOrdersByUsername(),this.orderDeliverys=[];for(let e=0;e<10;e++)this.orderDeliverys.push({trackingNumber:this.$t("DN"),remark:this.$t("remark"),type:"",orderId:null})},methods:{updateData(){this.searchOrdersByUsername()},deleteOrder(e){this.request.delete("/order/orderId/"+e).then((e=>{"200"===e.code?(this.$message.success(this.$t("message.successfullyDeleted").toString()),this.updateData()):this.$message.error(this.$t("message.failedToDelete").toString())}))},async searchOrdersByUsername(){const e=this.$loading({lock:!0,text:"Loading",spinner:"el-icon-loading",background:"rgba(0, 0, 0, 0.8)"});setTimeout((()=>{e.close()}),6e4),this.request.get("/order/username").then((t=>{if(this.tableData=JSON.parse(JSON.stringify(t.data)),0!==this.tableData.length){this.tableData=this.tableData.sort(this.compare);for(let t=0;t<this.tableData.length;t++){switch(this.tableData[t].sendRegion){case"Moscow":this.tableData[t].sendRegion=this.$t("Moscow");break;case"China":this.tableData[t].sendRegion=this.$t("China");break;default:}switch(this.tableData[t].receiveRegion){case"Moscow":this.tableData[t].receiveRegion=this.$t("Moscow");break;case"China":this.tableData[t].receiveRegion=this.$t("China");break;default:}this.tableData[t].others=this.tableData[t].others.split("/");let s="";for(let e=0;e<this.tableData[t].others.length;e++)"保险"===this.tableData[t].others[e]||"自取"===this.tableData[t].others[e]||"木架"===this.tableData[t].others[e]||"防水"===this.tableData[t].others[e]||"托盘"===this.tableData[t].others[e]?s+=this.$t("true"):s+=this.$t("false"),e!==this.tableData[t].others.length-1&&(s+="/");this.tableData[t].others=s,this.tableData[t].isConfirmed,this.tableData[t].isConfirmed=this.$t(this.tableData[t].isConfirmed.toString()),"12-15days"===this.tableData[t].dateOut?this.tableData[t].dateOut=this.$t("time1"):"18-25days"===this.tableData[t].dateOut&&(this.tableData[t].dateOut=this.$t("time2")),this.request.get("/order-delivery/findByorder/"+this.tableData[t].id).then((s=>{"200"===s.code&&(this.tableData[t].orderDs=s.data,t===this.tableData.length-1&&e.close())}))}}else e.close()}))},orderDsDraw(e){this.drawer=!0,this.orderId=e.id,this.COrder.id=e.id,this.COrder.deliveryNum=e.deliveryNum,this.isUpload=[];for(let t=0;t<e.orderDs.length;t++)this.isUpload.push(e.orderDs[t].isUpload);"否"===e.isConfirmed?this.NoeditAlble=!1:"是"===e.isConfirmed?this.NoeditAlble=!0:"Нет"===e.isConfirmed?this.NoeditAlble=!1:"Да"===e.isConfirmed&&(this.NoeditAlble=!0),this.orderDs=e.orderDs},updateOrderD(){this.drawer=!1;for(let e=0;e<this.orderDs.length;e++)this.request.post("/order-delivery/update",this.orderDs[e]).then((e=>{"200"===e.code?this.$message.success(this.$t("optsucc")):"501"===e.code?this.$message.error(this.$t("订单已经确认")):"500"===e.code&&this.$message.error(this.$t("optfail"))}))},openPhoto(e){this.urls=[],this.openPhotoDialogVisible=!0,this.request.get("/order-file/orderDId/"+e).then((e=>{if("200"===e.code){for(let t=0;t<e.data.length;t++)this.urls.push(e.data[t].url);this.AdminDialogVisible=!0}else this.$message.error("加载失败")}))},compare(e,t){return t.id-e.id},addOrderD(){this.COrder.deliveryNum=this.orderDs.length+this.addDeliveryNum,this.request.post("/order/changeOrderNum",this.COrder).then((e=>{if("200"===e.code){for(let e=0;e<this.addDeliveryNum;e++)this.orderDeliverys[e].orderId=this.orderId,o.Z.post("/order-delivery/add",this.orderDeliverys[e]).then((t=>{if("200"===t.code){let t=e+1;this.$message.success("No."+t+"更新成功")}}));this.orderDeliverys=[];for(let e=0;e<10;e++)this.orderDeliverys.push({trackingNumber:this.$t("DN"),remark:this.$t("remark"),type:"",orderId:null})}else this.$message.error(this.$t("optfail"))})),this.drawer=!1,this.addOrderDDialogVisible=!1,this.updateData()},orderDetails(e){this.details=!0,this.Corder=e}}}),n=d,c=s(1001),m=(0,c.Z)(n,r,i,!1,null,null,null),h=m.exports}}]);
//# sourceMappingURL=685.695fc884.js.map