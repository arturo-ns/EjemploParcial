import {createRouter, createWebHistory} from "vue-router";
import Home from "./shared/presentation/views/home.vue";
import templateRoutes from '@template/presentation/template-routes.js'

const boton1 = () => import('./shared/presentation/views/boton1.vue');
const pageNotFound = () => import('./shared/presentation/views/page-not-found.vue');
const routes = [
    { path: '/home',            name: 'home',       component: Home,        meta: { title: 'Home' } },
    { path: '/boton1',          name: 'boton1',     component: boton1,      meta: { title: 'Button1' } },
    { path: '/template',        name: 'template',   children: templateRoutes },
    { path: '/',                redirect: '/home' },
    { path: '/:pathMatch(.*)*', name: 'not-found', component: pageNotFound, meta: { title: 'Page Not Found' } }
];

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: routes,

});

router.beforeEach((to, from, next) => {
    console.log(`Navigating from ${from.name} to ${to.name}`);
    // Set the page title
    let baseTitle = 'Proyecto';
    document.title = `${baseTitle} - ${to.meta['title']}`;
    // TODO: Call authentication guard
    next();
});

export default router;