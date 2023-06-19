import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "../store/store";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: '',
    redirect: "/home",
  },
  {
    path: '/home',
    name: 'home',
    component: () => import('../views/Home.vue')
  },
  {
    path: '/delivery_cn',
    name: 'delivery_cn',
    component: () => import('../views/DeliveryCN.vue')
  },
  {
    path: '/delivery_ru',
    name: 'delivery_ru',
    component: () => import('../views/DeliveryRU.vue')
  },
  {
    path: '/delivery_condition',
    name: 'delivery_condition',
    component: () => import('../views/DeliveryCondition.vue')
  },
  {
    path: '/service',
    name: 'service',
    component: () => import('../views/Service.vue')
  },
  {
    path: '/service_pay',
    name: 'service_pay',
    component: () => import('../views/ServicePay.vue')
  },
  {
    path: '/customs_clearance',
    name: 'customs_clearance',
    component: () => import('../views/CustomsClearance.vue')
  },
  {
    path: '/marketplace',
    name: 'marketplace',
    component: () => import('../views/Marketplace.vue')
  },
  {
    path: '/prohibited_goods',
    name: 'prohibited_goods',
    component: () => import('../views/ProhibitedGoods.vue')
  },
  {
    path: '/rules_individuals',
    name: 'rules_individuals',
    component: () => import('../views/RulesIndividuals.vue')
  },
  {
    path: '/faq',
    name: 'faq',
    component: () => import('../views/FAQ.vue')
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../views/Register.vue')
  },
  {
    path: '/404',
    name: '404',
    component: () => import('../views/404.vue')
  },
  {
    path: '/front',
    name: 'Front',
    component: () => import('../views/front/Front'),
    children: [
      {
        path: 'home',
        name: 'FrontHome',
        component: () => import('../views/front/Home.vue')
      },
      {
        path: 'createorder',
        name: 'Createorder',
        component: () => import('../views/front/Createorder.vue')
      },
      {
        path: 'searchorder',
        name: 'Searchorder',
        component: () => import('../views/front/Searchorder.vue')
      },
      {
        path: 'person',
        name: 'FrontPerson',
        component: () => import('../views/front/Person')
      },
      {
        path: 'password',
        name: 'FrontPassword',
        component: () => import('../views/front/Password')
      },
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 提供一个重置路由的方法
export const resetRouter = () => {
  router.matcher = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
  })
}

// 注意：刷新页面会导致页面路由重置
export const setRoutes = () => {
  const storeMenus = localStorage.getItem("menus");
  if (storeMenus) {

    // 获取当前的路由对象名称数组
    const currentRouteNames = router.getRoutes().map(v => v.name)
    if (!currentRouteNames.includes('Manage')) {
      // 拼装动态路由
      const manageRoute = { path: '/manage', name: 'Manage', component: () => import('../views/Manage.vue'), redirect: "/manage/Dashbord", children: [
          { path: 'person', name: '个人信息', component: () => import('../views/Person.vue')},
          { path: 'password', name: '修改密码', component: () => import('../views/Password.vue')}
        ] }
      const menus = JSON.parse(storeMenus)
      menus.forEach(item => {
        if (item.path) {  // 当且仅当path不为空的时候才去设置路由
          let itemMenu = { path: item.path.replace("/", ""), name: item.name, component: () => import('../views/' + item.pagePath + '.vue')}
          manageRoute.children.push(itemMenu)
        } else if(item.children.length) {
          item.children.forEach(item => {
            if (item.path) {
              let itemMenu = { path: item.path.replace("/", ""), name: item.name, component: () => import('../views/' + item.pagePath + '.vue')}
              manageRoute.children.push(itemMenu)
            }
          })
        }
      })
      // 动态添加到现在的路由对象中去
      router.addRoute(manageRoute)
    }
  }
}

// 重置我就再set一次路由
setRoutes()


router.beforeEach((to, from, next) => {
  localStorage.setItem("currentPathName", to.name)  // 设置当前的路由名称
  store.commit("setPath")

  // 未找到路由的情况
  if (!to.matched.length) {
    const storeMenus = localStorage.getItem("menus")
    if (storeMenus) {
      next("/404")
    } else {
      // 跳回登录页面
      next("/login")
    }
  }
  // 其他的情况都放行
  next()

})

export default router
