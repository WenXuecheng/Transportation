<template>
    <div>
        <main class="main">

            <aside class="sidebar" id="nav_docs">
                <div class="sidebar-brand">
                    <h1 class="font-weight-400"><a href="/home"><span class="font-weight-700">Aurexp</span> {{ $t("topic") }}</a></h1>
                </div>
                <div class="scroll-wrapper scrollbar-inner" style="position: relative;"><div class="scrollbar-inner scroll-content scroll-scrolly_visible" style="height: auto; margin-bottom: 0px; margin-right: 0px; max-height: 488px;">
                    <ul class="navigation pr-3">
                        <li class="navigation-title">{{ $t("menu") }}</li>
                        <li class="navigation-item">
                            <a href="/front/home" class="navigation-link">{{ $t("home") }}</a>
                        </li>
                        <li class="navigation-item">
                            <a href="/front/createorder"  class="navigation-link">{{ $t("createorder") }}</a>
                        </li>
                        <li class="navigation-item">
                            <span class="float-right badge badge-primary" ></span>
                            <a href="/front/searchorder" class="navigation-link">{{ $t("searchorder") }}</a>
                        </li>
                        <li class="navigation-title">{{ user.nickname }}</li>
                        <li class="navigation-item">
                            <a href="/front/password" class="navigation-link"> {{$t('password')}}</a>
                        </li>
                        <li class="navigation-item">
                            <a href="/front/person" class="navigation-link">{{$t('person')}}</a>
                        </li>
                        <li class="navigation-item">
                            <a href="/login" class="navigation-link" @click="logout"> {{$t('logout')}}</a>
                        </li>
                    </ul>
                </div><div class="scroll-element scroll-x scroll-scrolly_visible"><div class="scroll-element_outer"><div class="scroll-element_size"></div><div class="scroll-element_track"></div><div class="scroll-bar" style="width: 88px;"></div></div></div><div class="scroll-element scroll-y scroll-scrolly_visible"><div class="scroll-element_outer"><div class="scroll-element_size"></div><div class="scroll-element_track"></div><div class="scroll-bar" style="height: 251px; top: 109.57px;"></div></div></div></div>
            </aside >
            <section class="content">
                <div class="header">
                    <div class="navigation-trigger d-xl-none" data-action="aside-open" data-target=".sidebar">
                        <div class="navigation-trigger-inner">
                            <i class="navigation-trigger-line"></i>
                            <i class="navigation-trigger-line"></i>
                            <i class="navigation-trigger-line"></i>
                        </div>
                    </div>
                    <div class="header-brand d-xl-none">
                        <h1><a href="/home">Aurexp посылки из китая</a></h1>
                    </div>
                    <div class="header-nav ml-auto">
                        <div class="code-example">
                            <div class="dropdown" style="right: 10%">
                                <button class="btn btn-outline-primary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    {{$t('lang')}}
                                </button>
                                <div class="dropdown-menu">
                                    <a class="dropdown-item" @click="selectRU">Русский</a>
                                    <a class="dropdown-item" @click="selectCN">中文</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="content-inner content-docs">
                    <el-main>
                        <router-view :user="user" @logout="logout"/>
                    </el-main>
                </div>
            </section>
        </main>

    </div>
</template>


<script>


export default {
    name: 'Front',
    data() {
        return {
            tableData: Array(20).fill(item),
            user: {},
            language: null,
        }
    },
    created() {
        this.updateData()
        this.language = localStorage.getItem("lang")
        if (this.language === "cn"){
            this.language = "中文"
        }else {
            this.language = "Русский"
        }
    },
    mounted() {
    },
    methods: {
        updateData(){
            this.user=localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
        },
        logout() {
            this.$store.commit("logout")
            this.$message.success("Выход успешный")
        },
        selectRU(){
            this.language = "Русский"
            localStorage.setItem('lang', 'ru')
            this.$i18n.locale = "ru"
            location.reload()
        },
        selectCN(){
            this.language = "中文"
            localStorage.setItem('lang', 'cn')
            this.$i18n.locale = "cn"
            location.reload()
        },
    },
}
</script>

<style>

</style>