<script setup>
  import {useI18n} from "vue-i18n";
  const { t } = useI18n();
  const year = new Date().getFullYear();
  const company = 'Proyecto Peru, inc';
  const name = 'Andy Arturo Nuñez Soto';
  const code = 'U20231E795';
</script>

<template>
  <div class="grid bg-primary mt-4 p-2 align-content-start">
    <div class="col-12 ml-3 align-items-center justify-content-center">
      <p>{{ t('footer.copyright', { year, company }) }}</p>
    </div>
    <div  class="col-12 ml-3 mt-1 align-items-center justify-content-center">
      <p>{{ t('footer.developed-by', { name, code }) }}</p>
    </div>
  </div>
</template>

<style scoped>

</style>