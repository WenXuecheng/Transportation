<template>
    <div style="position: relative; right: -25%;top: 10%">
    <el-card style="width: 500px; ">
        <el-form label-width="80px" size="small">
            <el-form-item label="用户名">
                <el-input v-model="form.username" disabled autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="昵称">
                <el-input v-model="form.nickname" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="邮箱">
                <el-input v-model="form.email" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="电话">
                <el-input v-model="form.phone" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="地址">
                <el-input v-model="form.address" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button v-no-more-click type="primary" @click="save">确 定</el-button>
            </el-form-item>
        </el-form>
    </el-card>
    </div>
</template>

<script>
import {serverIp} from "../../public/config";
import {serverPort} from "../../public/config";
export default {
    name: "Person",
    data() {
        return {
            form: {},
            user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
            serverIp: serverIp,
            serverPort: serverPort
        }
    },
    created() {
        this.getUser().then(res => {
            if (res!=null){
                this.form = res
            }
        })
    },
    methods: {
        async getUser() {
            return (await this.request.get("/user/username/" + this.user.username)).data
        },
        save() {
            this.request.post("/user", this.form).then(res => {
                console.log(res)
                if (res.code === '200') {
                    this.$message.success("保存成功")

                    // 更新浏览器存储的用户信息
                    this.getUser().then(res => {
                        res.token = JSON.parse(localStorage.getItem("user")).token
                        localStorage.setItem("user", JSON.stringify(res))
                    })
                    // 触发父级更新User的方法
                    this.$emit("refreshUser")
                } else {
                    this.$message.error("保存失败")
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
