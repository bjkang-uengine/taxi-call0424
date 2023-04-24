
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import CallManager from "./components/listers/CallCards"
import CallDetail from "./components/listers/CallDetail"

import TaxiCallManager from "./components/listers/TaxiCallCards"
import TaxiCallDetail from "./components/listers/TaxiCallDetail"
import CommentManager from "./components/listers/CommentCards"
import CommentDetail from "./components/listers/CommentDetail"

import PointManager from "./components/listers/PointCards"
import PointDetail from "./components/listers/PointDetail"



export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/calls',
                name: 'CallManager',
                component: CallManager
            },
            {
                path: '/calls/:id',
                name: 'CallDetail',
                component: CallDetail
            },

            {
                path: '/taxiCalls',
                name: 'TaxiCallManager',
                component: TaxiCallManager
            },
            {
                path: '/taxiCalls/:id',
                name: 'TaxiCallDetail',
                component: TaxiCallDetail
            },
            {
                path: '/comments',
                name: 'CommentManager',
                component: CommentManager
            },
            {
                path: '/comments/:id',
                name: 'CommentDetail',
                component: CommentDetail
            },

            {
                path: '/points',
                name: 'PointManager',
                component: PointManager
            },
            {
                path: '/points/:id',
                name: 'PointDetail',
                component: PointDetail
            },




    ]
})
