import Vue from 'vue'
import Index from './index.vue'
import { checkLogin } from './model/user'

import './styles.scss'

checkLogin().then(d => {
    new Vue({
        el: '#app',
        render: h => h(Index)
    })
})