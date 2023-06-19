import axios from 'axios'
import {serverIp,serverPort,http} from "../../public/config";
import {Message} from "element-ui";
const request = axios.create({
    baseURL: `${http}://${serverIp}:${serverPort}`,
    timeout: 30000
})

// request 拦截器
// 可以自请求发送前对请求做一些处理
// 比如统一加token，对请求参数统一加密
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    let user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : null
    if (user) {
        config.headers['token'] =  user.token;  // 设置请求头
    }
    return config
}, error => {
    return Promise.reject(error)
});

// response 拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
        let res = response.data;
        // 如果是返回的文件
        if (response.config.responseType === 'blob') {
            return res
        }
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        // 当权限验证不通过的时候给出提示
        if (res.code === '401') {
            if (res.msg === "无token，请重新登录"){
                Message({
                    message: "Нет токена, пожалуйста, войдите снова",
                    type: 'error'
                })
                //store.logout()
            }
            if (res.msg === "token验证失败，请重新登录"){
                Message({
                    message: "Верификация не прошла, пожалуйста, войдите снова",
                    type: 'error'
                })
                //store.logout()
            }
            if (res.msg === "用户不存在，请重新登录"){
                Message({
                    message: "Пользователь не существует, пожалуйста, войдите снова",
                    type: 'error'
                })
                //store.logout()
            }

        }
        return res;
    },
    error => {
        return Promise.reject(error)
    }
)


export default request

