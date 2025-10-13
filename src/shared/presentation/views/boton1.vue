<script setup>
import {useI18n} from "vue-i18n";
import { ref } from "vue";
import useTemplateStore from "@template/application/template.store.js";
import ItemListComponent from "@template/presentation/views/item-list.component.vue";
const { t } = useI18n();

const store = useTemplateStore();
const model = ref({ name: '', description: '' });
const error = ref('');
const showForm = ref(false);

const cancel = () => {
  error.value = '';
  model.value = { name: '', description: '' };
  showForm.value = false;
};

const save = () => {
  error.value = '';
  if (!model.value.name) {
    error.value = 'Name is required';
    return;
  }
  store.addItem({ ...model.value });
  model.value = { name: '', description: '' };
};
</script>

<template>
  <div class="align-content-start justify-content-start m-4">
    <h1>{{ t('option.button1') }}</h1>
  </div>
  <div class="m-4">
    <div class="add-item-toolbar">
      <pv-button v-if="!showForm" label="Add Item" icon="pi pi-plus" @click="showForm = true" />
    </div>
    <div v-if="showForm" class="add-item-form">
      <pv-input-text v-model="model.name" placeholder="Name" />
      <pv-input-text v-model="model.description" placeholder="Description" />
      <div class="form-actions">
        <pv-button label="Save" icon="pi pi-save" @click="save" />
        <pv-button label="Cancel" severity="secondary" @click="cancel" />
      </div>
      <div v-if="error" class="text-red-500">{{ error }}</div>
    </div>
    <item-list-component/>
  </div>
  
</template>

<style scoped>
.add-item-toolbar {
  margin-bottom: 12px;
}

.add-item-form {
  display: flex;
  flex-direction: column;
  gap: 12px;
  max-width: 30rem;
  margin-bottom: 12px;
}

.form-actions {
  display: flex;
  gap: 8px;
}
</style>


