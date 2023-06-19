<template>
        <div style="line-height: 60px; display: flex">
            <div style="flex: 1;">
                <span :class="collapseBtnClass" style="cursor: pointer; font-size: 18px" @click="collapse"></span>
                <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px">
                    <el-breadcrumb-item >首页</el-breadcrumb-item>
                    <el-breadcrumb-item>{{ currentPathName }}</el-breadcrumb-item>
                </el-breadcrumb>
            </div>
            <div style="display: inline-block; position: relative; right: 0%">
                <img :src="user.avatarUrl" alt=""
                     style="width: 30px; border-radius: 50%; position: relative; top: 10px; right: 5px">
                <span>{{ user.nickname }}</span>
            </div>
            <el-dropdown style="cursor: pointer">
                <span class="el-dropdown-link"><i class="el-icon-arrow-down el-icon--right"></i></span>
                <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center">
                    <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                        <span style="text-decoration: none" @click="$router.push('/manage/password')">修改密码</span>
                    </el-dropdown-item>
                    <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                        <span style="text-decoration: none" @click="$router.push('/manage/person')">个人信息</span>
                    </el-dropdown-item>
                    <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                        <span style="text-decoration: none" @click="logout">退出</span>
                    </el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>
</template>

<script>
export default {
    name: "Header",
    props: {
        collapseBtnClass: String,
        user: Object
    },
    computed: {
        currentPathName() {
            return this.$store.state.currentPathName;　　//需要监听的数据
        }
    },
    methods: {
        logout() {
            this.$store.commit("logout")
            this.$message.success("Выход успешный")
        },
        collapse() {
            this.$emit("asideCollapse")
        },
    },
    data() {
        return {}
    }
}
</script>

<style scoped>
.el-col {
    border-radius: 4px;
}
.bg-purple-dark {
    background: #99a9bf;
}
.bg-purple {
    background: #d3dce6;
}
.bg-purple-light {
    background: #e5e9f2;
}
.grid-content {
    border-radius: 4px;
    min-height: 36px;
}


</style>

