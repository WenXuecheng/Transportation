import Vue from 'vue'
import Vuex from 'vuex'
import router, {resetRouter} from "@/router";
import {Message} from "element-ui";
Vue.use(Vuex)

const store = new Vuex.Store({
    state: {
        currentPathName: ''
    },
    mutations: {
        setPath (state) {
            state.currentPathName = localStorage.getItem("currentPathName")
        },
        logout() {
            try {// 清空缓存
                localStorage.removeItem("user")
                localStorage.removeItem("menus")
                router.push("/login")
                // 重置路由
                resetRouter()
            } catch (error){
                Message({
                    message: error,
                    type: 'error'
                })
            }
        }
    }
})

export default store
