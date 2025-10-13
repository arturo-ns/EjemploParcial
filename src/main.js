import { createApp } from 'vue'
import './style.css'
import App from './app.vue'
import i18n from "./i18n.js";
import Material from '@primeuix/themes/material';
import PrimeVue from 'primevue/config';

import {
    Button,
    Column,
    ConfirmDialog,
    DataTable,
    InputText,
    SelectButton,
    Toast,
    Toolbar
} from "primevue";

import router from "./router.js";
import pinia from "./pinia.js";

createApp(App)
    .use(i18n)
    .use(PrimeVue, {theme: { preset: Material}, ripple: true})
    .component('pv-button',         Button)
    .component('pv-column',         Column)
    .component('pv-confirm-dialog', ConfirmDialog)
    .component('pv-data-table',     DataTable)
    .component('pv-select-button',  SelectButton)
    .component('pv-input-text',     InputText)
    .component('pv-toolbar',        Toolbar)
    .component('pv-toast',          Toast)

    .use(router)
    .use(pinia)
    .mount('#app')
