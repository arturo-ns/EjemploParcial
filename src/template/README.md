Módulo reutilizable inspirado en el contexto `publishing`.

Estructura:
- application/ — estado con Pinia (stores)
- domain/ — entidades y lógica de dominio
- infrastructure/ — API client y assemblers
- presentation/ — rutas y vistas (lazy-loaded)

Alias: importa con `@template`.

Integración en otro proyecto (pasos):
1) Vite alias en `vite.config.js`:
   ```js
   import { fileURLToPath, URL } from 'node:url'
   export default defineConfig({
     resolve: { alias: {
       '@': fileURLToPath(new URL('./src', import.meta.url)),
       '@template': fileURLToPath(new URL('./src/template', import.meta.url))
     }}
   })
   ```
2) Rutas en `src/router.js`:
   ```js
   import templateRoutes from '@template/presentation/template-routes.js'
   const routes = [
     { path: '/template', name: 'template', children: templateRoutes }
   ]
   ```
3) Variables de entorno en `.env`:
   - `VITE_LEARNING_PLATFORM_API_URL=https://tu-api`
   - `VITE_TEMPLATE_ITEMS_ENDPOINT_PATH=/items` (opcional)

4) Backend/Mock:
   - Asegura que el recurso `items` exista en tu API (GET/POST/PUT/DELETE).

5) UI libs (opcional):
   - Las vistas usan componentes `pv-*` (PrimeVue). Ajusta o reemplaza según tu stack.

Qué personalizar (comentarios en código):
- `@template/infrastructure/template-api.js`: cambia paths o variables de entorno.
- `@template/infrastructure/item.assembler.js`: mapea campos de API -> entidad.
- `@template/domain/model/item.entity.js`: define tu entidad real.
- `@template/application/template.store.js`: valida/transforma y reglas de negocio.
- `@template/presentation/*`: columnas, labels/i18n, formularios y rutas.

Rutas de ejemplo:
- `/template/items`
- `/template/items/new`
- `/template/items/:id/edit`

---

Guía de adaptación (Checklist para tu proyecto)

1) Variables de entorno (API y endpoints)
- Define `VITE_LEARNING_PLATFORM_API_URL` apuntando a tu backend.
- (Opcional) `VITE_TEMPLATE_ITEMS_ENDPOINT_PATH` si tu recurso no es `/items`.
- (Local/mock) Puedes usar `VITE_TEMPLATE_API_BASE_URL`; por defecto el código hace fallback a `http://localhost:4000/api/v1` si no hay baseURL.

2) Renombrar identificadores genéricos
- Módulo: si duplicas este módulo, cambia el alias/nombre `template` por tu contexto.
  - `@template/*` (rutas, imports y alias en `vite.config.js`).
  - ID del store: en `@template/application/template.store.js` cambia `defineStore('template', ...)`.
- Entidad: renombra `Item` por tu entidad real.
  - Archivo `@template/domain/model/item.entity.js` y todas las referencias (imports).
- API: renombra `TemplateApi` si corresponde a tu contexto.
  - Archivo `@template/infrastructure/template-api.js` y sus imports en store.
- Rutas y nombres de ruta
  - En `@template/presentation/template-routes.js`: cambia paths (`items`) y names (`template-items`, `template-item-new`, `template-item-edit`).

3) Mapeo de datos (Assembler)
- Archivo: `@template/infrastructure/item.assembler.js`.
- Ajusta `toEntityFromResource` para mapear los campos de tu API a la entidad (por ejemplo, `name/description` ó `title/summary`).
- Si tu API envuelve datos, ajusta `toEntitiesFromResponse`.

4) Store (reglas y validaciones)
- Archivo: `@template/application/template.store.js`.
- Revisa validaciones y transformaciones en `addItem`, `updateItem`, `deleteItem`.
- Ajusta `fetchItems` (paginación, filtros, errores) según tu API.

5) Presentación (vistas y columnas)
- Lista: `@template/presentation/views/item-list.component.vue`.
  - Cambia columnas, labels y paginación para tu entidad.
  - Mantén u oculta acciones según si deseas CRUD o solo lectura.
- Formulario: `@template/presentation/views/item-form.component.vue`.
  - Cambia modelo, validaciones y campos.
- Rutas: `@template/presentation/template-routes.js`.
  - Ajusta títulos (`meta.title`) y lazy-load según tus vistas.

6) Endpoints (TemplateApi)
- Archivo: `@template/infrastructure/template-api.js`.
- Cambia `VITE_TEMPLATE_ITEMS_ENDPOINT_PATH` o fija el path correcto para tu backend.
- Verifica la baseURL: viene de `VITE_LEARNING_PLATFORM_API_URL`. En local, el fallback usa `http://localhost:4000/api/v1`.

7) Prueba rápida con json-server (mock)
- Levanta json-server en `server/`:
  - `json-server --watch db.json --routes routes.json`
- Asegúrate que `GET http://localhost:4000/api/v1/items` responde.
- Abre la app y navega a `/template/items` (o las vistas donde integres el listado) para confirmar.

8) Búsquedas recomendadas (refactors)
- Busca y reemplaza en el código:
  - `Item`, `items` → tu entidad y colección.
  - `TemplateApi` → tu API de contexto.
  - `template` (ID de store, nombres de ruta, alias) → tu namespace.
  - Campos de UI (`Name`, `Description`) → labels/keys de tu dominio (idealmente con i18n).

Notas
- Mantén la estructura DDD (application/domain/infrastructure/presentation) para facilitar la reutilización.
- Adapta i18n si tu proyecto usa traducciones para labels/títulos.