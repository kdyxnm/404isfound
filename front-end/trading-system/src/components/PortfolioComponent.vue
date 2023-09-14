<template>
  <div class="portfolio-component">
    <!-- Portfolio table with Sell button -->
    <h2>Portfolio</h2>
    <h3>Net Profit is {{netProfit}}</h3>
    <el-table :data="portfolioData">
      <el-table-column prop="companyName" label="Company Name"></el-table-column>
      <el-table-column prop="amount" label="Amount"></el-table-column>
      <el-table-column prop="purchasePrice" label="Purchase Price"></el-table-column>
      <el-table-column prop="currentPrice" label="Current Price"></el-table-column>
      <el-table-column prop="industry" label="Industry"></el-table-column>
      <el-table-column label="Actions">
        <template #default="scope">
          <el-button type="primary" @click="sellShare(scope.listId)">Sell</el-button>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
import { ref } from 'vue';
import CompanyListings from './CompanyListings.vue';

export default {
  components: {
    CompanyListings,
  },

  props: {
    userId: {
      type: Number,
      default: null,
    },
  },

  setup(props, context) {
    const portfolioData = ref([]);
    const netProfit = ref(0);

    const getPortfolioDataAndNetProfit = async () => {
      // Fetch the portfolio data and net profit from your API or data source here
      console.log('getting user info, id : ' + props.userId)
      
      portfolioData.value = [
        { companyName: 'Company A', amount: 100, purchasePrice: 100, currentPrice: 150, industry: 'Tech' },
        { companyName: 'Company B', amount: 200, purchasePrice: 500, currentPrice: 150, industry: 'Education' },
        { companyName: 'Company C', amount: 300, purchasePrice: 400, currentPrice: 150, industry: 'Manufacture' },
      ];
      
      // Calculate net profit here based on your data
      // For demonstration, I'm setting it with a static value
      netProfit.value = 500; 
    };

    onBeforeMount(() => {
      getPortfolioDataAndNetProfit();
    });

    const sellShare = (listId) => {
      context.emit('sell-share', listId)
    }

    return {
      portfolioData,
      netProfit,
      sellShare,
    };
  },
};
</script>

<style lang="scss">
.portfolio-component {
  .el-button {
    margin-right: 10px;
  }
  .el-input {
    margin-bottom: 20px;
  }
  .el-table {
    width: 100%;
  }
}
</style>
