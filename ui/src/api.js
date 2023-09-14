const BASE_URL = process.env.REACT_APP_API_BASE_URL;
const CONTENT_TYPE_APPLICATION_JSON = 'application/json';
const STORAGE_ITEM_ACCESS_TOKEN = 'accessToken';
const STORAGE_ITEM_TOKEN_TYPE = 'tokenType';

const setTokenInStorage = (token) => {
  localStorage.setItem(STORAGE_ITEM_ACCESS_TOKEN, token.accessToken);
  localStorage.setItem(STORAGE_ITEM_TOKEN_TYPE, token.type);
};

export default class Api {
  constructor() {
    this.baseUrl = BASE_URL;
  }

  async login(username, password) {
    const res = await this.doCall('/login', 'POST', { username, password });
    const token = await res.json();
    setTokenInStorage(token);
    return token;
  }

  async getTest() {
    const res = await this.doCall('/test', 'GET');
    return res.json();
  }

  async getCompaniesListings() {
    const res = await this.doCall('/adminViewListing', 'GET');
    return res.json();
  }

  async logout(username) {
    await this.doCall('/auth/logout', 'POST', { username });
    localStorage.removeItem(STORAGE_ITEM_ACCESS_TOKEN);
    localStorage.removeItem(STORAGE_ITEM_TOKEN_TYPE);
  }

  async doCall(path, method, data) {
    const headers = {
      'Content-Type': CONTENT_TYPE_APPLICATION_JSON,
      Accept: CONTENT_TYPE_APPLICATION_JSON,
    };
    const accessToken = localStorage.getItem(STORAGE_ITEM_ACCESS_TOKEN);
    if (accessToken) {
      headers.Authorization = `${localStorage.getItem(STORAGE_ITEM_TOKEN_TYPE)} ${accessToken}`;
    }
    let body;
    if (data) {
      body = JSON.stringify(data);
    }
    console.log(body);
    const res = await fetch(
      `${this.baseUrl}${path}`,
      {
        method,
        headers,
        body,
        credentials: 'include',
      },
    );
    console.log(res);
    if (res.status === 401 && accessToken) {
    //   await this.refreshToken(accessToken);
      return this.doCall(path, method, data);
    }
    if (res.status > 299) {
      throw new Error(`expecting success from API for ${method} ${path} but response was status ${res.status}: ${res.statusText}`);
    }
    return res;
  }
}
