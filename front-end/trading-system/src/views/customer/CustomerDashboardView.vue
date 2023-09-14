<template>
  <div id="customer-dashboard">
    <h1>Customer Dashboard</h1>
    <el-menu mode="horizontal">
      <el-menu-item index="1" @click="switchMenu(1)">Market</el-menu-item>
      <el-menu-item index="2" @click="switchMenu(2)">Search</el-menu-item>
      <el-menu-item index="3" @click="switchMenu(3)">Portfolio</el-menu-item>
    </el-menu>

    <h2  v-if="menuIndex === 1">Market Place</h2>
    
    <company-listings v-if="menuIndex === 1" :companyListings="marketData" :displayType="'Buy'" @buy-share="openPurchaseDialog"></company-listings>
    <search-component v-if="menuIndex === 2" @buy-share="openPurchaseDialog"></search-component>
    <portfolio-component v-if="menuIndex === 3" :userId="this.userInfo.userId" @sell-share="openSellDialog"></portfolio-component>


    <!-- Purchase dialog -->
    <el-dialog v-model="isPurchaseDialogVisible" title="Purchase Share">
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
    </el-dialog>

    <!-- Sell dialog -->
    <el-dialog v-model="isSellDialogVisible" title="Sell Share">
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
    </el-dialog>

  </div>
</template>

<script>
import SearchComponent from '../../components/SearchComponent.vue';
import PortfolioComponent from '../../components/PortfolioComponent.vue';
import CompanyListings from '../../components/CompanyListings.vue'

export default {
  components: {
    SearchComponent,
    PortfolioComponent,
    CompanyListings,
  },
  data() {
    return {
      menuIndex: 1,
      marketData: [],
      isPurchaseDialogVisible: false,
      isSellDialogVisible: false,
      purchaseForm: {
        listId: '',
        amount: '',
      },
      sellForm: {
        listId: '',
        amount: '',
      },
      formLabelWidth: '180px',

      userInfo : {
        username: null,
        userId: null,
      }
    };
  },

  methods: {
    switchMenu(index){
      this.menuIndex = index;
    },

    getUserInfo() {
      this.userInfo.username = "sample"
      this.userInfo.userId = 1
    },

    buyShare(listId) {
      console.log("Buy share " + listId)
      console.log("Amount : " + this.purchaseForm.amount)
      console.log("start execute purchase logic")
    },

    sellShare(listId) {
      console.log("Buy share " + listId)
      console.log("Amount : " + sellForm.amount)
      console.log("start execute sell logic")
    },

    openPurchaseDialog(listId){
      this.isPurchaseDialogVisible = true;
      this.purchaseForm.listId = listId
    },

    confirmPurchase(listId){
      this.buyShare(listId)
      this.isPurchaseDialogVisible = false;

      this.purchaseForm = {
        listId: '',
        amount: '',
      }
    },
    cancelPurchase() {
      this.isPurchaseDialogVisible = false;

      this.purchaseForm = {
        listId: '',
        amount: '',
      }
    },

    openSellDialog(listId){
      this.isSellDialogVisible = true;
      this.sellForm.listId = listId
    },

    confirmSell(listId){
      this.sellShare(listId)
      this.isSellDialogVisible = false;
      this.sellForm = {
        listId: '',
        amount: '',
      }
    },

    cancelSell(){
      this.isSellDialogVisible = false;
      this.sellForm = {
        listId: '',
        amount: '',
      }
    },

    getMarketData() {
      this.marketData = [
        { id: 1, listId: '123', price: '$100', amount: '1000', companyId: '001', companyName: 'ABC Corp' },
        { id: 2, listId: '124', price: '$200', amount: '500', companyId: '002', companyName: 'XYZ Ltd' },
        { id: 3, listId: '125', price: '$300', amount: '300', companyId: '003', companyName: 'OPQ Ltd' },
        { id: 4, listId: '126', price: '$400', amount: '200', companyId: '004', companyName: 'EFG Ltd' },]
    }
  },

  beforeMount() {
    this.getUserInfo();
    this.getMarketData();
  }
};
</script>

<style lang="scss">
#customer-dashboard {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  .el-menu {
    margin-bottom: 20px;
  }
}
</style>
