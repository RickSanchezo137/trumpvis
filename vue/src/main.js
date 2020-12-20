import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
// 引入字体文件
import './assets/font/iconfont.css'
// 引入全局格式
import './assets/css/global.less'
import axios from 'axios'

// 请求基准路径配置
axios.defaults.baseURL = '/api'
// 挂载
Vue.prototype.$http = axios

// 挂载
Vue.prototype.$echarts = window.echarts

Vue.config.productionTip = false

Vue.prototype.chartInstance = null

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
