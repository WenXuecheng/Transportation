import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/gloable.css'
import request from "@/utils/request";
import store from "./store/store"
import locale from 'element-ui/lib/locale/lang/en'
import i18n from "./utils/i18n";

// 引入 vue-kikindeditor 需要的文件
import VueKindEditor from 'vue-kindeditor'
import 'kindeditor/kindeditor-all-min.js'
import 'kindeditor/themes/default/default.css'
// 注册 vue-kikindeditor plugin
Vue.use(VueKindEditor)

Vue.config.productionTip = false

Vue.use(ElementUI, { size: "mini" },{ locale });

Vue.prototype.request=request

new Vue({
  i18n,
  router,
  store,
  render: h => h(App)
}).$mount('#app')

Vue.directive('noMoreClick', {
  inserted(el, binding) {
    el.addEventListener('click', e => {
      el.classList.add('is-disabled')
      el.disabled = true
      setTimeout(() => {
        el.disabled = false
        el.classList.remove('is-disabled')
      }, 5000)//我这里设置的是2000毫秒也就是2秒
    })
  }
})

Vue.directive('noMoreClickTen', {
  inserted(el, binding) {
    el.addEventListener('click', e => {
      el.classList.add('is-disabled')
      el.disabled = true
      setTimeout(() => {
        el.disabled = false
        el.classList.remove('is-disabled')
      }, 10000)//我这里设置的是2000毫秒也就是2秒
    })
  }
})
Vue.directive('noMoreClickMin', {
  inserted(el, binding) {
    el.addEventListener('click', e => {
      el.classList.add('is-disabled')
      el.disabled = true
      setTimeout(() => {
        el.disabled = false
        el.classList.remove('is-disabled')
      }, 60000)
    })
  }
})