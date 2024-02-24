// 生产
export const serverIp = '34.88.124.218'
//测试
//export const serverIp = 'localhost'
export const serverPort = '9090'
export const http = 'http'
//export const http = 'https


//验证字符串
//验证账号密码
//英文字母数字俄语字母/*-+.@_
export const ValidStringPattern = /^[0-9a-zA-Z\u4e00-\u9fa5\u0400-\u04FF*@.\/\-+_]+$/
//验证手机号
//仅数字
export const ValidPhonePattern = /^[0-9]+$/
//验证昵称
//英文字母数字俄语字母
export const ValidNicknamePattern = /^[0-9a-zA-Z\u4e00-\u9fa5\u0400-\u04FF]+$/