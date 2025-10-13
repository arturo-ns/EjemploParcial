<script setup>
import { onMounted } from 'vue'
import useTemplateStore from '@template/application/template.store.js'

const store = useTemplateStore()
const { items, loaded, errors, fetchItems } = store

// CUSTOMIZA: Cambia cabeceras y columnas segun tu entidad/UX
// - Si tu entidad no tiene 'name/description', ajusta columnas
// - Integra i18n para headers si tu proyecto usa traducciones
onMounted(() => {
  if (!loaded) fetchItems()
})
</script>

<template>
  <div class="p-4">
    <h1>Items</h1>
    <!-- CUSTOMIZA: Textos/labels pueden venir de i18n -->
    <pv-data-table
      :loading="!loaded"
      :rows="5"
      :rows-per-page-options="[5, 10, 20]"
      :value="items"
      paginator
      striped-rows
      table-style="min-width: 50rem"
    >
      <pv-column header="ID" field="id" sortable />
      <pv-column header="Name" field="name" sortable />
      <pv-column header="Description" field="description" />
      <!-- CUSTOMIZA: Agrega/quita columnas -->
    </pv-data-table>
    <div v-if="errors.length" class="text-red-500 mt-3">
      Errors: {{ errors.map(e => e.message).join(', ') }}
    </div>
  </div>
  
</template>

<style scoped>

</style>

