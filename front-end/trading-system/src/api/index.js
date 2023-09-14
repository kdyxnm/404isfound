import axios from '../utils/request'
import path from './path'


const api = {

  login(username, pwd, type) {
    var userinfo = {
      'username':username,  
      'password': pwd,
      'roletype': type
    }
    return axios.post(path.baseUrlLoc + path.login, userinfo)
  },


  getAllCompanyListing() {
    var param = {
      'roletype': 'Admin'
    }
    return axios.get(path.baseUrlLoc + path.viewAllCompnayListing, param)
  },

  getAllCompanyListing() {
    var param = {
      'roletype': 'Company',
      'companyid' : id
    }
    return axios.get(path.baseUrlLoc + path.viewAllCompnayListing, param)
  },

  getAllCustomers() {
    return axios.get(path.baseUrlLoc + path.viewAllCustomer)
  },

  


  
}
export default api
