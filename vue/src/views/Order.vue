<script>
import {defineComponent} from 'vue'
import {serverIp, serverPort,http} from "../../public/config";

export default defineComponent({
    name: "order",
    data() {
        return {
            tableData: [],
            serverIp: serverIp,
            serverPort: serverPort,
            http: http,
            centerDialogVisible: false,
            uploadUsername: '',
            uploadOrdername: '',
            user: '',
            fileList: [],
            currentOrdername: '',
            limitnum: 10,
            UserDialogVisible: false,
            urls: [],
            AdminDialogVisible: false,
            pageNum: 1,
            pageSize: 10,
            total: 0,
            username: '',
            name: '',
            options: [{
                value: 1,
                label: '订单已提交',
                disabled: true
            }, {
                value: 2,
                label: '订单等待付款',
                disabled: true
            }, {
                value: 3,
                label: '已发出'
            }, {
                value: 4,
                label: '已到达'
            }, {
                value: 5,
                label: '待取件'
            }, {
                value: 6,
                label: '已取件'
            }],
            options1: [{
                value: false,
                label: '否'
            }, {
                value: true,
                label: '是'
            }],
            editDialogVisible: false,
            form: {
                id: null,
                orderName: null,
                isConfirmed: null,
                orderStatus: null,
                email:null,
                phone: null,
                receiveAdress: null,
                rmb: null,
                rubs: null,
                weight: null,
            },
        }
    },
    created() {
        this.user=localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
        this.searchOrdersByUsername()
    },
    methods:
        {
            //上传照片
            handleAddPhoto(ordername){
                this.centerDialogVisible = true
                this.currentOrdername = ordername
            },
            updateData(){
                this.searchOrdersByUsername()
            },
            deleteOrder(orderName){
                try {
                    this.request.delete("/order/ordername/" + orderName).then(res => {
                        if (res.code === '200'){
                            this.$message.success("删除成功")
                            this.updateData()
                        }
                    })
                } catch (e) {
                    this.$message.error("删除失败")
                }
            },
            uploadFileError(err, file, fileList){
                this.$message.error("上传失败！")
            },
            exceedFile(files, fileList){
                this.$message.error('只能上传'+this.limitnum+'个文件');
            },
            removeFile(file,fileList) {
                this.fileList=fileList;
            },
            loadJsonFromFile(file, fileList) {
                this.fileList = fileList
            },
            onBeforeUploadImage(file) {
                const isIMAGE = file.type === 'image/jpeg' || 'image/jpg' || 'image/png'
                const isLt1M = file.size / 1024 / 1024 < 5
                if (!isIMAGE) {
                    this.$message.error('上传文件只能是图片格式!')
                }
                if (!isLt1M) {
                    this.$message.error('上传文件大小不能超过 1MB!')
                }
                return isIMAGE && isLt1M
            },
            uploadSuccess(response, file, fileList){
                let order_file = {
                    "orderName": this.currentOrdername,
                    "userName": '',
                    "url": response
                }
                try {
                    this.request.post("/order-file", order_file).then(res => {
                        if (res.code === '200') {
                            this.searchOrdersByUsername()
                            this.$message.success("上传成功")
                        }
                    })
                    this.searchOrdersByUsername()
                } catch (e){
                    this.$message.error("上传失败")
                    this.request.delete("/file/url/"+ response).then(res => {

                    })
                }
            },
            //加载表单
            searchOrdersByUsername(){
                this.request.get("/order/page", {
                    params: {
                        pageNum: this.pageNum,
                        pageSize: this.pageSize,
                        username: this.username,
                        name: this.name
                    }
                }).then(res => {
                    this.tableData = JSON.parse(JSON.stringify(res.data))
                    if (this.tableData.total === 0)
                    {
                        return
                    }
                    this.pageNum = this.tableData.current
                    this.pageSize = this.tableData.size
                    this.total = this.tableData.total
                    this.tableData = this.tableData.records
                    const newProperty1='userUpload'
                    const newProperty2='adminUpload'
                    for (let i = 0; i < this.tableData.length; i++) {
                        this.request.get("/order-file/ordername/" + this.tableData[i].orderName).then(res => {
                            this.tableData[i][newProperty1] = []
                            this.tableData[i][newProperty2] = []
                            if (res.data != null)
                            {
                                for(let k = 0; k < res.data.length; k++){
                                    if (res.data[k].adminUp === false){
                                        this.tableData[i][newProperty1].push(res.data[k].url)
                                    } else {
                                        this.tableData[i][newProperty2].push(res.data[k].url)
                                    }
                                }
                            }
                        })
                        ///保险自提
                        this.tableData[i].others = [this.tableData[i].others[0] + this.tableData[i].others[1], this.tableData[i].others[3] + this.tableData[i].others[4]]
                        //是否确认
                        if (this.tableData[i].isConfirmed === true ){
                            this.tableData[i].isConfirmed = '是'
                        } else {
                            this.tableData[i].isConfirmed = '否'
                        }
                        //
                        if (this.tableData[i].receiveRegion === "Moscow" ){
                            this.tableData[i].receiveRegion = '莫斯科'
                        }
                        if (this.tableData[i].receiveRegion === "Guangdong" ){
                            this.tableData[i].receiveRegion = '广东'
                        }
                        if (this.tableData[i].sendRegion === "Moscow" ){
                            this.tableData[i].sendRegion = '莫斯科'
                        }
                        if (this.tableData[i].sendRegion === "Guangdong" ){
                            this.tableData[i].sendRegion = '广东'
                        }
                        //快递类型
                        if (this.tableData[i].dateOut === "12-15days") {
                            this.tableData[i].dateOut = this.$t('time1')
                        } else if (this.tableData[i].dateOut === "18-25days"){
                            this.tableData[i].dateOut = this.$t('time2')
                        }
                    }
                })
            },
            showUserPhoto(urls) {
                this.UserDialogVisible = true
                this.urls = urls

            },
            showAdminPhoto(urls){
                this.AdminDialogVisible = true
                this.urls = urls
            },
            confirmeOrder(order){
                order = {
                    id: order.id,
                    orderName: order.orderName,
                    isConfirmed: true
                }
                this.request.post("/order/update", order).then(res => {
                    if (res.code === '200'){
                        this.searchOrdersByUsername()
                        this.$message.success("保存成功")
                    }
                    else {
                        this.$message.error(res.msg)
                    }
                })
                this.request.get("/order/email/ok/" + order.id).then(res => {
                    if (res.code === '200') {
                        this.$message.success("邮件发送成功")
                    }else {
                        this.$message.error("邮件发送失败")
                    }
                })
            },
            handleSizeChange(pageSize) {
                this.pageSize = pageSize
                this.searchOrdersByUsername()
            },
            handleCurrentChange(pageNum) {
                this.pageNum = pageNum
                this.searchOrdersByUsername()
            },
            reset(){
                this.username=""
                this.name=""
                this.searchOrdersByUsername()
            },
            //编辑订单
            editOrder(order){
                this.editDialogVisible = true
                this.form.id = order.id
                this.form.orderName = order.orderName
                if (order.isConfirmed === "是")
                    this.form.isConfirmed = true
                else
                    this.form.isConfirmed = false
                this.form.orderStatus = order.orderStatus
                this.form.email = order.email
                this.form.phone = order.phone
                this.form.receiveAdress = order.receiveAdress
                this.form.weight = order.weight
                this.form.rmb = order.rmb
                this.form.rubs = order.rubs
            },
            updateOrder(){
                this.request.post("/order/update", this.form).then(res => {
                    if (res.code === '200') {
                        this.searchOrdersByUsername()
                        this.editDialogVisible = false
                        this.$message.success("上传成功")
                    }else {
                        this.$message.error(res.msg)
                    }
                })
            },
            sendmail(order){
                this.request.get("/order/email/" + order.id).then(res => {
                    if (res.code === '200') {
                        this.$message.success("发送成功")
                    }else {
                        this.$message.error("操作失败")
                    }
                })
            },
            sendmailpay(order){
                this.request.get("/order/email/pay/" + order.id).then(res => {
                    if (res.code === '200') {
                        this.$message.success("发送成功")
                    }else {
                        this.$message.error("操作失败")
                    }
                })
            },
            exp() {
                window.open(`${http}://${serverIp}:${serverPort}/order/export`)
            },
        }
})
</script>

<template>
    <div>
        <div style="margin: 10px 0">
            <el-input style="width: 200px" placeholder="按用户名搜索" suffix-icon="el-icon-search" v-model="username"></el-input>
            <el-input style="width: 200px" placeholder="按用姓名搜索" suffix-icon="el-icon-search" v-model="name"></el-input>
            <el-button class="ml-5" type="primary" @click="searchOrdersByUsername">搜索</el-button>
            <el-button type="warning" @click="reset">重置</el-button>
            <el-button type="primary" @click="exp" class="ml-5">导出 <i class="el-icon-top"></i></el-button>
        </div>

        <el-table
            header-cell-style="background-color: #DCDFE6; color: #606266;"
            :data="tableData"
            border
            style="width: 100%; border-radius:25px "
            height="720"
            width="100%"
            size="medium"
            default-expand-all="ture">
            <el-table-column type="expand" align-center="true">
                <template slot-scope="props">

                    <el-descriptions column="3" size="mini" border>
                        <template slot="extra">
                            <el-button v-no-more-click-ten type="primary" size="mini" @click="sendmailpay(props.row)">发送支付邮件</el-button>
                            <el-button v-no-more-click-ten type="primary" size="mini" @click="sendmail(props.row)">发送邮件</el-button>
                            <el-button v-no-more-click-ten type="primary" size="mini" @click="editOrder(props.row)">编辑订单</el-button>
                            <el-button v-no-more-click-ten type="primary" size="mini" @click="confirmeOrder(props.row)">确认订单</el-button>
                            <el-button v-no-more-click-ten type="primary" size="mini" @click="showAdminPhoto(props.row.adminUpload)">查看管理员上传</el-button>
                            <el-button v-no-more-click-ten type="primary" size="mini" @click="showUserPhoto(props.row.userUpload)">查看用户上传</el-button>
                            <el-button v-no-more-click-ten type="primary" size="mini" @click="handleAddPhoto(props.row.orderName)">上传照片</el-button>
                            <el-popconfirm
                                class="ml-5"
                                confirm-button-text='确定'
                                cancel-button-text='我再想想'
                                icon="el-icon-info"
                                icon-color="red"
                                title="您确定批量删除这些数据吗？"
                                @confirm="deleteOrder(props.row.orderName)"
                            >
                            <el-button type="danger" slot="reference" size="mini">撤销订单</el-button>
                            </el-popconfirm>
                        </template>
                        <el-descriptions-item>
                            <template slot="label">
                                <i class="el-icon-user"></i>
                                姓名
                            </template>
                            {{ props.row.name }}
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template slot="label">
                                <i class="el-icon-mobile-phone"></i>
                                手机号
                            </template>
                            {{ props.row.phone }}
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template slot="label">
                                <i class="el-icon-location-outline"></i>
                                物品数量和重量
                            </template>
                            {{props.row.weight}}kg，{{ props.row.deliveryNum }}个
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template slot="label">
                                <i class="el-icon-tickets"></i>
                                收货地址
                            </template>
                            <el-tag size="small">{{ props.row.receiveAdress}}</el-tag>
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template slot="label">
                                <i class="el-icon-office-building"></i>
                                寄货站点
                            </template>
                            {{props.row.sendRegion}}
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template slot="label">
                                <i class="el-icon-office-building"></i>
                                取货站点
                            </template>
                            {{ props.row.receiveRegion}}
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template slot="label">
                                <i class="el-icon-tickets"></i>
                                邮箱
                            </template>
                            <el-tag size="small">{{ props.row.email}}</el-tag>
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template slot="label">
                                <i class="el-icon-office-building"></i>
                                物品详情
                            </template>
                            {{ props.row.details}}
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template slot="label">
                                <i class="el-icon-office-building"></i>
                                备注
                            </template>
                            {{ props.row.remark}}
                        </el-descriptions-item>
                    </el-descriptions>
                    <template>
                        <div style="margin-top: 10px">
                            <el-steps :space="200" :active="props.row.orderStatus" finish-status="success" align-center="true">
                                <el-step title="订单已提交"></el-step>
                                <el-step title="订单等待付款"></el-step>
                                <el-step :title="'已从' + props.row.sendRegion + '发出'"></el-step>
                                <el-step :title="'已到达' + props.row.receiveRegion"></el-step>
                                <el-step title="待取件"></el-step>
                                <el-step title="已取件"></el-step>
                            </el-steps>
                        </div>
                    </template>
                </template>
            </el-table-column>
            <el-table-column
                label="用户名"
                prop="username">
            </el-table-column>
            <el-table-column
                label="订单号"
                prop="orderName">
            </el-table-column>
            <el-table-column
                label="保险"
                prop="others[0]">
            </el-table-column>
            <el-table-column
                label="自提"
                prop="others[1]">
            </el-table-column>
            <el-table-column
                label="快递类型"
                prop="dateOut">
            </el-table-column>
            <el-table-column
                label="创建时间"
                prop="createTime">
            </el-table-column>
            <el-table-column
                label="订单确认"
                prop="isConfirmed">
            </el-table-column>
        </el-table>

        <div style="padding: 10px 0">
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pageNum"
                :page-sizes="[5, 10, 15, 20]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
            </el-pagination>
        </div>

        <el-dialog
            title="上传照片"
            :visible.sync="centerDialogVisible"
            width="30%"
            center>
            <el-upload
                class="upload-demo"
                accept="image/jpeg,image/png,image/jpg"
                ref="upload"
                :auto-upload="true"
                :file-list="fileList"
                :before-upload="onBeforeUploadImage"
                :on-success="uploadSuccess"
                :on-change="loadJsonFromFile"
                :limit="limitnum"
                :on-exceed="exceedFile"
                :on-remove="removeFile"
                :on-error="uploadFileError"
                :action="http + '://'+ serverIp + ':' + serverPort + '/file/upload'">
                <el-button slot="trigger" size="small" type="primary">选取照片</el-button>
                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
            </el-upload>
        </el-dialog>

        <el-dialog
            title="我的上传"
            :visible.sync="UserDialogVisible"
            width="50%">
            <div class="demo-image__lazy">
                <el-image v-for="url in urls" :key="url" :src="url" lazy preview-src-list></el-image>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="UserDialogVisible = false">返 回</el-button>
            </span>
        </el-dialog>

        <el-dialog
            title="我的上传"
            :visible.sync="AdminDialogVisible"
            width="50%">
            <div class="demo-image__lazy">
                <el-image v-for="url in urls" :key="url" :src="url" lazy preview-src-list></el-image>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="AdminDialogVisible = false">返 回</el-button>
            </span>
        </el-dialog>

        <el-dialog
            title="编辑订单"
            :visible.sync="editDialogVisible"
            width="50%">
                <el-form label-width="80px" size="small">
                    <el-form-item label="订单确认">
                        <el-select clearable v-model="form.isConfirmed" placeholder="确认订单" style="width: 100%">
                            <el-option v-for="item in options1" :key="item.value" :label="item.label" :value="item.value"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="状态">
                        <el-select clearable v-model="form.orderStatus" placeholder="请选择订单状态" style="width: 100%">
                            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="重量(kg)">
                        <el-input v-model="form.weight" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱">
                        <el-input v-model="form.email" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="电话">
                        <el-input v-model="form.phone" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="常用快递地址">
                        <el-input v-model="form.receiveAdress" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="应付卢布">
                        <el-input v-model="form.rubs" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="应付人民币">
                        <el-input v-model="form.rmb" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="重量">
                        <el-input v-model="form.weight" autocomplete="off"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="updateOrder">确 定</el-button>
            </span>
        </el-dialog>

    </div>
</template>

<style>
.demo-table-expand label {
    width: 90px;
    color: #99a9bf;
}
.demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
}
</style>