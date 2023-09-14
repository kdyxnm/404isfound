import { createStore } from 'vuex'

export default createStore({
  state: {
    UserInfo: {
      userType: '',
      username: '',
      userId: null,
    },
    authenticationStatus: '',
    allCompanyListings: [],
  },
  getters: {
    getUserInfo: (state) => state.UserInfo,
    getAuthenticationStatus: (state) => state.authenticationStatus,
    getAllCompanyListings: (state) => state.allCompanyListings,
  },
  mutations: {
    SET_USER_INFO(state, userInfo) {
      state.UserInfo = userInfo;
    },
    SET_AUTHENTICATION_STATUS(state, status) {
      state.authenticationStatus = status;
    },
    SET_ALL_COMPANY_LISTINGS(state, listings) {
      state.allCompanyListings = listings;
    },
  },
  actions: {
    updateUserInfo({ commit }, userInfo) {
      commit('SET_USER_INFO', userInfo);
    },
    updateAuthenticationStatus({ commit }, status) {
      commit('SET_AUTHENTICATION_STATUS', status);
    },
    updateAllCompanyListings({ commit }, listings) {
      commit('SET_ALL_COMPANY_LISTINGS', listings);
    },
  },
  modules: {
    // Here you can add your Vuex modules
  },
})
