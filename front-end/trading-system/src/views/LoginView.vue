<template>

  <div class="content-container">
    <el-container>
      <el-header class="title">
        <h1>404NotFound Trading System</h1>
        <h2>Login</h2>
      </el-header>
      <el-main>
        <el-form ref="form" :model="form" label-width="120px">
          <el-form-item label="User Type">
            <el-select v-model="form.userType" placeholder="Select user type">
              <el-option label="Administrator" value="administrator"/>
              <el-option label="Company User" value="company_user"/>
              <el-option label="Customer" value="customer"/>
            </el-select>
          </el-form-item>
          <el-form-item label="Username">
            <el-input v-model="form.username" aria-placeholder="user name"></el-input>
          </el-form-item>
          <el-form-item label="Password">
            <el-input type="password" v-model="form.password" aria-placeholder="password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="handleSubmit">Login</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>

</template>

<script>
import router from '../router'

export default {
  data() {
    return {
      form: {
        userType: '',
        username: '',
        password: '',
      },
    };
  },
  methods: {
    handleSubmit() {
      var that = this;
      if (this.form.userType && this.form.username && this.form.password) {
        console.log('Form submitted', this.form);


        if (this.form.userType == 'company_user') {
          this.$router.push({ path: '/company-dashboard/' + 'response-id' + '/' + 'response-usrname' })
        } else if (this.form.userType == 'administrator') {
          this.$router.push({ path: '/admin-dashboard' })
        } else {
          this.$router.push({ path: '/customer-dashboard/' + 'response-id' + '/' + 'response-usrname' })
        }
        
      } else {
        console.error('All fields are required');
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.title{
  margin-bottom: 10%;
}
</style>
