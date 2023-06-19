<template>
    <div style="position: relative; right: -25%;top: 10%; width: 60%">
    <el-card style="width: 500px;">
        <el-form label-width="80px" size="small">
            <el-form-item :label="$t('username')">
                <el-input v-model="form.username" disabled autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item :label="$t('nickname')">
                <el-input v-model="form.nickname" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item :label="$t('email')">
                <el-input v-model="form.email" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item :label="$t('phone')">
                <el-input v-model="form.phone" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item :label="$t('address')">
                <el-input type="textarea" v-model="form.address" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button v-no-more-click type="primary" @click="save">{{$t('confirm')}}</el-button>
            </el-form-item>
        </el-form>
    </el-card>
    </div>
</template>

<script>
import {serverIp} from "../../../public/config";

export default {
    name: "Person",
    data() {
        return {
            serverIp: serverIp,
            form: {},
            user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
        }
    },
    created() {
        this.getUser().then(res => {
            this.form = res
        })
    },
    methods: {
        async getUser() {
            return (await this.request.get("/user/username/" + this.user.username)).data
        },
        save() {
            console.log(this.form)
            this.request.post("/user/saveByUser", this.form).then(res => {
                if (res.code === '200') {
                    this.$message.success("Сохранено успешно")

                    // 触发父级更新User的方法
                    this.$emit("refreshUser")

                    // 更新浏览器存储的用户信息
                    this.getUser().then(res => {
                        res.token = JSON.parse(localStorage.getItem("user")).token
                        localStorage.setItem("user", JSON.stringify(res))
                    })

                } else {
                    this.$message.error("Не удалось сохранить")
                }
            })
        },
        handleAvatarSuccess(res) {
            this.form.avatarUrl = res
        }
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
