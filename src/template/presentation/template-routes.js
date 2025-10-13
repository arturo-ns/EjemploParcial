// CUSTOMIZA: Agrega/quita rutas del módulo según tu caso
// - Ajusta paths (pluralización), names y meta.title a tu dominio
const itemList = () => import('@template/presentation/views/item-list.component.vue')
const itemForm = () => import('@template/presentation/views/item-form.component.vue')

const templateRoutes = [
  { path: 'items', name: 'template-items', component: itemList, meta: { title: 'Items' } },
  { path: 'items/new', name: 'template-item-new', component: itemForm, meta: { title: 'New Item' } },
  { path: 'items/:id/edit', name: 'template-item-edit', component: itemForm, meta: { title: 'Edit Item' } }
]

export default templateRoutes

