"use strict";(self["webpackChunkvue"]=self["webpackChunkvue"]||[]).push([[307],{7307:function(e,t,a){a.r(t),a.d(t,{default:function(){return u}});var l=function(){var e=this,t=e._self._c;return t("div",[t("div",{staticStyle:{margin:"10px 0"}},[t("el-input",{staticStyle:{width:"200px"},attrs:{placeholder:"请输入名称","suffix-icon":"el-icon-search"},model:{value:e.name,callback:function(t){e.name=t},expression:"name"}}),t("el-input",{staticStyle:{width:"200px"},attrs:{placeholder:"请输入文件类型","suffix-icon":"el-icon-search"},model:{value:e.name,callback:function(t){e.name=t},expression:"name"}}),t("el-input",{staticStyle:{width:"200px"},attrs:{placeholder:"请输入上传人","suffix-icon":"el-icon-search"},model:{value:e.name,callback:function(t){e.name=t},expression:"name"}}),t("el-button",{staticClass:"ml-5",attrs:{type:"primary"},on:{click:e.load}},[e._v("搜索")]),t("el-button",{attrs:{type:"warning"},on:{click:e.reset}},[e._v("重置")])],1),t("div",{staticStyle:{margin:"10px 0"}},[t("el-upload",{staticStyle:{display:"inline-block"},attrs:{action:e.http+"://"+e.serverIp+":"+e.serverPort+"/file/upload","show-file-list":!1,"on-success":e.handleFileUploadSuccess}},[t("el-button",{staticClass:"ml-5",attrs:{type:"primary"}},[e._v("上传文件 "),t("i",{staticClass:"el-icon-top"})])],1),t("el-popconfirm",{staticClass:"ml-5",attrs:{"confirm-button-text":"确定","cancel-button-text":"我再想想",icon:"el-icon-info","icon-color":"red",title:"您确定批量删除这些数据吗？"},on:{confirm:e.delBatch}},[t("el-button",{attrs:{slot:"reference",type:"danger"},slot:"reference"},[e._v("批量删除 "),t("i",{staticClass:"el-icon-remove-outline"})])],1)],1),t("el-table",{attrs:{data:e.tableData,border:"",stripe:"","header-cell-class-name":"headerBg",height:"750"},on:{"selection-change":e.handleSelectionChange}},[t("el-table-column",{attrs:{type:"selection",width:"55"}}),t("el-table-column",{attrs:{prop:"id",label:"ID",width:"80"}}),t("el-table-column",{attrs:{prop:"name",label:"文件名称"}}),t("el-table-column",{attrs:{prop:"type",label:"文件类型"}}),t("el-table-column",{attrs:{prop:"size",label:"文件大小(kb)"}}),t("el-table-column",{attrs:{prop:"size",label:"上传人"}}),t("el-table-column",{attrs:{prop:"size",label:"上传时间"}}),t("el-table-column",{attrs:{label:"启用",width:"65"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("el-switch",{attrs:{"active-color":"#13ce66","inactive-color":"#ccc"},on:{change:function(t){return e.changeEnable(a.row)}},model:{value:a.row.enable,callback:function(t){e.$set(a.row,"enable",t)},expression:"scope.row.enable"}})]}}])}),t("el-table-column",{attrs:{label:"操作",width:"200",align:"center"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.download(a.row.url)}}},[e._v("下载")]),t("el-popconfirm",{staticClass:"ml-5",attrs:{"confirm-button-text":"确定","cancel-button-text":"我再想想",icon:"el-icon-info","icon-color":"red",title:"您确定删除吗？"},on:{confirm:function(t){return e.del(a.row.id)}}},[t("el-button",{attrs:{slot:"reference",type:"danger"},slot:"reference"},[e._v("删除 "),t("i",{staticClass:"el-icon-remove-outline"})])],1)]}}])})],1),t("div",{staticStyle:{padding:"10px 0"}},[t("el-pagination",{attrs:{"current-page":e.pageNum,"page-sizes":[2,5,10,20],"page-size":e.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:e.total},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1)],1)},n=[],s=a(2618),i={name:"File",data(){return{tableData:[],name:"",multipleSelection:[],pageNum:1,pageSize:10,total:0,serverIp:s.IV,serverPort:s.Zi,http:s.dJ}},created(){this.load(),this.stop()},methods:{load(){this.request.get("/file/page",{params:{pageNum:this.pageNum,pageSize:this.pageSize,name:this.name}}).then((e=>{this.tableData=e.data.records,this.total=e.data.total}))},changeEnable(e){this.request.post("/file/update",e).then((e=>{"200"===e.code&&this.$message.success("操作成功")}))},del(e){this.request.delete("/file/"+e).then((e=>{"200"===e.code?(this.$message.success("删除成功"),this.load()):this.$message.error("删除失败")}))},handleSelectionChange(e){this.multipleSelection=e},delBatch(){let e=this.multipleSelection.map((e=>e.id));this.request.post("/file/del/batch",e).then((e=>{"200"===e.code?(this.$message.success("批量删除成功"),this.load()):this.$message.error("批量删除失败")}))},reset(){this.name="",this.load()},handleSizeChange(e){this.pageSize=e,this.load()},handleCurrentChange(e){this.pageNum=e,this.load()},handleFileUploadSuccess(e){this.load()},download(e){window.open(e)},stop(){var e=function(e){};document.body.style.overflow="hidden",document.addEventListener("touchmove",e,!1)}}},o=i,c=a(1001),r=(0,c.Z)(o,l,n,!1,null,"10f276ce",null),u=r.exports}}]);
//# sourceMappingURL=307.534b22d4.js.map