<script>
import {defineComponent} from 'vue'
import {serverIp, serverPort,http} from "../../public/config";
import request from "@/utils/request";

export default defineComponent({
    name: "order",
    data() {
        return {
            //当前量
            COrder: {},
            orderDId: null,
            orderid: null,
            orderD: null,
            //当前快递单号
            orderDs:[],
            orderDsLength: null,
            //新增快递单号数
            addDeliveryNum: 1,
            //新增快递单号
            orderDeliverys: [],


            //
            addOrderDDialogVisible: false,
            orderDDialogVisible: false,
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
                label: '已出发'
            }, {
                value: 3,
                label: '已到达'
            }, {
                value: 4,
                label: '订单等待付款',
                disabled: true
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
                usd: null,
                rubs: null,
                weight: null,
                remark: null,
            },
        }
    },
    created() {
        this.user=localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
        this.searchOrdersByUsername()

        //创建新增快递单号备用
        this.orderDeliverys = []
        for (let i =0;i<10;i++){this.orderDeliverys.push({
            trackingNumber: this.$t("DN"),
            remark: this.$t("remark"),
            type:'',
            orderId:null
        })}
    },
    methods:
        {
            updateData(){
                this.searchOrdersByUsername()
            },
            deleteOrder(id){
                try {
                    this.request.delete("/order/orderId/" + id).then(res => {
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
                const isLt1M = file.size / 1024 / 1024 < 1
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
                    "orderDId": this.orderDId,
                    "url": response,
                    "orderId": this.orderid
                }
                try {
                    this.request.post("/order-file", order_file).then(res => {
                        if (res.code === '200') {
                            this.orderD.isUpload = true
                            this.request.post("/order-delivery/update", this.orderD).then(res => {
                                if (res.code === '200') {
                                    this.$message.success("上传成功")
                                }
                                else {
                                    this.$message.error("上传失败")
                                    this.request.delete("/file/url/"+ response).then(res => {
                                    })
                                }
                            })
                        }
                        else {
                            this.$message.error("上传失败")
                            this.request.delete("/file/url/"+ response).then(res => {
                            })
                        }
                    })
                } catch (e){
                    this.$message.error("上传失败")
                    this.request.delete("/file/url/"+ response).then(res => {
                    })
                }
            },
            //加载表单
            searchOrdersByUsername(){
                const loading = this.$loading({
                    lock: true,
                    text: 'Loading',
                    spinner: 'el-icon-loading',
                    background: 'rgba(0, 0, 0, 0.8)'
                });
                setTimeout(() => {
                    loading.close();
                }, 10000);
                this.request.get("/order/page", {
                    params: {
                        pageNum: this.pageNum,
                        pageSize: this.pageSize,
                        username: this.username,
                        name: this.name,
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
                    for (let i = 0; i < this.tableData.length; i++) {
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
                        if (this.tableData[i].receiveRegion === "China" ){
                            this.tableData[i].receiveRegion = '中国'
                        }
                        if (this.tableData[i].sendRegion === "Moscow" ){
                            this.tableData[i].sendRegion = '莫斯科'
                        }
                        if (this.tableData[i].sendRegion === "China" ){
                            this.tableData[i].sendRegion = '中国'
                        }
                        //快递类型
                        if (this.tableData[i].dateOut === "12-15days") {
                            this.tableData[i].dateOut = this.$t('time1')
                        } else if (this.tableData[i].dateOut === "18-25days"){
                            this.tableData[i].dateOut = this.$t('time2')
                        }
                        if(i === this.tableData.length-1){
                            loading.close();
                        }

                    }
                })
            },
            confirmeOrder(order){
                order = {
                    id: order.id,
                    orderName: order.orderName,
                    isConfirmed: true,
                }
                this.request.post("/order/update", order).then(res => {
                    if (res.code === '200'){
                        this.searchOrdersByUsername()
                        this.$message.success("订单确认")
                    }
                    else {
                        this.$message.error(res.msg)
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
                this.form.usd = order.usd
                this.form.rubs = order.rubs
                this.form.remark = order.remark
            },
            updateOrder(){
                this.editDialogVisible = false
                this.request.post("/order/update", this.form).then(res => {
                    if (res.code === '200') {
                        this.searchOrdersByUsername()
                        this.editDialogVisible = false
                        this.$message.success("更新成功")
                    }else {
                        this.$message.error(res.msg)
                    }
                })
            },
            updateOrderD(){
                for (let i =0; i<this.orderDs.length;i++){
                    this.request.post("/order-delivery/update", this.orderDs[i]).then(res => {
                        if (res.code === '200') {
                            this.$message.success("更新成功")
                        }else {
                            this.$message.error(res.msg)
                        }
                    })
                    this.orderDDialogVisible = false
                }
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
            exp() {
                window.open(`${http}://${serverIp}:${serverPort}/order/export`)
            },
            async orderDsDraw(order){
                this.orderid = order.id
                for (let key in order) {
                    this.COrder[key] = order[key];
                }
                await this.request.get("/order-delivery/findByorder/" + order.id).then(res => {
                    if(res.code === '200')
                    {
                        this.orderDs = res.data
                        this.orderDsLength = this.orderDs.length
                        this.orderDDialogVisible = true
                    }else {
                        this.$message.error("加载失败")
                    }
                })
            },
            openPhoto(orderD){
                this.urls=[]
                this.orderDId = orderD.id
                this.orderD = orderD
                this.request.get("/order-file/orderDId/" + this.orderDId).then(res => {
                    if(res.code === '200')
                    {
                        for(let i = 0; i < res.data.length; i++){
                            this.urls.push(res.data[i].url)
                        }
                        this.AdminDialogVisible = true
                    } else {
                        this.$message.error("加载失败")
                    }
                })
            },
            updatePhoto(orderD){
                this.orderDId = orderD.id
                this.orderD = orderD
                this.centerDialogVisible = true
            },
            addOrderD(){
                this.orderDDialogVisible = false
                this.addOrderDDialogVisible = false
                this.COrder.deliveryNum = this.orderDs.length + this.addDeliveryNum
                if (this.COrder.isConfirmed === "是")
                    this.COrder.isConfirmed = true
                else
                    this.COrder.isConfirmed = false
                this.request.post("/order/update", this.COrder).then(res => {
                    if(res.code === '200'){
                        for(let i = 0; i< this.addDeliveryNum; i++){
                            this.orderDeliverys[i].orderId = this.orderid
                            request.post("/order-delivery/add", this.orderDeliverys[i]).then(res => {
                                if(res.code === '200'){
                                    let j=i+1
                                    this.$message.success("No."+ j+ "更新成功")
                                }
                            })
                        }
                        //清零
                        this.orderDeliverys = []
                        for (let i =0;i<10;i++){this.orderDeliverys.push({
                            trackingNumber: this.$t("DN"),
                            remark: this.$t("remark"),
                            type:'',
                            orderId:null
                        })}

                    }
                })
            }
        }
})
</script>

<template>
    <div>
        <div style="margin: 10px 0">
            <el-input style="width: 200px" placeholder="按用户名搜索" suffix-icon="el-icon-search" v-model="username"></el-input>
            <el-input style="width: 200px" placeholder="按用收件人姓名搜索" suffix-icon="el-icon-search" v-model="name"></el-input>
            <el-button class="ml-5" type="primary" @click="searchOrdersByUsername">搜索</el-button>
            <el-button type="warning" @click="reset">重置</el-button>
            <el-button type="primary" @click="exp" class="ml-5">导出<i class="el-icon-top"></i></el-button>
        </div>

        <el-table
            header-cell-style="background-color: #DCDFE6; color: #606266;"
            :data="tableData"
            border
            style="width: 100%; border-radius:25px "
            height="720"
            width="100%"
            size="medium">
            <el-table-column type="expand" align-center="true">
                <template slot-scope="props">

                    <el-descriptions column="3" size="mini" border>
                        <template slot="extra">
                            <el-button v-no-more-click-ten type="primary" size="mini" @click="sendmail(props.row)">发送订单信息邮件</el-button>
                            <el-button type="primary" size="mini" @click="editOrder(props.row)">编辑订单</el-button>
                            <el-button v-no-more-click-ten type="primary" size="mini" @click="confirmeOrder(props.row)">确认订单</el-button>
                            <el-popconfirm
                                class="ml-5"
                                confirm-button-text='确定'
                                cancel-button-text='我再想想'
                                icon="el-icon-info"
                                icon-color="red"
                                title="您确定批量删除这些数据吗？"
                                @confirm="deleteOrder(props.row.id)"
                            >
                            <el-button v-no-more-click-ten type="danger" slot="reference" size="mini">撤销订单</el-button>
                            </el-popconfirm>
                        </template>
                        <el-descriptions-item>
                            <template slot="label">
                                <i class="el-icon-user"></i>
                                收件人姓名
                            </template>
                            {{ props.row.name }}
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template slot="label">
                                <i class="el-icon-mobile-phone"></i>
                                收件人手机号
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
                                <el-step :title="'已从' + props.row.sendRegion + '发出'"></el-step>
                                <el-step :title="'已到达' + props.row.receiveRegion"></el-step>
                                <el-step title="订单等待付款"></el-step>
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
                label="保险/自取/木架/防水/托盘"
                prop="others">
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
                label="订单支付确认"
                prop="isConfirmed">
            </el-table-column>
            <el-table-column
                label="快递单号">
                <template slot-scope="props">
                    <el-button @click="orderDsDraw(props.row)" type="primary" style="margin-left: 16px;">
                        查 看
                    </el-button>
                </template>
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
                    <el-form-item label="快递地址">
                        <el-input v-model="form.receiveAdress" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="应付卢布">
                        <el-input v-model="form.rubs" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="应付美金">
                        <el-input v-model="form.usd" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="备注">
                        <el-input type="textarea" v-model="form.remark" autocomplete="off"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="updateOrder">更 新</el-button>
            </span>
        </el-dialog>

        <el-dialog
            title="编辑快递单号"
            :visible.sync="orderDDialogVisible"
            width="50%">
            <template>
            <el-form>
                <el-form-item>
                <div v-for="o in this.orderDsLength" :key="o">
                <h5>快递号.{{o}}</h5>
                <el-input show-word-limit maxlength ="30" v-model= orderDs[o-1].trackingNumber></el-input>
                <h5>备注.{{o}}</h5>
                <el-input type="textarea" maxlength="500" show-word-limit v-model= orderDs[o-1].remark></el-input>
                <h5>货物类型.{{o}}</h5>
                <el-radio-group v-model="orderDs[o-1].type" >
                    <el-radio label="快递货(курьер)">{{$t('快递货')}}</el-radio>
                    <el-radio label="鞋子(обувь)">{{$t('鞋子')}}</el-radio>
                    <el-radio label="食品(еда)">{{$t('食品')}}</el-radio>
                    <el-radio label="电子产品(электронное изделие)">{{$t('电子产品')}}</el-radio>
                    <el-radio label="箱包(сумка)">{{$t('箱包')}}</el-radio>
                    <el-radio label="小百货(универмаг)">{{$t('小百货')}}</el-radio>
                    <el-radio label="化妆品(косметические продукты)">{{$t('化妆品')}}</el-radio>
                    <el-radio label="服装(одежда)">{{$t('服装')}}</el-radio>
                    <el-radio label="其他(другой)">{{$t('其他')}}</el-radio>
                </el-radio-group>
                <el-button type="primary" @click="openPhoto(orderDs[o-1])">查看上传</el-button>
                <el-button type="primary" @click="updatePhoto(orderDs[o-1])">上传照片</el-button>
                <el-divider><i class="el-icon-goods"></i></el-divider>
                </div>
                </el-form-item>
            </el-form>
            </template>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="addOrderDDialogVisible = true">新增快递单号</el-button>
                <el-button type="primary" @click="updateOrderD">更 新</el-button>
            </span>
        </el-dialog>

        <el-dialog
            title="新增快递单号"
            :visible.sync="addOrderDDialogVisible"
            width="50%">
            <template>
                <el-form>
                    <el-row>
                        <el-col :xs="20" :sm="18" :md="18" :lg="18" :xl="18">
                            <el-form-item :label="$t('deliveryNum')">
                                <template>
                                    <template>
                                        <el-input-number  v-model="addDeliveryNum" :step="1" step-strictly :max="10" :min="1"></el-input-number>
                                    </template>
                                </template>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :xs="20" :sm="18" :md="18" :lg="18" :xl="18">
                            <el-form-item>
                                <div v-for="o in addDeliveryNum" :key="o">
                                    <el-col :xs="3" :sm="3" :md="3" :lg="3" :xl="3">
                                        No.{{o}}
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                                        <el-input :placeholder="$t('DN')" show-word-limit maxlength ="30" v-model= orderDeliverys[o-1].trackingNumber></el-input>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" style="margin-top: 1%">
                                        <el-radio-group v-model="orderDeliverys[o-1].type" >
                                            <el-radio label="快递货(курьер)">{{$t('快递货')}}</el-radio>
                                            <el-radio label="鞋子(обувь)">{{$t('鞋子')}}</el-radio>
                                            <el-radio label="食品(еда)">{{$t('食品')}}</el-radio>
                                            <el-radio label="电子产品(электронное изделие)">{{$t('电子产品')}}</el-radio>
                                            <el-radio label="箱包(сумка)">{{$t('箱包')}}</el-radio>
                                            <el-radio label="小百货(универмаг)">{{$t('小百货')}}</el-radio>
                                            <el-radio label="化妆品(косметические продукты)">{{$t('化妆品')}}</el-radio>
                                            <el-radio label="服装(одежда)">{{$t('服装')}}</el-radio>
                                            <el-radio label="其他(другой)">{{$t('其他')}}</el-radio>
                                        </el-radio-group>
                                    </el-col>
                                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                                        <el-input type="textarea" maxlength="500" show-word-limit :placeholder="$t('remark')" v-model= orderDeliverys[o-1].remark></el-input>
                                    </el-col>
                                </div>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
            </template>
            <span slot="footer" class="dialog-footer">
                <el-button v-no-more-click-ten type="primary" @click="addOrderD">确 定</el-button>
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