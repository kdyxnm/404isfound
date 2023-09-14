import axios from "axios";
// import { Message } from 'element-ui';
import router from '@/router';

const service = axios.create({
  timeout:10000,
  headers:{
    'Content-Type' : 'application/json',
  },
  withCredentials : true,
  crossDomain: true
})


service.interceptors.response.use(
  response => {
    // check the status

    // preprocess the data

    return response;
  },
  error => {
    console.error('Error:', error);

    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000,
    });

    if (error.response && error.response.status === 401) {
      router.push('/')
    }

    return Promise.reject(error);
  },
);

export default service;