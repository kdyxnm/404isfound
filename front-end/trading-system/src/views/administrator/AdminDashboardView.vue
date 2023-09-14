<!-- src/views/AdminDashboard.vue -->
<template>
  <div>
    <h1>Administrator Dashboard</h1>
    <el-menu mode="horizontal">
      <el-menu-item index="1" @click="switchMenu(1)">All Customers</el-menu-item>
      <el-menu-item index="2" @click="switchMenu(2)">All Company Listings</el-menu-item>
      <el-menu-item index="3" v-if="menuIndex === 3">Customer Portfolio</el-menu-item>
    </el-menu>
    <h2  v-if="menuIndex === 1">All Customers</h2>
    <h2  v-if="menuIndex === 2">All Company Listings</h2>
    <h2  v-if="menuIndex === 3">Cutomer Portfolio Details</h2>

    <AllCustomers v-if="menuIndex === 1" :customers="customers" @view-portfolio="viewPortfolio"></AllCustomers>
    <CompanyListings v-if="menuIndex === 2" :companyListings="companyListings"></CompanyListings>
    <portfolio-component v-if="menuIndex === 3" :userId="this.currentViewingCid"></portfolio-component>
  </div>
</template>

<script>
import AllCustomers  from '../../components/AdminAllCustomers.vue';
import CompanyListings from '../../components/CompanyListings.vue';
import api from '../../api'
import PortfolioComponent from '../../components/PortfolioComponent.vue';

export default {
  components: {
    AllCustomers,
    CompanyListings,
    PortfolioComponent,
  },
  data() {
    return {
      menuIndex : 1,
      customers: [
        { id: 1, username: 'john_doe' },
        { id: 2, username: 'jane_doe' },
      ],
      companyListings: [
        { id: 1, listId: '123', price: '$100', amount: '1000', companyId: '001', companyName: 'ABC Corp' },
        { id: 2, listId: '124', price: '$200', amount: '500', companyId: '002', companyName: 'XYZ Ltd' },
      ],

      currentViewingCid : null,
    };
  },
  methods: {
    switchMenu(index) {
      this.menuIndex = index;
    },
    viewPortfolio(customerId) {
      console.log('View portfolio of customer with ID:', customerId);
      this.currentViewingCid = customerId;
      this.switchMenu(3);
      // Here you can add the logic to view the portfolio of the selected customer
    },
  },

  beforeMount() {
    var that = this
    // api request

  },
};
</script>
