import apiRequest from './ApiRequest';

const ApiFactory = {
    get: (url, params) => apiRequest('GET', url, params),
    post: (url, data) => apiRequest('POST', url, {}, data),
    put: (url, data) => apiRequest('PUT', url, {}, data),
    delete: (url, params) => apiRequest('DELETE', url, params),
}

export default ApiFactory;