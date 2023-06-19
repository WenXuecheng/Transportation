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
            ruleForm: {
                orderName: '',
                sendRegion: '',
                receiveRegion: '',
                receiveAdress: '',
                dateOut: '',
                deliveryNum: 1,
                others: [],
                name: '',
                phone: '',
                email: '',
                details: '',
                remark: '',
                pay: '',
                username: '',
            },
            rules: {
                sendRegion: [
                    { required: true, message: this.$t('entercorrectly').toString(), trigger: 'change' },

                ],
                receiveRegion: [
                    { required: true, message: this.$t('entercorrectly').toString(), trigger: 'change' }
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
                    { required: true, message: this.$t('entercorrectly').toString(), trigger: 'blur' },
                    { validator: validatestr,  trigger: 'blur'}
                ],
                remark: [
                    { required: false, message: this.$t('entercorrectly').toString(), trigger: 'blur' },
                    { validator: validatestr,  trigger: 'blur'}
                ],
                pay: [
                    { required: true, message: this.$t('entercorrectly').toString(), trigger: 'blur' },
                    { validator: validatestr,  trigger: 'blur'}
                ],
            },
            vMode: ["1","2"] //打开折叠窗口
        };
    },
    created() {
    },
    methods: {
        //表格上传操作
        submitForm(formName) {
            this.updateuser()
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.setOthers()
                    this.setPay()
                    this.setReceiveRegion()
                    request.post("/order", this.ruleForm).then(res => {
                        if (res.code === '200') {
                            this.open()
                            this.resetForm('ruleForm')
                        } else {
                            this.$message.error(this.$t('entercorrectly'))
                        }
                    })
                } else {
                    return false;
                }
            });
        },
        //创建订单后的提醒
        open() {
            const h = this.$createElement;

            this.$notify({
                title: this.$t('Ordercreatedsuccessfully'),
                message: h('i', { style: 'color: teal'}, this.$t('notecheckyouremail').toString())
            });
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
        setPay(){
            if (this.ruleForm.pay === "Оплата в магазине"){
                this.ruleForm.pay = "到店付款"
            }
            if (this.ruleForm.pay === "Онлайн оплата"){
                this.ruleForm.pay = "在线付款"
            }
        },
        setOthers(){
            if (this.ruleForm.others.length === 0){
                this.ruleForm.others = "没有/没有"
            } else if(this.ruleForm.others.length === 1)
            {
                if(this.ruleForm.others[0] === "Страхование"){
                    this.ruleForm.others = "保险" + '/' + "没有"
                }
                if(this.ruleForm.others[0] === "Самовывоз"){
                    this.ruleForm.others = "没有" + '/' + "自取"
                }
                if(this.ruleForm.others[0] === "保险"){
                    this.ruleForm.others = "保险" + '/' + "没有"
                }
                if(this.ruleForm.others[0] === "自取"){
                    this.ruleForm.others = "没有" + '/' + "自取"
                }
            }else if (this.ruleForm.others.length ===2) {
                this.ruleForm.others = "保险/自取"
            }
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
        },
        setReceiveRegion(){
            if (this.ruleForm.sendRegion === "Moscow") {
                this.ruleForm.receiveRegion = "Guangdong"
            }else if (this.ruleForm.sendRegion === "Guangdong") {
                this.ruleForm.receiveRegion = "Moscow"
            }
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
            </el-collapse-item>
        </el-collapse>

        <el-card class="box-card">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px">
                <el-form-item :label="$t('orderName')" prop="orderName">
                    <el-input v-model="ruleForm.orderName" disabled ></el-input>
                </el-form-item>
                <el-form-item :label="$t('sendRegion')" prop="sendRegion" required>
                    <el-select v-model="ruleForm.sendRegion" :placeholder="$t('selectServiceArea')" style="width: 25%">
                        <el-option :label="$t('Moscow')" value="Moscow"></el-option>
                        <el-option :label="$t('Guangdong')" value="Guangdong"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item :label="$t('dateOut')" required>
                    <el-select v-model="ruleForm.dateOut" :placeholder="$t('selectServiceArea')" style="width: 25%">
                        <el-option :label="$t('time1')" value="12-15days"></el-option>
                        <el-option :label="$t('time2')" value="18-25days"></el-option>
                    </el-select>
                    <div><el-alert type="warning" style="width: 25%;">{{ $t("noteOrder.p8") }}</el-alert></div>
                </el-form-item>


                <el-form-item :label="$t('deliveryNum')" prop="deliveryNum">
                    <template>
                        <template>
                            <el-input-number v-model="ruleForm.deliveryNum" :step="1" step-strictly></el-input-number>
                        </template>
                    </template>
                </el-form-item>
                <el-form-item :label="$t('others.mode')" prop="others">
                    <el-checkbox-group v-model="ruleForm.others">
                        <el-checkbox :label="$t('others.insurance')" name="type"></el-checkbox>
                        <el-checkbox :label="$t('others.selfPickup')" name="type"></el-checkbox>
                    </el-checkbox-group>
                </el-form-item>
                <el-form-item :label="$t('name')" prop="name">
                    <el-input
                        type="text"
                        :placeholder="$t('pleaseEnterContent')"
                        v-model="ruleForm.name"
                        show-word-limit
                        style="width: 25%"
                    >
                    </el-input>
                </el-form-item>
                <el-form-item :label="$t('phone')" prop="phone">
                    <el-input
                        type="text"
                        :placeholder="$t('pleaseEnterContent')"
                        v-model="ruleForm.phone"
                        show-word-limit
                        style="width: 25%"
                    >
                    </el-input>
                </el-form-item>
                <el-form-item :label="$t('email')" prop="email">
                    <el-input
                        type="text"
                        :placeholder="$t('pleaseEnterContent')"
                        v-model="ruleForm.email"
                        show-word-limit
                    >
                    </el-input>
                </el-form-item>
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
                <el-form-item :label="$t('details')" prop="details">
                    <el-input type="textarea" v-model="ruleForm.details" maxlength="50"
                              show-word-limit></el-input>
                </el-form-item>
                <el-form-item :label="$t('remark')" prop="remark">
                    <el-input type="textarea" v-model="ruleForm.remark" maxlength="50"
                              show-word-limit></el-input>
                </el-form-item>
                <el-form-item :label="$t('pay.paymentMethods')" prop="pay">
                <el-row>
                    <el-col :span="10"><div>
                        <el-radio-group v-model="ruleForm.pay">
                            <el-radio-button :label="$t('pay.paymentInShop')"></el-radio-button>
                            <el-radio-button :label="$t('pay.onlineTransfer')"></el-radio-button>
                        </el-radio-group>
                    </div></el-col>

                    <el-col :span="24"><div class="grid-content bg-purple-dark">
                        <el-tag  type="success" size="medium ">{{ $t('pay.transferringMoneyViaMobilePhone') }}</el-tag>
                    </div></el-col>
                </el-row>
                </el-form-item>
                <el-form-item>
                    <el-button v-no-more-click-ten type="primary" @click="submitForm('ruleForm')">{{ $t('createNow') }}</el-button>
                    <el-button @click="resetForm('ruleForm')">{{ $t('reset') }}</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<style scoped>

</style>