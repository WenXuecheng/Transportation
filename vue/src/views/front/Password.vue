<template>
    <div style="position: relative">
        <el-card style="max-width: 500px;margin: auto">
            <el-form label-width="120px" size="small" :model="form" :rules="rules" ref="pass">

                <el-form-item :label="$t('originalPassword')" prop="password">
                    <el-input v-model="form.password" autocomplete="off" show-password></el-input>
                </el-form-item>
                <el-form-item :label="$t('newPassword')" prop="newPassword">
                    <el-input v-model="form.newPassword" autocomplete="off" show-password></el-input>
                </el-form-item>
                <el-form-item :label="$t('confirmPassword')" prop="confirmPassword">
                    <el-input v-model="form.confirmPassword" autocomplete="off" show-password></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button v-no-more-click type="primary" @click="save">{{ $t('confirm') }}</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script>
import {ValidStringPattern} from "../../../public/config";
export default {
    name: "Password",
    data() {
        var validateOthers = (rule, value, callback) => {
            if(this.isValidString(value) == false){
                // this.$refs.ruleForm.validateField('checkPass');
                callback(new Error('цифры, буквы и @.*/-+_'));
            } else {
                callback();
            }
        };
        return {
            form: {},
            user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
            rules: {
                password: [
                    { required: true, message: this.$t('originalPassword'), trigger: 'blur' },
                    { min: 5, max: 50, message: 'Длина от 5 до 50 символов', trigger: 'blur' },
                    { validator: validateOthers,  trigger: 'blur'}
                ],
                newPassword: [
                    { required: true, message: this.$t('enteranewpassword'), trigger: 'blur' },
                    { min: 5, max: 50, message: this.$t('Nolessthandigitsinlength'), trigger: 'blur' },
                    { validator: validateOthers,  trigger: 'blur'}
                ],
                confirmPassword: [
                    { required: true, message: this.$t('enterYourPassword'), trigger: 'blur' },
                    { min: 5, max: 50, message: this.$t('Nolessthandigitsinlength'), trigger: 'blur' },
                    { validator: validateOthers,  trigger: 'blur'}
                ],
            }
        }
    },
    created() {
        this.form.username = this.user.username
        this.form.id = this.user.id
    },
    methods: {
        save() {
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
            this.$refs.pass.validate((valid) => {
                if (valid) {
                    if (this.form.newPassword !== this.form.confirmPassword) {
                        this.$message.error(this.$t('notSame'))
                        loading.close();
                        return false
                    }
                    this.request.post("/user/password", this.form).then(res => {
                        if (res.code === '200') {
                            this.$message.success(this.$t('optsucc'))
                            this.$store.commit("logout")
                        } else {
                            this.$message.error(this.$t('optfail'))
                        }
                        loading.close();
                    })
                }else {
                    loading.close();
                }
            })
        },
        isValidString(str) {
            try {
                //英文字母数字俄语字母/*-+.@_
                return ValidStringPattern.test(str);
            } catch (e){
                return null
            }
        },
    }
}
</script>

<style>
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

</style>
