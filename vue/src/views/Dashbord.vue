<template>
  <div>
    <el-row :gutter="10" style="margin-bottom: 60px">
      <el-col :span="4">
        <el-card style="color: #409EFF">
          <div><i class="el-icon-user-solid" /> 用户总数</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
              {{this.orderInfo.numUser}}
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="color: #F56C6C">
          <div><i class="el-icon-money" /> 订单总成交量</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
              {{this.orderInfo.totalOrderTurnover}}
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
            <el-card style="color: #E6A23C">
                <div><i class="el-icon-s-shop" /> 订单总量</div>
                <div style="padding: 10px 0; text-align: center; font-weight: bold">
                    {{this.orderInfo.totalOrder}}
                </div>
            </el-card>
        </el-col>
      <el-col :span="4">
        <el-card style="color: #67C23A">
          <div><i class="el-icon-bank-card" /> 人民币兑卢布汇率</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold">
              <el-button type="success" @click="dialogVisible = true">{{this.orderInfo.exchangeRates}}</el-button>
          </div>
        </el-card>
      </el-col>
        <el-col :span="4">
            <el-card style="color: #67C23A">
                <div><i class="el-icon-bank-card" /> 美元兑人民币汇率</div>
                <div style="padding: 10px 0; text-align: center; font-weight: bold">
                    <el-button type="success" @click="dialogVisibleUs = true">{{this.orderInfo.usrmbRates}}</el-button>
                </div>
            </el-card>
        </el-col>
    </el-row>

    <el-row :gutter="10" style="margin-bottom: 60px">
        <el-col :span="12">
            <div style="height: 250px">
                <el-card class="box-card">
                    <div style="margin-top: 1%;margin-left: 50%; color: #606266;height: 20px">
                        <span>{{ $t("newgoods") }}</span>
                    </div>
                    <el-button type="primary" @click="dialogVisiblep1 = true">编 辑</el-button>
                    <div style="height: 250px">
                        <el-image
                                style="width: 80%; height: 80%; margin-top: 5%;margin-left: 10%;"
                                align="center"
                                :src="photoandannouncement.photo1"
                                fit="contain">
                        </el-image>
                    </div>
                </el-card>
            </div>
        </el-col>
        <el-col :span=12>
            <el-card class="box-card">
                <template>
                    <div style="margin-top: 1%;margin-left: 50%; color: #606266;height: 20px">
                        <span>{{ $t("taobaodewu") }}</span>
                    </div>
                    <el-button type="primary" @click="dialogVisiblep2 = true">编 辑</el-button>
                    <el-carousel :interval="4000" type="card" height="220px">
                        <el-carousel-item >
                            <el-image
                                    style="width: 80%; height: 80%; margin-top: 5%;margin-left: 10%;"
                                    align="center"
                                    :src="photoandannouncement.photo2"
                                    fit="contain">
                            </el-image>
                        </el-carousel-item>
                        <el-carousel-item >
                            <el-image
                                    style="width: 80%; height: 80%; margin-top: 5%;margin-left: 10%;"
                                    align="center"
                                    :src="photoandannouncement.photo3"
                                    fit="contain">
                            </el-image>
                        </el-carousel-item>
                    </el-carousel>
                </template>
            </el-card>
        </el-col>
    </el-row>
    <el-row :gutter="10" style="margin-bottom: 60px">
      <el-col :span="16">
          <el-card >
              <div v-html="html"></div>
              <el-button type="primary" @click="dialogVisibleA1 = true">编 辑</el-button>
          </el-card>
      </el-col>
      <el-col :span="8">
          <el-card >
              <h3 style="text-align: center">{{photoandannouncement.announcement1.title}}</h3>
              <p style="text-align: center">{{photoandannouncement.announcement1.p1}}</p>
              <p style="text-align: center">{{photoandannouncement.announcement1.p2}}</p>
              <el-button type="primary" @click="dialogVisibleA2 = true">编 辑</el-button>
          </el-card>
      </el-col>
    </el-row>
      <el-dialog
              title="更改Rub汇率"
              :visible.sync="dialogVisible"
              width="30%">
          <el-input v-model="orderInfo.exchangeRates" placeholder="请输入内容"></el-input>
          <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="changeRatesFunc">确 定</el-button>
          </span>
      </el-dialog>
      <el-dialog
              title="更改Dollar汇率"
              :visible.sync="dialogVisibleUs"
              width="30%">
          <el-input v-model="orderInfo.usrmbRates" placeholder="请输入内容"></el-input>
          <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisibleUs = false">取 消</el-button>
          <el-button type="primary" @click="changeUsrmbRatesFunc">确 定</el-button>
          </span>
      </el-dialog>

      <el-dialog
              title="更改公告1"
              :before-close="handleClose"
              :visible.sync="dialogVisibleA1"
              width="80%">
          <div style="border: 1px solid #ccc;">
            <Toolbar
                    style="border-bottom: 1px solid #ccc"
                    :editor="editor"
                    :defaultConfig="toolbarConfig"
                    :mode="mode"
            />
            <Editor
                    style="height: 500px; overflow-y: hidden;"
                    v-model="html"
                    :defaultConfig="editorConfig"
                    :mode="mode"
                    @onCreated="onCreated"
                    @onChange="onChange"
            />
          </div>
          <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisibleA1 = false">取 消</el-button>
          <el-button type="primary" @click="changeInfoA1">确 定</el-button>
          </span>
      </el-dialog>

      <el-dialog
              title="更改公告2"
              :visible.sync="dialogVisibleA2"
              width="30%">
          <el-input type="textarea" v-model="photoandannouncement.announcement1.title" placeholder="请输入内容"></el-input>
          <div style="margin: 20px 0;"></div>
          <el-input type="textarea" v-model="photoandannouncement.announcement1.p1" placeholder="请输入内容"></el-input>
          <div style="margin: 20px 0;"></div>
          <el-input type="textarea" v-model="photoandannouncement.announcement1.p2" placeholder="请输入内容"></el-input>
          <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisibleA2 = false">取 消</el-button>
          <el-button type="primary" @click="changeInfoA2">确 定</el-button>
          </span>
      </el-dialog>




      <el-dialog
              title="更改展示1"
              :visible.sync="dialogVisiblep1"
              width="30%"
              center>
          <el-upload
                  class="upload-demo"
                  accept="image/jpeg,image/png,image/jpg"
                  ref="upload"
                  :auto-upload="true"
                  :file-list="fileListp1"
                  :before-upload="onBeforeUploadImagep1"
                  :on-success="uploadSuccessp1"
                  :on-change="loadJsonFromFilep1"
                  :limit="limitnump1"
                  :on-exceed="exceedFilep1"
                  :on-remove="removeFilep1"
                  :on-error="uploadFileErrorp1"
                  :action="http + '://'+ serverIp + ':' + serverPort + '/file/upload'">
              <el-button slot="trigger" size="small" type="primary">选取照片</el-button>
              <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
      </el-dialog>



      <el-dialog
              title="更改展示2"
              :visible.sync="dialogVisiblep2"
              width="30%"
              center>
          <el-upload
                  class="upload-demo"
                  accept="image/jpeg,image/png,image/jpg"
                  ref="upload"
                  :auto-upload="true"
                  :file-list="fileListp2"
                  :before-upload="onBeforeUploadImagep2"
                  :on-success="uploadSuccessp2"
                  :on-change="loadJsonFromFilep2"
                  :limit="limitnump2"
                  :on-exceed="exceedFilep2"
                  :on-remove="removeFilep2"
                  :on-error="uploadFileErrorp2"
                  :action="http + '://'+ serverIp + ':' + serverPort + '/file/upload'">
              <el-button slot="trigger" size="small" type="primary">选取照片</el-button>
              <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
      </el-dialog>
  </div>
</template>

<script>
import {serverIp, serverPort,http} from "../../public/config";
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
export default {
    components: { Editor, Toolbar },
    name: "Dashbord",
    data() {
      return {
        //富文本公告
        editor: null,
        html: '<p>请编辑</p>',
        toolbarConfig: { },
        editorConfig: { placeholder: '请输入内容...',
            maxLength : 100000,
            MENU_CONF:{
                uploadImage:{
                    fieldName: 'file',
                    server: http+"://"+serverIp+":" + serverPort + "/file/uploadImg",
                    timeout: 30 * 1000,
                    allowedFileTypes: ['image/*'],
                }
            }
        },
        mode: 'default', // or 'simple'
        //订单信息
        orderInfo: {
            numUser: 0,
            totalOrderTurnover: 0,
            totalOrder: 0,
            exchangeRates: null,
            usrmbRates: null,
        },
        dialogVisible : false,
        dialogVisibleUs: false,
        dialogVisibleA1: false,
        dialogVisibleA2: false,
        dialogVisiblep1: false,
        dialogVisiblep2: false,
        photoandannouncement: {
            photo1: 'https://www.ups.com/assets/resources/supplychain/images/350x197/m15-350x197-fast-fulfillment-assembly.jpg',
            photo2: 'https://www.ups.com/assets/resources/supplychain/images/350x197/m15-350x197-fast-fulfillment-assembly.jpg',
            photo3: 'https://www.ups.com/assets/resources/supplychain/images/350x197/m15-350x197-fast-fulfillment-assembly.jpg',
            announcement1: {title: "None", p1:"None", p2:"None"},
        },
        fileListp1: [],
        limitnump1: 1,
        limitnump2: 2,
        fileListp2: [],
        responseAssemblies: ["",""],
        flag: 0,
        serverIp: serverIp,
        serverPort: serverPort,
        http: http
    }
  },
    mounted() {
        this.updateExchangeRate()
        this.updateUsrmbRate()
        this.usernum()
        this.ordernum()
        this.totalOrderTurnovernum()
        this.getphotoandannouncement()
    },
    created() {
    },
    //富文本
    beforeDestroy() {
        const editor = this.editor
        if (editor == null) return
        editor.destroy() // 组件销毁时，及时销毁编辑器
    },
    methods: {
        //对话框关闭提示
        handleClose(done) {
            this.$confirm('内容还没保存，是否关闭对话框？')
                .then(_ => {
                    done();
                })
                .catch(_ => {});
        },
      //富文本
        onCreated(editor) {
            this.editor = Object.seal(editor) // 一定要用 Object.seal() ，否则会报错
        },
        onChange(editor){

        },
        //禁止页面滑动
        stop(){
            var box=function(e)
            {passive: false ;};
            document.body.style.overflow='hidden';
            document.addEventListener("touchmove",box,false);
        },
        changeRatesFunc()
        {
            this.request.get("/others/1/"+this.orderInfo.exchangeRates).then(res => {
                if (res.code == '200')
                {
                    this.updateExchangeRate()
                    this.dialogVisible = false
                    this.$message.success("保存成功")
                }
            })

        },//改美元的汇率
        changeUsrmbRatesFunc()
        {
            this.request.get("/others/1/UsRmbSet/"+this.orderInfo.usrmbRates).then(res => {
                if (res.code == '200')
                {
                    this.updateExchangeRate()
                    this.dialogVisibleUs = false
                    this.$message.success("保存成功")
                }
            })

        },
        updateExchangeRate()
        {
            this.request.get("/others/1").then(res => {
                if (res.code == '200')
                {
                    this.orderInfo.exchangeRates = res.data
                }
            })
        },
        updateUsrmbRate()
        {
            this.request.get("/others/1/UsRmbGet/1").then(res => {
                if (res.code == '200')
                {
                    this.orderInfo.usrmbRates = res.data
                }
            })
        },
        usernum(){
            this.request.get("/user/usernum").then(res => {
                if (res.code == '200')
                {
                    this.orderInfo.numUser = res.data
                }
            })
        },
        ordernum(){
            this.request.get("/order/ordernum").then(res => {
                if (res.code == '200')
                {
                    this.orderInfo.totalOrder = res.data
                }
            })
        },
        totalOrderTurnovernum(){
            this.request.get("/order/orderturnover").then(res => {
                if (res.code == '200')
                {
                    this.orderInfo.totalOrderTurnover = res.data
                }
            })
        },
        getphotoandannouncement(){
            this.request.get("/others/getphotoandannouncement").then(res => {
                if (res.code === '200')
                {
                    this.photoandannouncement = res.data
                    this.photoandannouncement.announcement1 = JSON.parse(res.data.announcement1)
                    this.responseAssemblies[0] = res.data.photo2
                    this.responseAssemblies[1] = res.data.photo3
                }
            })
            this.request.get("/article/"+ 1).then(res => {
                console.log(res.data)
                    if (res.code === '200')
                    {
                        this.html = res.data.content
                    }
            })
        },

        changeInfoA1(){
            let article = {
                id: 1,
                title: "",
                content: this.html
            }
            this.request.post("/article", article).then(res =>{
                if (res.code === '200'){
                    this.getphotoandannouncement()
                    this.$message.success("修改成功")
                    this.dialogVisibleA1 = false
                } else {
                    this.$message.error("编辑失败")
                }

            })
        },
        changeInfoA2(){
            let data = {
                id: 1,
                photo1: this.photoandannouncement.photo1,
                photo2: this.photoandannouncement.photo2,
                photo3: this.photoandannouncement.photo3,
                announcement1: JSON.stringify(this.photoandannouncement.announcement1),
            }
            this.request.post("/others", data).then(res =>{
                if (res === true){
                    this.getphotoandannouncement()
                    this.$message.success("修改成功")
                    this.dialogVisibleA2 = false
                } else {
                    this.$message.error("编辑失败")
                }

            })
        },

        //图片上传
        uploadFileErrorp1(err, file, fileList) {
            this.$message.error(this.$t('message.uploadFailed').toString())
        },
        exceedFilep1(files, fileList) {
            this.$message.error(this.$t('message.numberDocumentsAllowed') + ':' + this.limitnump1);
        },
        removeFilep1(file, fileList) {
            this.fileListp1 = fileList;
        },
        loadJsonFromFilep1(file, fileList) {
            this.fileListp1 = fileList
        },
        onBeforeUploadImagep1(file) {
            const isIMAGE = file.type === 'image/jpeg' || 'image/jpg' || 'image/png'
            const isLt1M = file.size / 1024 / 1024 < 5
            if (!isIMAGE) {
                this.$message.error(this.$t('message.uploadFilesCanOnlyInImageFormat').toString())
            }
            if (!isLt1M) {
                this.$message.error(this.$t('message.uploadFilesCanOnlyInImageFormat') + '1MB!')
            }
            return isIMAGE && isLt1M
        },
        uploadSuccessp1(response, file, fileList) {
            let data = {
                id: 1,
                photo1: response,
                photo2: this.photoandannouncement.photo2,
                photo3: this.photoandannouncement.photo3,
                announcement1: JSON.stringify(this.photoandannouncement.announcement1),
            }
            this.request.post("/others", data).then(res =>{
                if (res === true){
                    this.getphotoandannouncement()
                    this.$message.success("修改成功")
                } else {
                    this.$message.error("编辑失败")
                }

            })
        },
        uploadFileErrorp2(err, file, fileList) {
            this.$message.error(this.$t('message.uploadFailed').toString())
        },
        exceedFilep2(files, fileList) {
            this.$message.error(this.$t('message.numberDocumentsAllowed') + ':' + this.limitnump2);
        },
        removeFilep2(file, fileList) {
            this.fileListp2 = fileList;
        },
        loadJsonFromFilep2(file, fileList) {
            this.fileListp2 = fileList
        },
        onBeforeUploadImagep2(file) {
            const isIMAGE = file.type === 'image/jpeg' || 'image/jpg' || 'image/png'
            const isLt1M = file.size / 1024 / 1024 < 5
            if (!isIMAGE) {
                this.$message.error(this.$t('message.uploadFilesCanOnlyInImageFormat').toString())
            }
            if (!isLt1M) {
                this.$message.error(this.$t('message.uploadFilesCanOnlyInImageFormat') + '1MB!')
            }
            return isIMAGE && isLt1M
        },
        uploadSuccessp2(response, file, fileList) {
            if (this.flag === 2)
            {
                this.flag = 0
            }
            this.responseAssemblies[this.flag] = response
            this.flag = this.flag +1
            let data = {
                id: 1,
                photo1: this.photoandannouncement.photo1,
                photo2: this.responseAssemblies[0],
                photo3: this.responseAssemblies[1],
                announcement1: JSON.stringify(this.photoandannouncement.announcement1),
            }
            this.request.post("/others", data).then(res =>{
                if (res === true){
                    this.getphotoandannouncement()
                    this.$message.success("修改成功")
                } else {
                    this.$message.error("编辑失败")
                }

            })
        },
        logout(){
            this.$emit("logout")
        }
    }
}
</script>

<style scoped>
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}
.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}
.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}
.avatar {
    width: 178px;
    height: 178px;
    display: block;
}
</style>
<style src="@wangeditor/editor/dist/css/style.css"></style>