<script>
import {defineComponent} from 'vue'
import request from "@/utils/request";
import router, {resetRouter} from "@/router";
export default defineComponent({
    name: "createorder",
    data() {

        var validatestr = (rule, value, callback) => {
            if(this.isValidString(value) == false){
                // this.$refs.ruleForm.validateField('checkPass');
                callback(new Error(this.$t('entercorrectly').toString()));
            } else {
                callback();
            }
        };
        var validatePhone = (rule, value, callback) => {
            if(this.isValidStringPhone(value) == false){
                // this.$refs.ruleForm.validateField('checkPass');
                callback(new Error('Example：89692184441'));
            } else {
                callback();
            }
        };
        return {
            //物品类型
            options: [{
                value: '快递货',
                label: this.$t('快递货')
            }, {
                value: '鞋子',
                label: this.$t('鞋子')
            }, {
                value: '食品',
                label: this.$t('食品')
            }, {
                value: '电子产品',
                label: this.$t('电子产品')
            }, {
                value: '箱包',
                label: this.$t('箱包')
            }, {
                value: '小百货',
                label: this.$t('小百货')
            }, {
                value: '化妆品',
                label: this.$t('化妆品')
            }, {
                value: '服装',
                label: this.$t('服装')
            }, {
                value: '其他',
                label: this.$t('其他')
            }],
            value: '',
            ruleForm: {
                orderName: '',
                sendRegion: 'China',
                receiveRegion: '',
                receiveAdress: '',
                dateOut: '12-15days',
                deliveryNum: 1,
                others: [],
                name: '',
                phone: '',
                email: '',
                details: '',
                remark: '',
                username: '',

            },
            DialogVisible : false,
            orderDeliverys: [],
            rules: {
                sendRegion: [
                    { required: true, message: this.$t('entercorrectly').toString(), trigger: 'change' },

                ],
                receiveRegion: [
                    { required: true, message: this.$t('entercorrectly').toString(), trigger: 'change' }
                ],
                deliveryNum:[
                    { required: true, message: ''},
                ],
                dateOut: [
                    { type: 'date', required: true, message: this.$t('entercorrectly').toString(), trigger: 'change' }
                ],
                name: [
                    { required: true, message: this.$t('entercorrectly').toString(), trigger: 'blur' },
                    { validator: validatestr,  trigger: 'blur'}
                ],
                phone: [
                    { required: true, message: this.$t('entercorrectly').toString(), trigger: 'blur' },
                    { validator: validatePhone,  trigger: 'blur'}
                ],
                email: [
                    { required: true, message: this.$t('entercorrectly').toString(), trigger: 'blur'},
                    { validator: validatestr,  trigger: 'blur'}
                ],
                receiveAdress: [
                    { required: true, message: this.$t('entercorrectly').toString(), trigger: 'blur' },
                    { validator: validatestr,  trigger: 'blur'}
                ],
                details: [
                    { required: false, message: this.$t('entercorrectly').toString(), trigger: 'blur' },
                    { validator: validatestr,  trigger: 'blur'}
                ],
                remark: [
                    { required: false, message: this.$t('entercorrectly').toString(), trigger: 'blur' },
                    { validator: validatestr,  trigger: 'blur'}
                ],
            },
            vMode: ["1","2"] //打开折叠窗口
        };
    },
    created() {
        for (let i =0;i<10;i++){this.orderDeliverys.push({
            trackingNumber:  this.$t("DN"),
            remark:  this.$t("备注"),
            type:'',
            orderId: null
        })}
    },
    methods: {
        //表格上传操作
        submitForm(formName) {
            //加载
            const loading = this.$loading({
                lock: true,
                text: 'Loading',
                spinner: 'el-icon-loading',
                background: 'rgba(0, 0, 0, 0.8)'
            });
            setTimeout(() => {
                loading.close();
            }, 10000);
            //
            this.updateuser()
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.setOthers()
                    this.setReceiveRegion()
                    request.post("/order", this.ruleForm).then(res => {
                        if (res.code === '200') {
                            //连接订单和快递单号
                            let orderId = res.data
                            for(let i = 0; i< this.ruleForm.deliveryNum; i++){
                                this.orderDeliverys[i].orderId = orderId
                                request.post("/order-delivery/add", this.orderDeliverys[i]).then(res => {
                                    if(res.code !== '200'){
                                        this.$message.error("No."+ i+1 +this.$t('entercorrectly'))
                                        this.resetForm('ruleForm')
                                    }
                                })
                            }
                            //清空
                            this.resetForm('ruleForm')
                            this.orderDeliverys = []
                            for (let i =0;i<10;i++){this.orderDeliverys.push({
                                trackingNumber: this.$t("DN"),
                                remark: this.$t("remark"),
                                type:'',
                                orderId:null
                            })}
                            loading.close()
                            this.open()
                        } else {
                            loading.close()
                            this.resetForm('ruleForm')
                            this.$message.error(this.$t('entercorrectly'))
                        }
                    })
                } else {
                    loading.close()
                    return false;
                }
            });
        },
        //创建订单后的提醒
        open() {
            const h = this.$createElement;
            this.DialogVisible = true
            // this.$notify({
            //     title: this.$t('Ordercreatedsuccessfully'),
            //     message: h('i', { style: 'color: teal'}, this.$t('notecheckyouremail').toString())
            // });
        },
        isValidStringPhone(str) {
            try {
                const pattern = /^[0-9]+$/;
                return pattern.test(str);
            } catch (e){
                return null
            }
        },
        isValidString(str) {
            try {
                // 检查首个字符是否为空格
                if (str !== '' && str[0] === ' ') {
                    return false;
                }
                return true;
            } catch (e){
                return null
            }
        },
        updateuser(){
            try {
                this.user=localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
                if (this.user === null){
                } else {
                    this.ruleForm.username=this.user.username
                }
            } catch (e){
                this.$store.commit("logout")
                this.$message.error(this.$t('Signinagain').toString())
            }
        },
        setOthers(){
            if (this.ruleForm.others.length === 0){
                this.ruleForm.others = "无/无/无/无/无"
            } else
            {
                let others = ""
                for(let i = 0; i<this.ruleForm.others.length; i++)
                {
                    if (this.ruleForm.others[i] === "Страхование"){
                        this.ruleForm.others[i] = "保险"
                    }
                    if (this.ruleForm.others[i] === "Самовывоз"){
                        this.ruleForm.others[i] = "自取"
                    }
                    if (this.ruleForm.others[i] === "Деревянная рама"){
                        this.ruleForm.others[i] = "木板"
                    }
                    if (this.ruleForm.others[i] === "Гидроизоляция"){
                        this.ruleForm.others[i] = "防水"
                    }
                    if (this.ruleForm.others[i] === "Поддоны"){
                        this.ruleForm.others[i] = "托盘"
                    }
                }
                for(let k = 0; k<this.ruleForm.others.length; k++){
                    if (this.ruleForm.others[k] === "保险"){
                        others = others + "保险"
                        break
                    } else {
                        if (k === this.ruleForm.others.length-1){
                            others = others + "无"
                        }
                    }
                }
                others = others + "/"
                for(let k = 0; k<this.ruleForm.others.length; k++){
                    if (this.ruleForm.others[k] === "自取"){
                        others = others + "自取"
                        break
                    } else {
                        if (k === this.ruleForm.others.length-1){
                            others = others + "无"
                        }
                    }
                }
                others = others + "/"
                for(let k = 0; k<this.ruleForm.others.length; k++){
                    if (this.ruleForm.others[k] === "木板"){
                        others = others + "木板"
                        break
                    } else {
                        if (k === this.ruleForm.others.length-1){
                            others = others + "无"
                        }
                    }
                }
                others = others + "/"
                for(let k = 0; k<this.ruleForm.others.length; k++){
                    if (this.ruleForm.others[k] === "防水"){
                        others = others + "防水"
                        break
                    } else {
                        if (k === this.ruleForm.others.length-1){
                            others = others + "无"
                        }
                    }
                }
                others = others + "/"
                for(let k = 0; k<this.ruleForm.others.length; k++){
                    if (this.ruleForm.others[k] === "托盘"){
                        others = others + "托盘"
                        break
                    } else {
                        if (k === this.ruleForm.others.length-1){
                            others = others + "无"
                        }
                    }
                }
                this.ruleForm.others = others
            }
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        setReceiveRegion(){
            if (this.ruleForm.sendRegion === "Moscow") {
                this.ruleForm.receiveRegion = "China"
            }else if (this.ruleForm.sendRegion === "China") {
                this.ruleForm.receiveRegion = "Moscow"
            }
        },
        lookorder(){
            this.$router.push("/front/searchorder").catch(() =>{})
        },
        again(){
            this.DialogVisible = false
        }
    }
})
</script>

<template>
    <div>
        <el-collapse v-model="vMode">
            <el-collapse-item :title="$t('orderPlacementProcess')" name="1">
                <div><el-alert type="success">{{ $t("noteOrder.p1") }}</el-alert></div>
                <div><el-alert type="success">{{ $t("noteOrder.p2") }}</el-alert></div>
                <div><el-alert type="success">{{ $t("noteOrder.p3") }}</el-alert></div>
            </el-collapse-item>
            <el-collapse-item :title="$t('instructionsForPlacingAnOrder')" name="2">
                <div><el-alert type="error">{{ $t("noteOrder.p4") }}</el-alert></div>
                <div><el-alert type="success">{{ $t("noteOrder.p5") }}</el-alert></div>
                <div><el-alert type="success">{{ $t("noteOrder.p6") }}</el-alert></div>
                <div><el-alert type="warning">{{ $t("noteOrder.p7") }}</el-alert></div>
                <div><el-alert type="warning">{{ $t('pay.transferringMoneyViaMobilePhone') }}</el-alert></div>
            </el-collapse-item>
        </el-collapse>

        <el-card class="box-card">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="35%">
                <el-row>
                    <el-col :xs="20" :sm="18" :md="18" :lg="18" :xl="18">
                        <el-form-item :label="$t('orderName')" prop="orderName">
                        <el-input v-model="ruleForm.orderName" disabled ></el-input>
                    </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="20" :sm="18" :md="18" :lg="18" :xl="18">
                    <el-form-item :label="$t('sendRegion')" prop="sendRegion" required>
                        <el-select v-model="ruleForm.sendRegion" disabled :placeholder="$t('selectServiceArea')">
                            <el-option :label="$t('Moscow')" value="Moscow"></el-option>
                            <el-option :label="$t('China')" value="China"></el-option>
                        </el-select>
                    </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="20" :sm="18" :md="18" :lg="18" :xl="18">
                        <el-form-item :label="$t('dateOut')" required>
                            <el-select v-model="ruleForm.dateOut" >
                                <el-option :label="$t('time1')" value="12-15days"></el-option>
                                <el-option :label="$t('time2')" value="18-25days"></el-option>
                            </el-select>
                            <div><el-alert type="warning">{{ $t("noteOrder.p8") }}</el-alert></div>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="20" :sm="18" :md="18" :lg="18" :xl="18">
                        <el-form-item :label="$t('deliveryNum')" prop="deliveryNum">
                            <template>
                                <template>
                                    <el-input-number  v-model="ruleForm.deliveryNum" :step="1" step-strictly :max="10" :min="1"></el-input-number>
                                </template>
                            </template>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="20" :sm="18" :md="18" :lg="18" :xl="18">
                        <el-form-item>
                            <div v-for="o in ruleForm.deliveryNum" :key="o">
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

                <el-row>
                    <el-col :xs="20" :sm="18" :md="18" :lg="18" :xl="18">
                        <el-form-item prop="others">
                            <el-checkbox-group v-model="ruleForm.others">
                                <el-checkbox :label="$t('others.insurance')" name="type"></el-checkbox>
                                <el-checkbox :label="$t('others.selfPickup')" name="type"></el-checkbox>
                                <el-checkbox :label="$t('others.mujia')" name="type"></el-checkbox>
                                <el-checkbox :label="$t('others.fangshui')" name="type"></el-checkbox>
                                <el-checkbox :label="$t('others.tuopan')" name="type"></el-checkbox>
                            </el-checkbox-group>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="20" :sm="18" :md="18" :lg="18" :xl="18">
                        <el-form-item :label="$t('name')" prop="name">
                            <el-input
                                type="text"
                                :placeholder="$t('pleaseEnterContent')"
                                v-model="ruleForm.name"
                                show-word-limit
                            >
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="20" :sm="18" :md="18" :lg="18" :xl="18">
                        <el-form-item :label="$t('phone')" prop="phone">
                            <el-input
                                type="text"
                                :placeholder="$t('pleaseEnterContent')"
                                v-model="ruleForm.phone"
                                show-word-limit
                            >
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="20" :sm="18" :md="18" :lg="18" :xl="18">
                        <el-form-item :label="$t('email')" prop="email">
                            <el-input
                                type="text"
                                :placeholder="$t('pleaseEnterContent')"
                                v-model="ruleForm.email"
                                show-word-limit
                            >
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="20" :sm="18" :md="18" :lg="18" :xl="18">
                        <el-form-item :label="$t('receiveAdress')" prop="receiveAdress">
                            <el-input
                                type="text"
                                :placeholder="$t('pleaseEnterContent')"
                                v-model="ruleForm.receiveAdress"
                                maxlength="50"
                                show-word-limit
                            >
                            </el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="20" :sm="18" :md="18" :lg="18" :xl="18">
                        <el-form-item :label="$t('details')" prop="details">
                            <el-input type="textarea" v-model="ruleForm.details" maxlength="50"
                                      show-word-limit></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>

                <el-row>
                    <el-col :xs="20" :sm="18" :md="18" :lg="18" :xl="18">
                        <el-form-item :label="$t('remark')" label-width="40%" prop="remark">
                            <el-input type="textarea" v-model="ruleForm.remark" maxlength="50"
                                      show-word-limit></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                        <el-form-item>
                            <el-col :xs="15" :sm="7" :md="5" :lg="6" :xl="6">
                                <el-button v-no-more-click-ten type="primary" @click="submitForm('ruleForm')">{{ $t('createNow') }}</el-button>
                            </el-col>
                            <el-col :xs="12" :sm="1" :md="1" :lg="6" :xl="6">
                                <el-button @click="resetForm('ruleForm')">{{ $t('reset') }}</el-button>
                            </el-col>
                        </el-form-item>

                </el-row>
            </el-form>
        </el-card>

        <el-dialog
            :visible.sync="DialogVisible"
            fullscreen
            :title = "$t('Ordercreatedsuccessfully')"
            :show-close="false">
            <h2 class="heading h3 mb-0"> {{$t('notecheckyouremail')}} </h2>
            <button type="button" class="btn btn-block btn-outline-tertiary mt-5" @click="lookorder">{{$t("查看订单")}}</button>
            <button type="button" class="btn btn-block btn-outline-tertiary mt-5" @click="again">{{$t("再次创建")}}</button>
        </el-dialog>

    </div>
</template>

<style scoped>

</style>