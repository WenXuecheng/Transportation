<template>
    <div style="position: relative; right: -15%;top: 10%; width: 60%">
        <el-card style="width: 500px;position: relative;top: 20%; right: -25%">
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
export default {
    name: "Password",
    data() {
        return {
            form: {},
            user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
            rules: {
                password: [
                    { required: true, message: this.$t('entertheoriginalpassword'), trigger: 'blur' },
                    { min: 3, message: this.$t('Nolessthandigitsinlength'), trigger: 'blur' }
                ],
                newPassword: [
                    { required: true, message: this.$t('enteranewpassword'), trigger: 'blur' },
                    { min: 3, message: this.$t('Nolessthandigitsinlength'), trigger: 'blur' }
                ],
                confirmPassword: [
                    { required: true, message: this.$t('enterYourPassword'), trigger: 'blur' },
                    { min: 3, message: this.$t('Nolessthandigitsinlength'), trigger: 'blur' }
                ],
            }
        }
    },
    created() {
        this.form.username = this.user.username
    },
    methods: {
        save() {
            this.$refs.pass.validate((valid) => {
                if (valid) {
                    if (this.form.newPassword !== this.form.confirmPassword) {
                        this.$message.error(this.$t('notSame'))
                        return false
                    }
                    this.request.post("/user/password", this.form).then(res => {
                        if (res.code === '200') {
                            this.$message.success(this.$t('修改成功'))
                            this.$store.commit("logout")
                        } else {
                            this.$message.error(res.msg)
                        }
                    })
                }
            })
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
