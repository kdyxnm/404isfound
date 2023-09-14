<template>
  <div class="search-component">
    <h2>Search For Share</h2>

    <el-input placeholder="Company Name" v-model="filters.companyName"></el-input>
    <el-input placeholder="Industry" v-model="filters.industry"></el-input>
    <el-select v-model="filters.shareType" placeholder="Select Type" size="large">
      <el-option
        :label="'A'"
        :value="'A'"
      />
      <el-option
        :label="'B'"
        :value="'B'"
      />
      <el-option
        :label="'C'"
        :value="'C'"
      />
    </el-select>
    <el-input placeholder="Price Range" v-model="filters.priceRange"></el-input>
    <el-button type="primary" @click="searchShares">Search</el-button>

    <!-- Search results table with Buy button -->
    <h2>Search Result</h2>
    <company-listings class="result" :companyListings="searchResults" :displayType="'Buy'" @buy-share="buyShare"></company-listings>

  </div>
</template>

<script>
import { ref } from 'vue';
import CompanyListings from "./CompanyListings.vue"

export default {
  components: {
    CompanyListings,
  },
  setup(props, context) {
    const filters = ref({
      companyName: '',
      industry: '',
      shareType: '',
      priceRange: '',
    });
    const searchResults = ref([]);
    const purchaseAmount = ref('');

    const searchShares = () => {
      var that = this

      console.log('search cirteria ')
      console.log(filters.value)
      // Implement the search logic later

      searchResults.value = [
        { id: 1, listId: '123', price: '$100', amount: '1000', companyId: '001', companyName: 'ABC Corp' },
        { id: 2, listId: '124', price: '$200', amount: '500', companyId: '002', companyName: 'XYZ Ltd' },
        { id: 3, listId: '125', price: '$300', amount: '300', companyId: '003', companyName: 'OPQ Ltd' },
        { id: 4, listId: '126', price: '$400', amount: '200', companyId: '004', companyName: 'EFG Ltd' },
      ];

    };
    const buyShare = (listId) => {
      context.emit('buy-share', listId);
    };
    return {
      filters,
      searchResults,
      purchaseAmount,
      searchShares,
      buyShare,
    };
  },
};
</script>

<style lang="scss">
.search-component {
  display: flex;
  flex-direction: column;
  align-items: center;
  .el-button {
    margin-right: 10px;
  }
  .el-input {
    margin-bottom: 20px;
    width: 50%;
  }
  .el-table {
    width: 100%;
  }
  .el-select{
    width: 50%;
  }
  .result{
    width: 100%;
  }
}
</style>
