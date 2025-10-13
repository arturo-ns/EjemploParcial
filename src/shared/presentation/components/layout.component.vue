<script setup>

import LanguageSwitcherComponent from "./language-switcher.component.vue";
import {useI18n} from "vue-i18n";
import FooterContentComponent from "./footer-content.component.vue";
const { t } = useI18n();

const items = [
  {label: 'option.home', to: '/home'},
  {label: 'option.button1', to: '/boton1'}
];
</script>

<template>
  <pv-toast/>
  <pv-confirm-dialog/>
  <div class="header">
    <pv-toolbar class="bg-primary">
      <template #start>
        <img :src="'https://logo.clearbit.com/apple.com'" alt="brand logo" width="24" height="24" class="mr-2"/>
        <h3>{{ t('brand.name') }}</h3>
      </template>
      <template #center>

      </template>
      <template #end>
        <div class="toolbar-end">
          <div class="nav-buttons mr-3">
            <pv-button v-for="item in items" :key="item.label" as-child v-slot="slotProps">
              <router-link :to="item.to" :class="slotProps['class']">{{ t(item.label) }}</router-link>
            </pv-button>
          </div>
          <language-switcher-component/>
        </div>
      </template>
    </pv-toolbar>
  </div>
  <div class="main-content">
    <router-view/>
  </div>
  <div class="footer">
    <footer-content-component/>
  </div>
</template>

<style scoped>
.header {
  position: absolute;
  left: 0;
  top:0;
  width:100%;
}

.main-content {
  margin-top: 60px;
}

.footer {
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  padding: 10px;
}

.toolbar-end {
  display: flex;
  align-items: center;
  gap: 12px;
}

.nav-buttons {
  display: flex;
  align-items: center;
  gap: 8px;
}
</style>