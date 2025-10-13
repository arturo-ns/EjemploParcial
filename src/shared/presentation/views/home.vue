<script setup>
import {useI18n} from "vue-i18n";
import ItemListComponent from "@template/presentation/views/item-list.component.vue";
const { t } = useI18n();

</script>

<template>
  <div class="align-content-start justify-content-start m-4">
    <h1>{{  t('home.title') }}</h1>
    <p>{{ t('home.content')}}</p>
  </div>
  <div class="m-4">
    <item-list-component/>
  </div>
</template>

<style scoped>

</style>