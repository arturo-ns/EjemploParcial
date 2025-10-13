import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import { TemplateApi } from '@template/infrastructure/template-api.js'
import { ItemAssembler } from '@template/infrastructure/item.assembler.js'
import { Item } from '@template/domain/model/item.entity.js'

const templateApi = new TemplateApi()

// CUSTOMIZA: Cambia el ID del store si renombras el módulo
const useTemplateStore = defineStore('template', () => {
    const items = ref([])
    const loaded = ref(false)
    const errors = ref([])

    const count = computed(() => (loaded.value ? items.value.length : 0))

    // CUSTOMIZA: Ajusta la carga inicial (paginación, filtros, cache, i18n de errores)
    function fetchItems() {
        templateApi.getItems()
            .then(response => {
                items.value = ItemAssembler.toEntitiesFromResponse(response)
                loaded.value = true
            })
            .catch(error => errors.value.push(error))
    }

    // CUSTOMIZA: Valida/transforma 'item' antes de enviar (requeridos, normalización)
    function addItem(item) {
        templateApi.createItem(item)
            .then(response => {
                const entity = ItemAssembler.toEntityFromResource(response.data)
                items.value.push(entity)
            })
            .catch(error => errors.value.push(error))
    }

    // CUSTOMIZA: Ajusta el mapeo de IDs/atributos según tu backend (UUID/num)
    function updateItem(item) {
        templateApi.updateItem(item)
            .then(response => {
                const entity = ItemAssembler.toEntityFromResource(response.data)
                const index = items.value.findIndex(i => i.id === entity.id)
                if (index !== -1) items.value[index] = entity
            })
            .catch(error => errors.value.push(error))
    }

    // CUSTOMIZA: Cambia a soft-delete, confirmaciones, auditoría, side-effects
    function deleteItem(item) {
        templateApi.deleteItem(item.id)
            .then(() => {
                const index = items.value.findIndex(i => i.id === item.id)
                if (index !== -1) items.value.splice(index, 1)
            })
            .catch(error => errors.value.push(error))
    }

    function getItemById(id) {
        const idNum = parseInt(id)
        return items.value.find(i => i.id === idNum)
    }

    return {
        items,
        loaded,
        errors,
        count,
        fetchItems,
        addItem,
        updateItem,
        deleteItem,
        getItemById
    }
})

export default useTemplateStore

