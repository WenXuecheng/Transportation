import Vue from "vue";
import VueI18n from "vue-i18n";

//element 国际化处理
import locale from "element-ui/lib/locale";
import elRu from "element-ui/lib/locale/lang/ru-RU";
import elCn from "element-ui/lib/locale/lang/zh-CN";

Vue.use(VueI18n);

//将刚刚locales创建的文件分别引入并输出成i18n需要的格式
function loadLocaleMessages() {
    //检测locales文件夹中有多少个语系
    const locales = require.context(
        "../locales",
        true,
        /.json$/
    );
    const messages = {};
    locales.keys().forEach(file => {
        const keyArr = file.split('/');
        keyArr.shift();
        messages[keyArr.join('.').replace(/\.json$/g, '')] = locales(file);
    });
    return {
        cn: { ...messages.cn, ...elCn },
        ru: { ...messages.ru, ...elRu },
    };
}
function getLanguage() {
    // 第一次进入页面或手动清除设置默认语言
    localStorage.getItem('lang') ? null : localStorage.setItem('lang', "ru")
    let locale = localStorage.getItem('lang')
    return locale
}
const i18n = new VueI18n({
    locale:  getLanguage() ,
    messages: loadLocaleMessages()
});
locale.i18n((key, value) => i18n.t(key, value));

export default i18n;
