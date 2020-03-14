import axios from 'axios';
import { getApplication } from './EnvContext';

const ajax = axios.create({
});

axios.interceptors.request.use((config) => {
    config.headers['x-requested-with'] = 'XMLHttpRequest'
    return config;
});

ajax.interceptors.response.use((response)=>{
    return response;
},
(error)=>{
    if (error.response.status == 403) {
        (()=>getApplication().setUser(null))();
    }

    return Promise.reject(error);
});

export default ajax;
