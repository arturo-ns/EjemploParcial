import { BaseApi } from '@/shared/infrastructure/base-api.js'
import { BaseEndpoint } from '@/shared/infrastructure/base-endpoint.js'

// CUSTOMIZE: Cambia el path o la variable de entorno para apuntar a tu recurso.
// Si no configuras VITE_TEMPLATE_ITEMS_ENDPOINT_PATH, por defecto usará '/items'.
const itemsEndpointPath = import.meta.env.VITE_TEMPLATE_ITEMS_ENDPOINT_PATH || '/items'

/**
 * API genérica para el módulo Template.
 * - Base URL se toma de VITE_LEARNING_PLATFORM_API_URL (ver shared/BaseApi).
 * - Cambia itemsEndpointPath arriba si tu backend usa otro path/versión.
 */
export class TemplateApi extends BaseApi {
    #itemsEndpoint

    constructor() {
        super()
        // Fallback baseURL for json-server if shared env not set
        if (!this.http?.defaults?.baseURL) {
            this.http.defaults.baseURL = import.meta.env.VITE_TEMPLATE_API_BASE_URL || 'http://localhost:4000/api/v1'
        }
        this.#itemsEndpoint = new BaseEndpoint(this, itemsEndpointPath)
    }

    getItems() { return this.#itemsEndpoint.getAll() }
    getItemById(id) { return this.#itemsEndpoint.getById(id) }
    createItem(resource) { return this.#itemsEndpoint.create(resource) }
    updateItem(resource) { return this.#itemsEndpoint.update(resource.id, resource) }
    deleteItem(id) { return this.#itemsEndpoint.delete(id) }
    // CUSTOMIZA: Si necesitas más recursos, crea más endpoints (e.g. /categories)
    // y métodos: getCategories(), createCategory(), etc. siguiendo este patrón.
}

