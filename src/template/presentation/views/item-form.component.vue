<script setup>
import { useRoute, useRouter } from 'vue-router'
import { ref, watchEffect } from 'vue'
import useTemplateStore from '@template/application/template.store.js'

const route = useRoute()
const router = useRouter()
const store = useTemplateStore()
const { getItemById, addItem, updateItem } = store

// CUSTOMIZA: Ajusta el modelo y validaciones según tu entidad
const isEdit = ref(Boolean(route.params.id))
const model = ref({ id: undefined, name: '', description: '' })

watchEffect(() => {
  if (isEdit.value) {
    const found = getItemById(route.params.id)
    if (found) model.value = { ...found }
  }
})

// CUSTOMIZA: Validación y manejo de errores al guardar
const save = () => {
  if (isEdit.value) updateItem(model.value)
  else addItem(model.value)
  router.back()
}
</script>

<template>
  <div class="p-4">
    <h1>{{ isEdit ? 'Edit Item' : 'New Item' }}</h1>
    <div class="flex flex-column gap-3 max-w-30rem">
      <pv-input-text v-model="model.name" placeholder="Name" />
      <pv-input-text v-model="model.description" placeholder="Description" />
      <div class="flex gap-2">
        <pv-button label="Save" icon="pi pi-save" @click="save" />
        <pv-button label="Cancel" severity="secondary" @click="router.back()" />
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>

