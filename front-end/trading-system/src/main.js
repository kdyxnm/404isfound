import { createApp } from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import elementIcon from './plugins/icons'
import ElementPlus from 'element-plus';
import axios from 'axios';
// import 'element-plus/packages/theme-chalk/src/index.scss';

const app = createApp(App)
app.config.globalProperties.$axios = axios
app.use(elementIcon).use(ElementPlus).use(store).use(router)
app.mount('#app')
