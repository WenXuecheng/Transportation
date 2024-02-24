<template>
    <div>
        <template>
            <div >
                <el-row :gutter="20">
                    <el-col  :xs="24" :sm="6" :md="6" :lg="6" :xl="6" >
                        <div >
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
                                    <el-statistic group-separator="," :precision="2" decimal-separator="." :value="usrmbRates" :title="$t('usrmbRate')">
                                        <template slot="prefix">
                                            <i class="el-icon-s-flag" style="color: red"></i>
                                        </template>
                                        <template slot="suffix">
                                            <i class="el-icon-s-flag" style="color: blue"></i>
                                        </template>
                                    </el-statistic>
                                </div>
                                <h3 style="text-align: center">{{photoandannouncement.announcement1.title}}</h3>
                                <p style="text-align: center">{{photoandannouncement.announcement1.p1}}</p>
                                <p style="text-align: center">{{photoandannouncement.announcement1.p2}}</p>
                            </el-card>
                        </div>
                    </el-col>
                    <el-col  :xs="24" :sm="9" :md="9" :lg="9" :xl="9" style="margin-top: 10px">
                        <div>
                            <el-card class="box-card" style="height: 100%">
                                <div style="margin-top: 1%; color: #606266;height: 20px">
                                    <p style="text-align: center">{{ $t("newgoods") }}</p>
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
                    <el-col  :xs="24" :sm="9" :md="9" :lg="9" :xl="9" style="margin-top: 10px">
                        <el-card class="box-card" style="height: 100%">
                            <template>
                                <div style="margin-top: 1%; color: #606266;height: 20px">
                                    <p style="text-align: center">{{ $t("taobaodewu") }}</p>
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
                <el-row :gutter="20">
                    <el-col  :xs="24" :sm="24" :md="24" :lg="24" :xl="24" style="margin-top: 10px">
                        <el-card class="box-card" >
                            <div v-html="html"></div>
                        </el-card>
                    </el-col>
                </el-row>
            </div>
        </template>

        <el-dialog
            :visible.sync="DialogVisible"
            fullscreen
            :show-close="false">
            <h2 class="heading h3 mb-0">Wellcome to Aurexp!</h2>
            <button type="button" class="btn btn-block btn-outline-tertiary mt-5" @click="jump2createorder">{{$t("createorder")}}</button>
            <button type="button" class="btn btn-block btn-outline-tertiary mt-5" @click="jump2searchorder">{{$t("searchorder")}}</button>
            <button type="button" class="btn btn-block btn-outline-tertiary mt-5" @click="DialogVisible = false">{{$t("信息")}}</button>
            <button type="button" class="btn btn-block btn-outline-tertiary mt-5" @click="logout">{{$t("logout")}}</button>
        </el-dialog>

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
            //第一个大窗口
            DialogVisible:true,
            html: '',
            exchangeRate: null,
            usrmbRates: null,
            photoandannouncement: {
                photo1: 'https://www.ups.com/assets/resources/supplychain/images/350x197/m15-350x197-fast-fulfillment-assembly.jpg',
                photo23: ['https://www.ups.com/assets/resources/supplychain/images/350x197/m15-350x197-fast-fulfillment-assembly.jpg'],
                announcement1: {title: "None", p1:"None", p2:"None"},
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
                "username":"",
                "weight":"",
                "orderStatus":"",
            },
            tableData: [],
            dialogVisible: false,
        }
    },
    created() {

    },
    mounted() {
        const loading = this.$loading({
            lock: true,
            text: 'Loading',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.8)'
        });
        setTimeout(() => {
            loading.close();
        }, 10000);
        this.updateExchangeRate()
        this.updateUsrmbRate()
        this.request.get("/others/getphotoandannouncement").then(res => {
            if (res.code === '200')
            {
                let data = res.data
                this.photoandannouncement.photo1 =  data.photo1
                this.photoandannouncement.photo23[0] =  data.photo2
                this.photoandannouncement.photo23[1] =  data.photo3
                this.photoandannouncement.announcement1 = JSON.parse(data.announcement1)
                this.request.get("/article/1").then(res => {
                    if (res.code === '200')
                    {
                        this.html = res.data.content
                        loading.close();
                    }
                })
            }
            if (res.code ==="401"){
                this.logout()
                loading.close()
            }
        })
    },
    methods: {
        updateExchangeRate()
        {
            this.request.get("/others/1").then(res => {
                if (res.code === '200')
                {
                    this.exchangeRate = res.data
                }
            })
        },
        updateUsrmbRate()
        {
            this.request.get("/others/1/UsRmbGet/1").then(res => {
                if (res.code === '200')
                {
                    this.usrmbRates = res.data
                }
            })
        },
        logout(){
            this.$emit("logout")
        },
        jump2createorder(){
            this.$router.push("/front/createorder").catch(() =>{})
        },
        jump2searchorder(){
            this.$router.push("/front/searchorder").catch(() =>{})
        }
    }
}
</script>

<style>

</style>
