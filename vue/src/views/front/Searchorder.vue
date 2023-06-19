<script>
import {defineComponent} from 'vue'
import {serverIp, serverPort,http} from "../../../public/config";

export default defineComponent({
    name: "searchorder",
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
            numOrders: null,
            CS: {
                'text-align': 'center',  //文本居中
                'min-width': '250px',   //最小宽度
                'word-break': 'break-all'  //过长时自动换行
            },
        }
    },
    created() {
        this.user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
        this.searchOrdersByUsername()

    },
    methods:
        {
            //上传照片
            handleAddPhoto(username, ordername) {
                this.centerDialogVisible = true
                this.currentOrdername = ordername
            },
            updateData() {
                this.searchOrdersByUsername()
            },
            deleteOrder(orderName) {
                this.request.delete("/order/ordername/" + orderName).then(res => {
                    if (res.code === '200') {
                        console.log('22222')
                        this.$message.success(this.$t('message.successfullyDeleted').toString())
                        this.updateData()
                    } else {
                        this.$message.error(this.$t('message.failedToDelete').toString())
                    }
                })
            },
            uploadFileError(err, file, fileList) {
                this.$message.error(this.$t('message.uploadFailed').toString())
            },
            exceedFile(files, fileList) {
                this.$message.error(this.$t('message.numberDocumentsAllowed') + ':' + this.limitnum);
            },
            removeFile(file, fileList) {
                this.fileList = fileList;
            },
            loadJsonFromFile(file, fileList) {
                this.fileList = fileList
            },
            onBeforeUploadImage(file) {
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
            uploadSuccess(response, file, fileList) {
                let order_file = {
                    "orderName": this.currentOrdername,
                    "userName": '',
                    "url": response
                }
                try {

                    this.request.post("/order-file", order_file).then(res => {
                        if (res.code === '200') {
                            this.searchOrdersByUsername()
                            this.$message.success(this.$t('message.uploadSuccessful').toString())
                        }
                    })
                } catch (e) {

                    this.$message.error(this.$t('message.message.uploadFailed').toString())
                }
            },
            //加载表单
            searchOrdersByUsername() {
                this.request.get("/order/username").then(res => {
                    this.tableData = JSON.parse(JSON.stringify(res.data))
                    if (this.tableData.length === 0) {
                        return
                    }
                    const newProperty1 = 'userUpload'
                    const newProperty2 = 'adminUpload'
                    for (let i = 0; i < this.tableData.length; i++) {
                        this.request.get("/order-file/ordername/" + this.tableData[i].orderName).then(res => {
                            this.tableData[i][newProperty1] = []
                            this.tableData[i][newProperty2] = []
                            if (res.data != null) {
                                for (let k = 0; k < res.data.length; k++) {
                                    if (res.data[k].adminUp === false) {
                                        this.tableData[i][newProperty1].push(res.data[k].url)
                                    } else {
                                        this.tableData[i][newProperty2].push(res.data[k].url)
                                    }
                                }
                            }
                        })
                        //城市
                        switch(this.tableData[i].sendRegion)
                        {
                            case "Moscow":
                                this.tableData[i].sendRegion = this.$t("Moscow")
                                break;
                            case "Guangdong":
                                this.tableData[i].sendRegion = this.$t("Guangdong")
                                break;
                            default:

                        }
                        switch(this.tableData[i].receiveRegion)
                        {
                            case "Moscow":
                                this.tableData[i].receiveRegion = this.$t("Moscow")
                                break;
                            case "Guangdong":
                                this.tableData[i].receiveRegion = this.$t("Guangdong")
                                break;
                            default:

                        }

                        ///保险自提
                        this.tableData[i].others = [this.tableData[i].others[0] + this.tableData[i].others[1], this.tableData[i].others[3] + this.tableData[i].others[4]]
                        if (this.tableData[i].others[0] === "保险"){
                            this.tableData[i].others[0] = this.$t("true")
                        } else {
                            this.tableData[i].others[0] = this.$t("false")
                        }
                        if (this.tableData[i].others[1] === "自取"){
                            this.tableData[i].others[1] = this.$t("true")
                        } else {
                            this.tableData[i].others[1] = this.$t("false")
                        }
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
                    }
                })
            },
            showUserPhoto(urls) {
                this.UserDialogVisible = true
                this.urls = urls

            },
            showAdminPhoto(urls) {
                this.AdminDialogVisible = true
                this.urls = urls
            }
        },
})
</script>

<template>
    <div>
        <el-table
            header-cell-style="background-color: #DCDFE6; color: #606266;"
            :data="tableData"
            border
            style="width: 100%; border-radius:25px "
            height="780"
            width="100%"
            size="medium"
            default-expand-all="ture">
            <el-table-column type="expand" align-center="true">
                <template slot-scope="props">
                    <el-descriptions column="3" size="mini" border>
                        <template slot="extra">
                            <el-button type="primary" size="mini" @click="showAdminPhoto(props.row.adminUpload)">{{$t('Viewmyobjects')}}</el-button>
                            <el-button type="primary" size="mini" @click="showUserPhoto(props.row.userUpload)">{{$t('Viewmyuploads')}}</el-button>
                            <el-button type="primary" size="mini" @click="handleAddPhoto(props.row.username,props.row.orderName)">{{$t('Uploadphotos')}}</el-button>
                            <el-button type="danger" size="mini" @click="deleteOrder(props.row.orderName)">{{$t('Cancellationorders')}}</el-button>
                        </template>
                        <el-descriptions-item>
                            <template slot="label">
                                <i class="el-icon-user"></i>
                                {{$t('name')}}
                            </template>
                            {{ props.row.name }}
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template slot="label">
                                <i class="el-icon-mobile-phone"></i>
                                {{$t('phone')}}
                            </template>
                            {{ props.row.phone }}
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template slot="label">
                                <i class="el-icon-location-outline"></i>
                                {{$t('weight')}}/{{$t('deliveryNum')}}
                            </template>
                            {{props.row.weight}}kg，{{ props.row.deliveryNum }}{{$t('number')}}
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template slot="label">
                                <i class="el-icon-tickets"></i>
                                {{$t('receiveAdress')}}
                            </template>
                            <el-tag size="small">{{ props.row.receiveAdress}}</el-tag>
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template slot="label">
                                <i class="el-icon-office-building"></i>
                                {{$t('sendRegion')}}
                            </template>
                            {{props.row.sendRegion}}
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template slot="label">
                                <i class="el-icon-office-building"></i>
                                {{$t('receiveRegion')}}
                            </template>
                            {{ props.row.receiveRegion}}
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template slot="label">
                                <i class="el-icon-tickets"></i>
                                {{$t('email')}}
                            </template>
                            <el-tag size="small">{{ props.row.email}}</el-tag>
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template slot="label">
                                <i class="el-icon-office-building"></i>
                                {{$t('details')}}
                            </template>
                            {{ props.row.details}}
                        </el-descriptions-item>
                        <el-descriptions-item>
                            <template slot="label">
                                <i class="el-icon-office-building"></i>
                                {{$t('remark')}}
                            </template>
                            {{ props.row.remark}}
                        </el-descriptions-item>
                    </el-descriptions>
                    <template>
                        <div style="margin-top: 10px">
                            <el-steps :space="200" :active="props.row.orderStatus" finish-status="success" align-center="true">
                                <el-step :title="$t('state1')"></el-step>
                                <el-step :title="$t('state2')"></el-step>
                                <el-step :title="$t('state3') + props.row.sendRegion+$t('state4')"></el-step>
                                <el-step :title="$t('state5') + props.row.receiveRegion+$t('state6')"></el-step>
                                <el-step :title="$t('state7')"></el-step>
                                <el-step :title="$t('state8')"></el-step>
                            </el-steps>
                        </div>
                    </template>
                </template>
            </el-table-column>
            <el-table-column
                :label="$t('orderName')"
                prop="orderName"
                >
            </el-table-column>
            <el-table-column
                :label="$t('create_time')"
                prop="createTime"
            >
            </el-table-column>
            <el-table-column
                :label="$t('type')"
                prop="dateOut"
            >
            </el-table-column>
            <el-table-column
                :label="$t('others.insurance')"
                prop="others[0]">
            </el-table-column>
            <el-table-column
                :label="$t('others.selfPickup')"
                prop="others[1]">
            </el-table-column>
            <el-table-column
                :label="$t('isConfirmed')"
                prop="isConfirmed">
            </el-table-column>
        </el-table>

        <el-dialog
            :title="$t('Uploadphotos')"
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
                <el-button slot="trigger" size="small" type="primary">{{$t('Selectionphotos')}}</el-button>
                <div slot="tip" class="el-upload__tip">{{$t('Uploadjpgpngfilesonlyandnomorethan500kb')}}</div>
            </el-upload>
        </el-dialog>

        <el-dialog
            :visible.sync="UserDialogVisible"
            width="50%">
            <div class="demo-image__lazy">
                <el-image v-for="url in urls" :key="url" :src="url" lazy preview-src-list></el-image>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="UserDialogVisible = false">{{$t('return')}}</el-button>
            </span>
        </el-dialog>

        <el-dialog
            :visible.sync="AdminDialogVisible"
            width="50%">
            <div class="demo-image__lazy">
                <el-image v-for="url in urls" :key="url" :src="url" lazy preview-src-list></el-image>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button type="primary" @click="AdminDialogVisible = false">{{$t('return')}}</el-button>
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