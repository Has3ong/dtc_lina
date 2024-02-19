
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import 보험보험Manager from "./components/listers/보험보험Cards"
import 보험보험Detail from "./components/listers/보험보험Detail"

import 회원회원Manager from "./components/listers/회원회원Cards"
import 회원회원Detail from "./components/listers/회원회원Detail"

import 심사심사Manager from "./components/listers/심사심사Cards"
import 심사심사Detail from "./components/listers/심사심사Detail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/보험/보험',
                name: '보험보험Manager',
                component: 보험보험Manager
            },
            {
                path: '/보험/보험/:id',
                name: '보험보험Detail',
                component: 보험보험Detail
            },

            {
                path: '/회원/회원',
                name: '회원회원Manager',
                component: 회원회원Manager
            },
            {
                path: '/회원/회원/:id',
                name: '회원회원Detail',
                component: 회원회원Detail
            },

            {
                path: '/심사/심사',
                name: '심사심사Manager',
                component: 심사심사Manager
            },
            {
                path: '/심사/심사/:id',
                name: '심사심사Detail',
                component: 심사심사Detail
            },



    ]
})
