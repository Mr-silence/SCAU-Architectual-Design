// router/index.js

import { createRouter, createWebHistory } from 'vue-router';
import Login from '../Login.vue';
import Dashboard from '../test.vue';

const routes = [
    {
        path: '/',
        redirect: '/login',
    },
    {
        path: '/login',
        component: Login,
    },
    {
        path: '/dashboard',
        component: Dashboard,
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
