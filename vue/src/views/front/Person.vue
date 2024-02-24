<template>
    <div style="position: relative">
    <el-card style="max-width: 500px;margin: auto">
        <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="80px">
            <el-form-item :label="$t('username')" prop="username">
                <el-input v-model="ruleForm.username" disabled autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item :label="$t('nickname')" prop="nickname">
                <el-input v-model="ruleForm.nickname" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item :label="$t('email')" prop="email">
                <el-input v-model="ruleForm.email"></el-input>
            </el-form-item>
            <el-form-item :label="$t('phone')" prop="phone">
                <el-input v-model="ruleForm.phone"></el-input>
            </el-form-item>
            <el-form-item :label="$t('address')" prop="address">
                <el-input type="textarea" v-model="ruleForm.address"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button v-no-more-click type="primary" @click="save('ruleForm')">{{$t('confirm')}}</el-button>
            </el-form-item>
        </el-form>
    </el-card>
    </div>
</template>

<script>
import {serverIp} from "../../../public/config";
import {ValidStringPattern, ValidPhonePattern, ValidNicknamePattern} from "../../../public/config";
export default {
    name: "Person",
    data() {
        var validateOthers = (rule, value, callback) => {
            if(this.isValidString(value) == false){
                // this.$refs.ruleForm.validateField('checkPass');
                callback(new Error('цифры, английский, русский языки и @.*/-+_:'));
            } else {
                callback();
            }
        };
        var validateNickname = (rule, value, callback) => {
            if(this.isValidStringNickname(value) == false){
                // this.$refs.ruleForm.validateField('checkPass');
                callback(new Error('цифры, английский и русский языки'));
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
        // 账号密码使用validateOthers，昵称使用validateNickname，电话号码使用validatePhone，地址只限长度
        return {
            serverIp: serverIp,
            pass: '',
            user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
            ruleForm: {},
            rules:{
                username: [
                    { required: true, message: 'имя пользователя', trigger: 'blur' },
                    { min: 5, max: 50, message: 'Длина от 5 до 50 символов', trigger: 'blur' },
                    { validator: validateOthers,  trigger: 'blur'}
                ],
                nickname: [
                    { required: true, message: 'псевдоним', trigger: 'blur' },
                    { min: 1, max: 50, message: 'Длина от 5 до 50 символов', trigger: 'blur' },
                    { validator: validateNickname,  trigger: 'blur'}
                ],
                email: [
                    { required: true, message: 'адрес электронной почты example.com', trigger: 'blur' },
                    { min: 5, max: 50, message: 'Длина от 5 до 50 символов', trigger: 'blur' },
                    { validator: validateOthers,  trigger: 'blur'}
                ],
                phone: [
                    { required: true, message: 'номер телефона', trigger: 'blur' },
                    { min: 5, max: 15, message: 'Длина от 5 до 15 символов', trigger: 'blur' },
                    { validator: validatePhone,  trigger: 'blur'}
                ],
                address: [
                    { required: false, message: '', trigger: 'blur' },
                    { min: 0, max: 200, message: 'Длина от 0 до 200 символов', trigger: 'blur' },
                ],
            },
        }
    },
    created() {
        this.getUser().then(res => {
            this.ruleForm = res
            console.log(this.ruleForm)
        })
    },
    methods: {
        async getUser() {
            return (await this.request.get("/user/username/" + this.user.username)).data
        },
        save() {
            const loading = this.$loading({
                lock: true,
                text: 'Loading',
                spinner: 'el-icon-loading',
                background: 'rgba(0, 0, 0, 0.8)'
            });
            setTimeout(() => {
                loading.close();
            }, 10000);
            this.$refs['ruleForm'].validate((valid) => {
                if (valid) {
                    this.request.post("/user/saveByUser", this.ruleForm).then(res => {
                        if (res.code === '200') {
                            this.$message.success(this.$t('ModifiedSuccessfully'))

                            // 触发父级更新User的方法
                            this.$emit("refreshUser")

                            // 更新浏览器存储的用户信息
                            this.getUser().then(res => {
                                res.token = JSON.parse(localStorage.getItem("user")).token
                                localStorage.setItem("user", JSON.stringify(res))
                            })

                        } else {
                            this.$message.error(this.$t('optfail'))
                        }
                        loading.close();
                    })
                } else {
                    this.$message.error(this.$t('optfail'))
                    loading.close();
                    return false;
                }
            });
        },
        handleAvatarSuccess(res) {
            this.form.avatarUrl = res
        },
        isValidString(str) {
            try {
                return ValidStringPattern.test(str);
            } catch (e){
                return null
            }
        },
        isValidStringPhone(str) {
            try {
                return ValidPhonePattern.test(str);
            } catch (e){
                return null
            }
        },
        isValidStringNickname(str) {
            try {
                return ValidNicknamePattern.test(str);
            } catch (e){
                return null
            }
        },
    }
}
</script>
<style>
.avatar-uploader {
    text-align: center;
    padding-bottom: 10px;
}
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
    width: 138px;
    height: 138px;
    line-height: 138px;
    text-align: center;
}
.avatar {
    width: 138px;
    height: 138px;
    display: block;
}
</style>
