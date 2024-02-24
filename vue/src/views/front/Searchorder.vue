<script>
import {defineComponent} from 'vue'
import {serverIp, serverPort,http} from "../../../public/config";
import request from "@/utils/request";

export default defineComponent({
    name: "searchorder",
    data() {
        return {
            //加载图片
            openPhotoDialogVisible: false,
            urls: [],
            //
            addOrderDDialogVisible: false,
            //新增快递单号数
            addDeliveryNum: 1,
            //新增快递单号
            orderDeliverys: [],
            //当前订单id
            orderId: null,
            //当前订单,用于添加OrderD
            COrder: {
                id: null,
                deliveryNum:null,
            },
            //当前订单，用于查看订单信息
            Corder:{},
            //查看订单详情的窗口
            details: false,
            //
            NoeditAlble: false,
            isUpload:[],
            //当前快递单号数目
            orderDs : [],
            tableData: [],
            serverIp: serverIp,
            serverPort: serverPort,
            http: http,
            uploadUsername: '',
            uploadOrdername: '',
            user: '',
            fileList: [],
            currentOrdername: '',
            limitnum: 10,
            numOrders: null,
            CS: {
                'text-align': 'center',  //文本居中
                'min-width': '250px',   //最小宽度
                'word-break': 'break-all'  //过长时自动换行
            },
            //抽屉
            drawer: false,
        }
    },
    created() {
        this.user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
        this.searchOrdersByUsername()
        //提前创建新增快递单号
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
            updateData() {
                this.searchOrdersByUsername()
            },
            deleteOrder(orderId) {
                this.request.delete("/order/orderId/" + orderId).then(res => {
                    if (res.code === '200') {
                        this.$message.success(this.$t('message.successfullyDeleted').toString())
                        this.updateData()
                    } else {
                        this.$message.error(this.$t('message.failedToDelete').toString())
                    }
                })
            },
            //加载表单
            async searchOrdersByUsername() {
                const loading = this.$loading({
                    lock: true,
                    text: 'Loading',
                    spinner: 'el-icon-loading',
                    background: 'rgba(0, 0, 0, 0.8)'
                });
                setTimeout(() => {
                    loading.close();
                }, 60000);
                this.request.get("/order/username").then(res => {
                    this.tableData = JSON.parse(JSON.stringify(res.data))
                    if (this.tableData.length === 0) {
                        loading.close();
                        return
                    }
                    this.tableData = this.tableData.sort(this.compare)
                    for (let i = 0; i <  this.tableData.length; i++) {
                        //城市
                        switch(this.tableData[i].sendRegion)
                        {
                            case "Moscow":
                                this.tableData[i].sendRegion = this.$t("Moscow")
                                break;
                            case "China":
                                this.tableData[i].sendRegion = this.$t("China")
                                break;
                            default:

                        }
                        switch(this.tableData[i].receiveRegion)
                        {
                            case "Moscow":
                                this.tableData[i].receiveRegion = this.$t("Moscow")
                                break;
                            case "China":
                                this.tableData[i].receiveRegion = this.$t("China")
                                break;
                            default:

                        }
                        ///others
                        this.tableData[i].others = this.tableData[i].others.split("/")
                        let others = ""
                        for(let k =0; k < this.tableData[i].others.length; k++){
                            if (this.tableData[i].others[k] === "保险"){
                                others = others + this.$t("true")
                            } else if(this.tableData[i].others[k] === "自取") {
                                others = others + this.$t("true")
                            } else if(this.tableData[i].others[k] === "木架") {
                                others = others + this.$t("true")
                            } else if(this.tableData[i].others[k] === "防水") {
                                others = others + this.$t("true")
                            } else if(this.tableData[i].others[k] === "托盘") {
                                others = others + this.$t("true")
                            } else {
                                others = others + this.$t("false")
                            }
                            if (k !== this.tableData[i].others.length - 1){
                                others = others + "/"
                            }
                        }
                        this.tableData[i].others = others
                        //是否确认
                        if (this.tableData[i].isConfirmed === true) {
                            this.tableData[i].isConfirmed = this.$t(this.tableData[i].isConfirmed.toString())
                        } else {
                            this.tableData[i].isConfirmed = this.$t(this.tableData[i].isConfirmed.toString())
                        }
                        //快递类型
                        if (this.tableData[i].dateOut === "12-15days") {
                            this.tableData[i].dateOut = this.$t('time1')
                        } else if (this.tableData[i].dateOut === "18-25days"){
                            this.tableData[i].dateOut = this.$t('time2')
                        }
                        //与该订单关联的快递
                        this.request.get("/order-delivery/findByorder/" + this.tableData[i].id).then(res => {
                            if(res.code === '200')
                            {
                                this.tableData[i].orderDs = res.data
                                if(i === this.tableData.length-1){
                                    loading.close();
                                }
                            }
                        })
                    }
                })
            },
            orderDsDraw(order){
                this.drawer = true
                this.orderId = order.id
                this.COrder.id = order.id
                this.COrder.deliveryNum = order.deliveryNum
                this.isUpload = []
                for(let i =0;i<order.orderDs.length;i++){
                    this.isUpload.push(order.orderDs[i].isUpload)
                }
                if (order.isConfirmed === "否")
                {
                    this.NoeditAlble = false
                } else if (order.isConfirmed === "是"){
                    this.NoeditAlble = true
                } else if (order.isConfirmed === "Нет")
                {
                    this.NoeditAlble = false
                } else if (order.isConfirmed === "Да"){
                    this.NoeditAlble = true
                }
                this.orderDs = order.orderDs

            },
            updateOrderD(){
                this.drawer = false
                for (let i =0; i<this.orderDs.length;i++){
                    this.request.post("/order-delivery/update", this.orderDs[i]).then(res => {
                        if (res.code === '200') {
                            this.$message.success(this.$t("optsucc"))
                        } else if(res.code === '501'){
                            this.$message.error(this.$t("订单已经确认"))
                        }
                        else if ( res.code === '500'){
                            this.$message.error(this.$t("optfail"))
                        }
                    })
                }
            },
            //查看图片 id 是 orderD的id
            openPhoto(id){
                this.urls = []
                this.openPhotoDialogVisible = true
                //把对应id orderD的urls加载进来
                this.request.get("/order-file/orderDId/" + id).then(res => {
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
            //比较函数 用于订单排序
            compare(a,b){
                return b.id - a.id;
            },
            addOrderD(){
                this.COrder.deliveryNum = this.orderDs.length + this.addDeliveryNum
                this.request.post("/order/changeOrderNum", this.COrder).then(res => {
                    if(res.code === '200'){
                        for(let i = 0; i< this.addDeliveryNum; i++){
                            this.orderDeliverys[i].orderId = this.orderId
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
                    else {

                        this.$message.error(this.$t("optfail"))}
                })
                this.drawer=false
                this.addOrderDDialogVisible = false
                this.updateData()
            },//对应订单的详细信息
            orderDetails(order){
                this.details = true
                this.Corder = order
            }
        },
})
</script>

<template>
    <div style="width: 100%">
        <el-table
            header-cell-style="background-color: #F2F6FC; color: #606266;"
            :data="tableData"
            border
            style="border-radius:10px; width: 276px; margin: auto"
            height="780"
            size="medium">
            <el-table-column
                :label="$t('create_time')"
                prop="createTime"
                width="100px"
            >
            </el-table-column>
            <el-table-column
                :label="$t('opt')"
                width="175px"
            >
                <template slot-scope="props">
                    <el-button @click="orderDetails(props.row)" icon="el-icon-search" round type="primary" style="margin-left: 16px;margin-top: 16px">
                        {{$t('moredetails')}}
                    </el-button>
                    <el-button @click="orderDsDraw(props.row)" icon="el-icon-tickets" round type="primary" style="margin-left: 16px;margin-top: 16px">
                        {{$t('快递号')}}
                    </el-button>
                    <el-button type="danger" icon="el-icon-delete" @click="deleteOrder(props.row.id)" round style="margin-left: 16px;margin-top: 16px">
                        {{$t('Cancellationorders')}}
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-dialog
            :visible.sync="details"
            fullscreen>
            <el-descriptions class="margin-top" :column="1" size="medium" border>
                <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-user"></i>
                        {{$t("username")}}
                    </template>
                    {{Corder.username}}
                </el-descriptions-item>
                <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-finished"></i>
                        {{$t("isConfirmed")}}
                    </template>
                    {{Corder.isConfirmed}}
                </el-descriptions-item>
                <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-s-promotion"></i>
                        {{$t("email")}}
                    </template>
                    {{Corder.email}}
                </el-descriptions-item>
                <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-user"></i>
                        {{$t("name")}}
                    </template>
                    {{Corder.name}}
                </el-descriptions-item>
                <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-mobile-phone"></i>
                        {{$t("phone")}}
                    </template>
                    {{Corder.phone}}
                </el-descriptions-item>
                <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-office-building"></i>
                        {{$t("receiveAdress")}}
                    </template>
                    {{Corder.receiveAdress}}
                </el-descriptions-item>
                <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-plus"></i>
                        {{$t("deliveryNum")}}
                    </template>
                    {{Corder.deliveryNum}}
                </el-descriptions-item>
                <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-circle-check"></i>
                        {{$t("others.mode1")}}
                    </template>
                    {{Corder.others}}
                </el-descriptions-item>
                <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-shopping-cart-2"></i>
                        {{$t("weight")}}
                    </template>
                    {{Corder.weight}}
                </el-descriptions-item>
                <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-time"></i>
                        {{$t("dateOut")}}
                    </template>
                    {{Corder.dateOut}}
                </el-descriptions-item>
                <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-money"></i>
                        {{$t("USD")}}
                    </template>
                    {{Corder.usd}}
                </el-descriptions-item>
                <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-coin"></i>
                        {{$t("RUB")}}
                    </template>
                    {{Corder.rubs}}
                </el-descriptions-item>
                <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-set-up"></i>
                        {{$t("details")}}
                    </template>
                    <el-tag size="small">{{Corder.details}}</el-tag>
                </el-descriptions-item>
                <el-descriptions-item>
                    <template slot="label">
                        <i class="el-icon-chat-line-square"></i>
                        {{$t("remark")}}
                    </template>
                    {{Corder.remark}}
                </el-descriptions-item>
            </el-descriptions>
        </el-dialog>


        <el-dialog
            :visible.sync="drawer"
            fullscreen
            width="40%">
            <template>
                <el-form>
                    <el-form-item>
                        <div v-for="o in orderDs.length" :key="o">
                            <h6>{{$t("快递号")}}.{{o}}</h6>
                            <el-input :disabled=NoeditAlble show-word-limit maxlength ="30" v-model= orderDs[o-1].trackingNumber></el-input>
                            <h6>{{$t("备注")}}.{{o}}</h6>
                            <el-input type="textarea" :disabled=NoeditAlble maxlength="500" show-word-limit v-model= orderDs[o-1].remark></el-input>
                            <h6>{{$t("货物类型")}}.{{o}}</h6>
                            <el-radio-group :disabled=NoeditAlble v-model="orderDs[o-1].type" >
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
                            <el-result v-if=isUpload[o-1] icon="success" :title="$t('图片已经更新点击查看')">
                            <template slot="extra">
                                <el-button type="primary" @click="openPhoto(orderDs[o-1].id)" size="medium" :disabled=!isUpload[o-1]>{{$t("查看照片")}}</el-button>
                            </template>
                            </el-result>
                            <el-result v-if=!isUpload[o-1] icon="info" :title="$t('包裹还未到达仓库')">
                                <template slot="extra">
                                    <el-button type="primary" @click="openPhoto(orderDs[o-1].id)" size="medium" :disabled=!isUpload[o-1]>{{$t("查看照片")}}</el-button>
                                </template>
                            </el-result>
                            <el-divider><i class="el-icon-goods"></i></el-divider>
                        </div>
                    </el-form-item>
                </el-form>
            </template>
            <div style="margin: auto">
            <span slot="footer" class="dialog-footer" >
                <el-button type="primary" @click="drawer = false">{{$t("return")}}</el-button>
                <el-button type="primary" :disabled=NoeditAlble @click="addOrderDDialogVisible = true">{{$t("新增")}}</el-button>
                <el-button v-no-more-click-ten type="primary" :disabled=NoeditAlble @click="updateOrderD">{{$t("更新")}}</el-button>
            </span>
            </div>
        </el-dialog>


        <el-dialog
            :visible.sync="openPhotoDialogVisible"
            fullscreen>
            <div class="demo-image__lazy">
                <el-image v-for="url in urls" :key="url" :src="url" lazy></el-image>
            </div>
        </el-dialog>

        <el-dialog
            :visible.sync="addOrderDDialogVisible"
            fullscreen>
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
                                        <h3>No.{{o}}</h3>
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
::-webkit-scrollbar {
    width: 20px; /*对垂直流动条有效*/
    height: 20px; /*对水平流动条有效*/
}
</style>