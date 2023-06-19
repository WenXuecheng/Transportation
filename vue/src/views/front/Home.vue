<template>
    <div>
        <template>
            <div >
                <el-row :gutter="20">
                    <el-col  :xs="24" :sm="6" :md="6" :lg="6" :xl="6">
                        <div style="height: 250px">
                            <el-card class="box-card">
                                <div style="height: 5%">
                                    <el-statistic group-separator="," :precision="2" decimal-separator="." :value="exchangeRate" :title="$t('exchangeRate')">
                                        <template slot="prefix">
                                            <i class="el-icon-s-flag" style="color: red"></i>
                                        </template>
                                        <template slot="suffix">
                                            <i class="el-icon-s-flag" style="color: blue"></i>
                                        </template>
                                    </el-statistic>
                                </div>
                                <div style="height: 95%">
                                    <p>{{photoandannouncement.announcement1.p1}}</p>
                                    <p>{{photoandannouncement.announcement1.p2}}</p>
                                </div>
                            </el-card>
                        </div>
                    </el-col>
                    <el-col  :xs="24" :sm="9" :md="9" :lg="9" :xl="9">
                        <div style="height: 250px">
                            <el-card class="box-card">
                                <div style="margin-top: 1%;margin-left: 40%; color: #606266;height: 20px">
                                    <span>{{ $t("newgoods") }}</span>
                                </div>
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
                    <el-col  :xs="24" :sm="9" :md="9" :lg="9" :xl="9">
                        <el-card class="box-card">
                            <template>
                                <div style="margin-top: 1%;margin-left: 50%; color: #606266;height: 20px">
                                    <span>{{ $t("taobaodewu") }}</span>
                                </div>
                                <el-carousel :interval="4000" type="card" height="220px">
                                    <el-carousel-item v-for="item in photoandannouncement.photo23" :key="item">
                                        <el-image
                                            style="width: 80%; height: 80%; margin-top: 5%;margin-left: 10%;"
                                            align="center"
                                            :src="item"
                                            fit="contain">
                                        </el-image>
                                    </el-carousel-item>
                                </el-carousel>
                            </template>
                        </el-card>
                    </el-col>
                </el-row>
                <el-row :gutter="20" style="margin-top: 10px">
                    <el-col  :xs="24" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-card class="box-card" >
                            <h3 style="text-align: center">{{photoandannouncement.announcement2.title}}</h3>
                            <p>{{photoandannouncement.announcement2.p1}}</p>
                            <p>{{photoandannouncement.announcement2.p2}}</p>
                        </el-card>
                    </el-col>
                    <el-col  :xs="24" :sm="12" :md="12" :lg="12" :xl="12">
                        <el-card class="box-card">
                            <h3 style="text-align: center">{{photoandannouncement.announcement3.title}}</h3>
                            <p>{{photoandannouncement.announcement3.p1}}</p>
                            <p>{{photoandannouncement.announcement3.p2}}</p>
                        </el-card>
                    </el-col>
                </el-row>
            </div>
        </template>


        <template>
            <div style="margin-top: 1%">
                <template>
                <el-descriptions column="3" size="mini" border >
                    <el-descriptions-item  fixed>
                        <template slot="label" >
                            <i class="el-icon-user"></i>
                            {{ $t("orderName") }}
                        </template>
                        {{this.order.orderName}}
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-user"></i>
                            {{ $t("name") }}
                        </template>
                        {{this.order.name}}
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-mobile-phone"></i>
                            {{ $t("phone") }}
                        </template>
                        {{this.order.phone}}
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-location-outline"></i>
                            {{ $t("deliveryNum") }}
                        </template>
                        {{this.order.deliveryNum}}
                    </el-descriptions-item>
                    <el-descriptions-item >
                        <template slot="label">
                            <i class="el-icon-user"></i>
                            {{ $t("others.mode") }}
                        </template>
                        {{this.order.others[0]}} / {{this.order.others[1]}}
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-tickets"></i>
                            {{ $t("receiveAdress") }}
                        </template>
                        <el-tag size="small">{{this.order.receiveAdress}}</el-tag>
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-office-building"></i>
                            {{ $t("sendRegion") }}
                        </template>
                        {{this.order.sendRegion}}
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-office-building"></i>
                            {{ $t("receiveRegion") }}
                        </template>
                        {{this.order.receiveRegion}}
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-tickets"></i>
                            {{ $t("email") }}
                        </template>
                        <el-tag size="small">{{this.order.email}}</el-tag>
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-office-building"></i>
                            {{ $t("details") }}
                        </template>
                        {{this.order.details}}
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-office-building"></i>
                            {{ $t('remark') }}
                        </template>
                        {{this.order.remark}}
                    </el-descriptions-item>
                    <el-descriptions-item>
                        <template slot="label">
                            <i class="el-icon-office-building"></i>
                            {{ $t('isConfirmed') }}
                        </template>
                        {{this.order.isConfirmed}}
                    </el-descriptions-item>
                </el-descriptions>
                </template>
            </div>
        </template>

        <template>
            <div style="margin-top: 10px">
                <el-steps :space="180" :active="this.order.orderStatus" finish-status="success" align-center="true">
                    <el-step :title="$t('state1')"></el-step>
                    <el-step :title="$t('state2')"></el-step>
                    <el-step :title="$t('state3') + this.order.sendRegion+$t('state4')"></el-step>
                    <el-step :title="$t('state5') + this.order.receiveRegion+$t('state6')"></el-step>
                    <el-step :title="$t('state7')"></el-step>
                    <el-step :title="$t('state8')"></el-step>
                </el-steps>
            </div>
        </template>
    </div>
</template>

<script>
export default {
    name: "FrontHome",
    props: {
        user: Object
    },
    data() {
        return {
            exchangeRate: null,
            photoandannouncement: {
                photo1: 'https://www.ups.com/assets/resources/supplychain/images/350x197/m15-350x197-fast-fulfillment-assembly.jpg',
                photo23: ['https://www.ups.com/assets/resources/supplychain/images/350x197/m15-350x197-fast-fulfillment-assembly.jpg'],
                announcement1: {p1:"None",p2:"None"},
                announcement2: {title: "None", p1:"None", p2:"None"},
                announcement3: {title: "None", p1:"None", p2:"None"}
            },
            order: {
                "id":"",
                "orderName":"",
                "sendRegion":"",
                "receiveRegion":"",
                "receiveAdress":"",
                "dateOut":"",
                "deliveryNum":"",
                "others":[],
                "name":"",
                "phone":"",
                "email":"",
                "details":"",
                "remark":"",
                "pay":"",
                "username":"",
                "weight":"",
                "orderStatus":"",
            },
            tableData: [],
            dialogVisible: false
        }
    },
    created() {
    },
    mounted() {
        this.searchOrdersByUsername()
        this.updateExchangeRate()
        this.getphotoandannouncement()
    },
    methods: {
        searchOrdersByUsername(){
            this.request.get("/order/username").then(res => {
                try {
                    this.tableData = JSON.parse(JSON.stringify(res.data))
                    if (this.tableData.length === 0)
                    {
                        return
                    }
                    const newProperty1='userUpload'
                    const newProperty2='adminUpload'
                    let j = 0
                    let n = 0
                    for (let i = 0; i < this.tableData.length; i++) {
                        if (this.tableData[i].id > n){
                            j=i
                            n=this.tableData[i].id
                        }
                    }
                    this.request.get("/order-file/ordername/" + this.tableData[j].orderName).then(res => {
                        this.tableData[j][newProperty1] = []
                        this.tableData[j][newProperty2] = []
                        if (res.data != null)
                        {
                            for(let k = 0; k < res.data.length; k++){
                                if (res.data[k].adminUp === false){
                                    this.tableData[j][newProperty1].push(res.data[k].url)
                                } else {
                                    this.tableData[j][newProperty2].push(res.data[k].url)
                                }
                            }
                        }
                    })
                    //城市
                    switch(this.tableData[j].sendRegion)
                    {
                        case "Moscow":
                            this.tableData[j].sendRegion = this.$t("Moscow")
                            break;
                        case "Guangdong":
                            this.tableData[j].sendRegion = this.$t("Guangdong")
                            break;
                        default:

                    }
                    switch(this.tableData[j].receiveRegion)
                    {
                        case "Moscow":
                            this.tableData[j].receiveRegion = this.$t("Moscow")
                            break;
                        case "Guangdong":
                            this.tableData[j].receiveRegion = this.$t("Guangdong")
                            break;
                        default:

                    }
                    ///保险自提
                    this.tableData[j].others = [this.tableData[j].others[0] + this.tableData[j].others[1], this.tableData[j].others[3] + this.tableData[j].others[4]]
                    if (this.tableData[j].others[0] === "保险"){
                        this.tableData[j].others[0] = this.$t("true")
                    } else {
                        this.tableData[j].others[0] = this.$t("false")
                    }
                    if (this.tableData[j].others[1] === "自取"){
                        this.tableData[j].others[1] = this.$t("true")
                    } else {
                        this.tableData[j].others[1] = this.$t("false")
                    }
                    //是否确认
                    if (this.tableData[j].isConfirmed === true ){
                        this.tableData[j].isConfirmed = this.$t(this.tableData[j].isConfirmed.toString())
                    } else {
                        this.tableData[j].isConfirmed = this.$t(this.tableData[j].isConfirmed.toString())
                    }
                    this.order = this.tableData[j]
                } catch (e) {
                }
            })
        },
        updateExchangeRate()
        {
            this.request.get("/others/1").then(res => {
                if (res.code === '200')
                {
                    this.exchangeRate = res.data
                }
            })
        },
        getphotoandannouncement(){
            this.request.get("/others/getphotoandannouncement").then(res => {
                if (res.code === '200')
                {
                    let data = res.data
                    this.photoandannouncement.photo1 =  data.photo1
                    this.photoandannouncement.photo23[0] =  data.photo2
                    this.photoandannouncement.photo23[1] =  data.photo3
                    this.photoandannouncement.announcement1 = JSON.parse(data.announcement1)
                    this.photoandannouncement.announcement2 = JSON.parse(data.announcement2)
                    this.photoandannouncement.announcement3 = JSON.parse(data.announcement3)
                }
            })
        }
    }
}
</script>

<style>

</style>
