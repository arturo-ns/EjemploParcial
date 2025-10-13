import { Item } from '@template/domain/model/item.entity.js'

export const ItemAssembler = {
    // CUSTOMIZA: Mapea los campos del recurso API a tu entidad de dominio
    toEntityFromResource(resource) {
        return new Item({
            id: resource.id,
            // Soporta esquemas tipo 'tutorials' (title/summary) o 'items' (name/description)
            name: resource.name ?? resource.title ?? '',
            description: resource.description ?? resource.summary ?? ''
        })
    },

    // CUSTOMIZA: Si tu API envuelve datos en otra propiedad, ajusta aquí
    toEntitiesFromResponse(response) {
        const resources = Array.isArray(response?.data) ? response.data : []
        return resources.map(resource => this.toEntityFromResource(resource))
    }
}

