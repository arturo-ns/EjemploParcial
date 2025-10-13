/**
 * BaseEndpoint class to handle common API endpoint operations.
 * @class
 * @example
 * const api = new BaseApi();
 * const usersEndpoint = new BaseEndpoint(api, '/users');
 * usersEndpoint.getAll().then(response => console.log(response.data));
 */
export class BaseEndpoint {
    constructor(baseApi, endpointPath) {
        this.http = baseApi.http;
        this.endpointPath = endpointPath;
    }

    getAll() {
        return this.http.get(this.endpointPath);
    }

    // Obtener un recurso por id
    getById(id) {
        return this.http.get(`${this.endpointPath}/${id}`);
    }

    // Crea un nuevo recurso con los datos que le pasemos (resource)
    create(resource) {
        return this.http.post(this.endpointPath, resource);
    }

    // Actualiza un recurso completo por ID con los nuevos datos que le pasamos (resource)
    update(id, resource) {
        return this.http.put(`${this.endpointPath}/${id}`, resource);
    }

    delete(id) {
        return this.http.delete(`${this.endpointPath}/${id}`);
    }
}