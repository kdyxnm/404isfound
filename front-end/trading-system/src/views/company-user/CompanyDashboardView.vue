<template>
  <div id="company-dashboard">
    <h1>Company Dashboard</h1>
    <el-menu mode="horizontal">
      <el-menu-item index="1" @click="switchMenu(1)">Create New Listing</el-menu-item>
      <el-menu-item index="2" @click="switchMenu(2)">Manage Current Listing</el-menu-item>
    </el-menu>

    
    <!-- <company-listings v-if="menuIndex === 1" :companyListings="marketData" :displayType="'Buy'" @buy-share="openPurchaseDialog"></company-listings> -->
    <!-- <search-component v-if="menuIndex === 2" @buy-share="openPurchaseDialog"></search-component> -->
    <add-company-listing v-if="menuIndex === 1"></add-company-listing>
    <company-listings v-if="menuIndex === 2" :companyListings="allCompanyListings" :displayType="'Delete'" @delete-listing="deleteListing"></company-listings>

    <!-- Purchase dialog -->
    <!-- <el-dialog v-model="isPurchaseDialogVisible" title="Purchase Share">
      <el-form :model="purchaseForm">
        <h4> Purchansing the listing {{ purchaseForm.listId }}</h4>
        <el-form-item label="Amount" :label-width="formLabelWidth">
          <el-input v-model="purchaseForm.amount" autocomplete="off" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="cancelPurchase">Cancel</el-button>
          <el-button type="primary" @click="confirmPurchase(purchaseForm.listId)">
            Confirm
          </el-button>
        </span>
      </template>
    </el-dialog> -->

    <!-- Sell dialog -->
    <!-- <el-dialog v-model="isSellDialogVisible" title="Sell Share">
      <el-form :model="sellForm">
        <h4> Sell the share {{ sellForm.listId }}</h4>
        <el-form-item label="Amount" :label-width="formLabelWidth">
          <el-input v-model="sellForm.amount" autocomplete="off" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="cancelPurchase">Cancel</el-button>
          <el-button type="primary" @click="confirmSell(sellForm.listId)">
            Confirm
          </el-button>
        </span>
      </template>
    </el-dialog> -->

  </div>
</template>

<script>
import AddCompanyListing from '../../components/AddCompanyListing.vue';
import CompanyListings from '../../components/CompanyListings.vue'

export default {
  components: {
    CompanyListings,
    AddCompanyListing,
  },

  data() {
    return {
      menuIndex: 1,

      allCompanyListings: []

    }
  },

  methods: {
    switchMenu(index){
      this.menuIndex = index;
    },

    deleteListing(listId) {
      console.log('Delete Listing : ' + listId);

      // Delete Logic

      // Update Data

    },
  },

  beforeMount() {
      this.allCompanyListings = [
        { id: 1, listId: '123', price: '$100', amount: '1000', companyId: '001', companyName: 'Dummy Name' },
        { id: 2, listId: '124', price: '$200', amount: '500', companyId: '002', companyName: 'Dummy Name' },
        { id: 3, listId: '125', price: '$300', amount: '300', companyId: '003', companyName: 'Dummy Name' },
        { id: 4, listId: '126', price: '$400', amount: '200', companyId: '004', companyName: 'Dummy Name' },
      ]
    },
};
</script>

<style lang="scss">


</style>
